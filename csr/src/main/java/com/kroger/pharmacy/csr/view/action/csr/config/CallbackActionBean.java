package com.kroger.pharmacy.csr.view.action.csr.config;

import com.kroger.commons.web.stripes.AbstractActionBean;
import com.kroger.commons.web.stripes.ActionBeanContext;
import com.kroger.pharmacy.csr.util.ConfigLoader;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.oauth2.sdk.*;
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic;
import com.nimbusds.oauth2.sdk.auth.Secret;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponse;
import com.nimbusds.openid.connect.sdk.OIDCTokenResponseParser;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class CallbackActionBean extends AbstractActionBean {

    private static Log logger = LogFactory.getLog(com.kroger.pharmacy.csr.view.action.csr.config.CallbackActionBean.class);

//    private Map<String, String> properties = PropertiesUtil.getServices();

    private static String authorizationCodeParamKey = "code";

    @DefaultHandler
    public Resolution init() throws Exception {

        System.out.println("Info: Entering init() method...");
        logger.info("Info: Initiating CallbackAction ...");
        ConfigLoader loader = new ConfigLoader();

        String pingClientId = loader.getClientId();
        String pingClientSecret = loader.getClientSecret();
        String pingTokenEndpoint = loader.getTokenEndpoint();

        System.out.println("Info: Loaded properties - pingClientId: " + pingClientId + ", pingTokenEndpoint: " + pingTokenEndpoint);

        ActionBeanContext context = getContext();

        String codeStr = context.getRequest().getParameter(authorizationCodeParamKey);
        System.out.println("Info: Received authorization code: " + codeStr);
        AuthorizationCode code = new AuthorizationCode(codeStr);

        String redirectURL = context.getRequest().getRequestURL().toString();
		if (redirectURL.startsWith("http://") && !redirectURL.contains("localhost")) {
			redirectURL = redirectURL.replaceFirst("http://", "https://");
        }
        System.out.println("Info: Constructed redirect URL: " + redirectURL);

        URI callback = new URI(redirectURL);
        AuthorizationGrant codeGrant = new AuthorizationCodeGrant(code, callback);

        ClientID clientID = new ClientID(pingClientId);
        Secret clientSecret = new Secret(pingClientSecret);
        ClientSecretBasic clientAuth = new ClientSecretBasic(clientID, clientSecret);

        URI tokenEndpoint = new URI(pingTokenEndpoint);
        System.out.println("Info: Token endpoint URI constructed: " + tokenEndpoint);

        TokenRequest tokenRequest = new TokenRequest(tokenEndpoint, clientAuth, codeGrant);

        HTTPResponse httpResponse = null;
        TokenResponse tokenResponse = null;

        try {
            System.out.println("Info: Sending TokenRequest...");
            httpResponse = tokenRequest.toHTTPRequest().send();
            System.out.println("Info: TokenRequest HTTP response received.");

            tokenResponse = OIDCTokenResponseParser.parse(httpResponse);
            System.out.println("Info: Parsed TokenResponse: " + tokenResponse);
        } catch (Exception e) {
            System.out.println("Error: Exception occurred while requesting token: " + e.getMessage());
            logger.error(e.getMessage(), e);
            throw new SecurityException("Failed to create user session. (Error:" + e.getMessage() + ")");
        }

        System.out.println("Info: Successfully received token response.");

        if (!tokenResponse.indicatesSuccess()) {
            TokenErrorResponse errorResponse = tokenResponse.toErrorResponse();
            System.out.println("Error: Token Error - HTTP Status Code [" + errorResponse.getErrorObject().getHTTPStatusCode() + "], "
                    + errorResponse.getErrorObject().getDescription());
            logger.error("Token Error: HTTP Status Code [" + errorResponse.getErrorObject().getHTTPStatusCode() + "] "
                    + errorResponse.getErrorObject().getDescription());
            throw new SecurityException(
                    "Failed to create user session. (Error:" + errorResponse.getErrorObject().getDescription() + ")");
        }

        OIDCTokenResponse successResponse = (OIDCTokenResponse) tokenResponse.toSuccessResponse();
        System.out.println("Info: Successfully parsed OIDC Token Response.");

        JWT idToken = successResponse.getOIDCTokens().getIDToken();
        System.out.println("Info: ID Token received: " + idToken);

        JWTClaimsSet claimsSet = idToken.getJWTClaimsSet();
        Map<String, Object> claims = claimsSet.getClaims();

        System.out.println("Info: Extracted JWT claims...");
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            System.out.println("Claim - " + entry.getKey() + ": " + entry.getValue());
        }

        HttpServletRequest headersRequest = context.getRequest();
        headersRequest.getSession().setAttribute("isAuthToken", "True");
        setHeaders(headersRequest.getSession(), claims);

        System.out.println("Info: Finished setting headers and attributes.");

        context.setRequest(headersRequest);

        logger.info("Info: Completing CallbackAction ...");
        System.out.println("Info: Completing CallbackAction ...");

        return new RedirectResolution("/index.jsp");
    }

    private void setHeaders(HttpSession httpSession, Map<String, Object> claims) {
        System.out.println("Info: Entering setHeaders() method...");
        Map<String, Object> modifiableClaims = new HashMap<String, Object>(claims);
        if (!modifiableClaims.containsKey("KSW_GROUPS")) {
            modifiableClaims.put("KSW_GROUPS", "");
        }
        for (Map.Entry<String, Object> entry : modifiableClaims.entrySet()) {
            httpSession.setAttribute(entry.getKey(), entry.getValue().toString());
            System.out.println("Header - " + entry.getKey() + ": " + entry.getValue().toString());
        }
        System.out.println("Info: Exiting setHeaders() method...");
    }
}
