<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="csr Welcome Page" documentType="doc3" documentClass="yui-t7"
	navbarHomeCurrent="current">
	<stripes:layout-component name="body">
		<c:redirect url="/view/csr/csrstatelist.jsp" />
	</stripes:layout-component>
</stripes:layout-render>
