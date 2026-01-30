package com.kroger.pharmacy.csr.view.action.csr.config;

import com.kroger.commons.web.stripes.AbstractActionBean;
import com.kroger.commons.web.stripes.ActionBeanContext;
import com.kroger.pharmacy.csr.util.ConfigLoader;
import com.nimbusds.oauth2.sdk.AuthorizationRequest;
import com.nimbusds.oauth2.sdk.ResponseType;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.openid.connect.sdk.OIDCScopeValue;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.URI;
import java.util.Map;

public class LoginActionBean extends AbstractActionBean {

    private static Log logger = LogFactory.getLog(com.kroger.pharmacy.csr.view.action.csr.config.LoginActionBean.class);

//    private Map<String, String> properties = PropertiesUtil.getServices();

    @DefaultHandler
    public Resolution init() throws Exception {
        ConfigLoader loader = new ConfigLoader();
        System.out.println("Info: Entering init() method...");
        logger.info("Info: Initiating LoginAction ...");

        String pingClientId = loader.getClientId();
        String pingAuthorizeEndpoint = loader.getAuthEndPoint();

        System.out.println("Info: Loaded properties - pingClientId: " + pingClientId + ", pingAuthorizeEndpoint: " + pingAuthorizeEndpoint);
        logger.debug("Debug: pingAuthorizeEndpoint=" + pingAuthorizeEndpoint);

        ActionBeanContext context = getContext();

        System.out.println("Info: Constructing Authorization Endpoint URI...");
        URI authzEndpoint = new URI(pingAuthorizeEndpoint);
        ClientID clientID = new ClientID(pingClientId);
        Scope scope = new Scope(OIDCScopeValue.OPENID);
        State state = new State();

        System.out.println("Info: Generating Redirect URL...");
        String redirectURL = context.getRequest().getRequestURL().toString().replace("login", "callback");
		if (redirectURL.startsWith("http://") && !redirectURL.contains("localhost")) {
			redirectURL = redirectURL.replaceFirst("http://", "https://");
        }
        URI callback = new URI(redirectURL);
        System.out.println("Info: Generated Redirect URL: " + redirectURL);

        ResponseType.Value value = ResponseType.Value.CODE;
        ResponseType resp = new ResponseType(value);

        System.out.println("Info: Building Authorization Request...");
        AuthorizationRequest authorizationRequest = new AuthorizationRequest.Builder(resp, clientID)
                .scope(scope).state(state).redirectionURI(callback).endpointURI(authzEndpoint).build();

        System.out.println("Info: Redirecting user to Authorization Endpoint...");
        URI requestURI = authorizationRequest.toURI();
        context.getResponse().sendRedirect(requestURI.toString());

        logger.info("Info: Completing LoginAction ...");
        System.out.println("Info: Exiting init() method and completing LoginAction.");

        return null;
    }
}
