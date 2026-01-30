<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
<stripes:layout-definition>
	<jsp:useBean id="securityBean"
		class="com.kroger.commons.security.SecurityBean" />
	<c:if
		test="${!securityBean.anonymous && securityBean.username != 'anonymousUser'}">
		<span class="links"> Welcome <%=securityBean.getFirstname()%>
			<kroger:securityCheck roles="kroger-changed-user">
			| <a href="<c:url value="/j_spring_security_exit_user"/>">Switch User Back</a>
			</kroger:securityCheck>
			| <a href="javascript:aboutAjaxRequest('<c:url value="/view/public/about.jsp"/>');">About</a>
            | <a href="<c:url value="/view/public/logout.jsp"/>">Logout</a> </span>
	</c:if>
	<h1><img src="<c:url value="/images/kroger_logo.svg"/>" alt="Kroger Logo" style="vertical-align: middle; line-height: 42px;" height="42" width="42">CSR Web Manager</h1>
</stripes:layout-definition>
