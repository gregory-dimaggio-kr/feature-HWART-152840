<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="csr Application Switch User Support" documentType="doc3"
	documentClass="yui-t2" navbarAdminSupportCurrent="current">
	<stripes:layout-component name="sidemenu">
		<stripes:layout-render name="/WEB-INF/jsp/support/sidemenu.jsp"
			adminSwitchUserPageCurrent="current" />
	</stripes:layout-component>
	<stripes:layout-component name="body">
		<kroger:securityCheck roles="kroger-changed-user">
		You have already switched to another user. You must return to your
		original user before switching to another user.
		<br />
			<br />
			<a href="<c:url value="j_spring_security_exit_user"/>">Switch User Back</a>
		</kroger:securityCheck>
		<kroger:securityCheck roles="kroger-changed-user" negate="true">
			<form name="switchUserForm" id="switchUserForm"
				action="<c:url value="/j_acegi_switch_user"/>" method="get"
				onsubmit="return validate()">
				Enterprise Id:
				<input type="text" name="j_username" maxlength="10" />
				<span class="actionButtons">
					<input type="submit" name="SwitchUser" value="Switch User"> 
				</span>
			</form>
			<script type="text/javascript">
		function validate()
		{
		    var username = document.switchUserForm.j_username.value;
		    if (username == "")
		    {
		        alert("Please enter an Id.");
		        return false;
		    }
		    return true;
		}
		</script>
		</kroger:securityCheck>
	</stripes:layout-component>
</stripes:layout-render>
