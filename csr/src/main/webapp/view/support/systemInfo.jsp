<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld" prefix="kroger"%>
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="csr System Properties" documentType="doc3"
	documentClass="yui-t2" navbarAdminSupportCurrent="current">
	<stripes:layout-component name="sidemenu">
		<stripes:layout-render name="/WEB-INF/jsp/support/sidemenu.jsp"
			adminSystemInfoPageCurrent="current" />
	</stripes:layout-component>
	<stripes:layout-component name="body">
		<kroger:systemInfo caption="System Properties"/>
	</stripes:layout-component>
</stripes:layout-render>
