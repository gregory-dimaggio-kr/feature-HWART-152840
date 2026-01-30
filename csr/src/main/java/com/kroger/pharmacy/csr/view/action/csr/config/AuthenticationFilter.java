package com.kroger.pharmacy.csr.view.action.csr.config;

import com.kroger.commons.ApplicationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    private static Log logger = LogFactory.getLog(com.kroger.pharmacy.csr.view.action.csr.config.AuthenticationFilter.class);

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        CustomHttpServletRequestWrapper wrappedRequest = new CustomHttpServletRequestWrapper(httpRequest);
        if (session.getAttribute("isAuthToken") != null) {
            addHeaderIfNotNull(wrappedRequest, "KSW_USERNAME", session.getAttribute("KSW_USERNAME"));
            addHeaderIfNotNull(wrappedRequest, "KSW_DIVNO", session.getAttribute("KSW_DIVNO"));
            addHeaderIfNotNull(wrappedRequest, "KSW_LOCATION", session.getAttribute("KSW_LOCATION"));
            addHeaderIfNotNull(wrappedRequest, "KSW_FULLNAME", session.getAttribute("KSW_FULLNAME"));
            addHeaderIfNotNull(wrappedRequest, "KSW_FIRSTNAME", session.getAttribute("KSW_FIRSTNAME"));
            addHeaderIfNotNull(wrappedRequest, "KSW_LASTNAME", session.getAttribute("KSW_LASTNAME"));
            addHeaderIfNotNull(wrappedRequest, "KSW_JOBCODE", session.getAttribute("KSW_JOBCODE"));
            addHeaderIfNotNull(wrappedRequest, "KSW_EMAIL", session.getAttribute("KSW_EMAIL"));
            String kswGroups = session.getAttribute("KSW_GROUPS") != null ? session.getAttribute("KSW_GROUPS").toString() : null;
            if (kswGroups == null || kswGroups.isEmpty() || kswGroups.toLowerCase().contains("size_limit")) {
                request.setAttribute("errorMessage", "Sorry. You're not authorized to enter the area you tried to reach.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/view/public/error403.jsp");
                dispatcher.forward(request, response);
            }
            wrappedRequest.addHeader("KSW_GROUPS", kswGroups.replace("[", "").replace("]", ""));


        } else {
            wrappedRequest.removeHeaders();

        }
        super.doFilter(wrappedRequest, response, chain);
    }

    private void addHeaderIfNotNull(CustomHttpServletRequestWrapper wrappedRequest, String headerName, Object attribute) {
        if (attribute != null) {
            wrappedRequest.addHeader(headerName, attribute.toString());
        }
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return null;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return null;
    }
}
