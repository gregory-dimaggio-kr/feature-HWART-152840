<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
           prefix="stripes"%>
<%@ page import="org.springframework.security.web.WebAttributes"%>
<%@ page import="org.springframework.security.core.AuthenticationException"%>
<%@ page import="com.kroger.commons.security.spoofing.SpoofingUtil"%>
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
                       pageTitle="Login" documentType="doc3" documentClass="yui-t7">
    <stripes:layout-component name="body">
        <%
            String redirectUrl = "";
            if (!SpoofingUtil.isSpoofingAllowed()) {
        %>
        <stripes:useActionBean
                beanclass="com.kroger.pharmacy.csr.view.action.csr.config.LogoutActionBean"
                id="logoutActionBean" event="init" alwaysExecuteEvent="true" />
        <%
                redirectUrl = session.getAttribute("logoutURL").toString();
                session.invalidate();
            }
        %>
        <%
            if (SpoofingUtil.isSpoofingAllowed()) {

                String siteMinderLogoutUrl = "http://intraweb.kroger.com/SecureWEB/kswlogout.htm";
                String appReentryUrl = request.getScheme()
                        + "://" + request.getServerName() + ":"
                        + request.getServerPort()
                        + request.getContextPath()
                        + "/index.jsp";
                redirectUrl = siteMinderLogoutUrl
                        + "?redirect=" + appReentryUrl;
                session.invalidate();

            }
        %>
        <c:redirect url="<%=redirectUrl%>" />
    </stripes:layout-component>
    <stripes:layout-component name="announcement">
        <!--  no announcement -->
    </stripes:layout-component>
</stripes:layout-render>
