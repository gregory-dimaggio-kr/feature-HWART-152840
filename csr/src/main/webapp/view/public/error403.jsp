<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
		   prefix="stripes"%>
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
					   pageTitle="Access Denied" documentType="doc3" documentClass="yui-t7">
	<stripes:layout-component name="navbar">
		<ul class="first-of-type">
			<li index="0" groupindex="0" id="yui-gen0"
				class="yuimenubaritem first-of-type current">
				<a href="#">Access Denied</a>
			</li>
		</ul>
	</stripes:layout-component>
	<stripes:layout-component name="body">
		<font color="red"> You have requested a page that you do not
			have proper authorization to access. <br /> <br /> Click <a
					href="<c:url value="/view/public/logout.jsp"/>">here</a> to logout. </font>
	</stripes:layout-component>
</stripes:layout-render>
