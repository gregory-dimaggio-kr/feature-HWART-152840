<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
           prefix="stripes"%>
<%@ page import="org.springframework.security.web.WebAttributes"%>
<%@ page
        import="org.springframework.security.core.AuthenticationException"%>
<%@ page import="com.kroger.commons.security.spoofing.SpoofingUtil"%>
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
                       pageTitle="Login" documentType="doc3" documentClass="yui-t7">
    <stripes:layout-component name="navbar">
        <ul class="first-of-type">
            <li class="yuimenubaritem first-of-type current"><a
                    class="yuimenubaritemlabel"
                    href="<c:url value="/view/public/logout.jsp"/>">Login</a></li>
        </ul>
    </stripes:layout-component>
    <stripes:layout-component name="body">
        <stripes:useActionBean
                beanclass="com.kroger.pharmacy.csr.view.action.csr.config.CallbackActionBean"
                id="callbackActionBean" event="init" alwaysExecuteEvent="true" />
    </stripes:layout-component>
    <c:redirect url="/index.jsp" />
</stripes:layout-render>
