package com.kroger.pharmacy.csr.view.action.csr.config;

import com.kroger.commons.web.stripes.AbstractActionBean;
import com.kroger.commons.web.stripes.ActionBeanContext;
import com.kroger.pharmacy.csr.util.ConfigLoader;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;


public class LogoutActionBean extends AbstractActionBean {

    private static Log logger = LogFactory.getLog(com.kroger.pharmacy.csr.view.action.csr.config.LogoutActionBean.class);

//    private Map<String, String> properties = PropertiesUtil.getServices();

    @DefaultHandler
    public Resolution init() throws Exception {

        logger.info("Info: Initiating LogoutAction ...");
        ConfigLoader loader = new ConfigLoader();

        String pingClientId = loader.getClientId();
        String pingLogoutEndpoint = loader.getLogoutPoint();

        ActionBeanContext context = getContext();
        HttpServletRequest headersRequest = context.getRequest();
        if(SecurityContextHolder.getContext()!=null) {
            String logoutURL = (new StringBuilder()).append(pingLogoutEndpoint).append("?client_id=").append(pingClientId)
                    .toString();
            headersRequest.getSession().setAttribute("logoutURL", logoutURL);
            SecurityContextHolder.clearContext();
            logger.info("Info: Completing LogoutAction");
        }
        return null;

    }
}
