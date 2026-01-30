<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
<stripes:layout-definition>
	<ul class="first-of-type">
		<kroger:securityCheck roles="sm-csr-support">
			<li class="yuimenuitem first-of-type">
				<a class="yuimenubaritemlabel ${adminSystemInfoPageCurrent}" href="<c:url value="/view/support/systemInfo.jsp"/>">System
					Properties</a>
			</li>
		</kroger:securityCheck>
		<kroger:securityCheck roles="sm-csr-support">
			<li class="yuimenuitem">
				<a class="yuimenubaritemlabel ${adminCurrentUsersPageCurrent}" href="<c:url value="/view/support/users.jsp"/>">Current Users</a>
			</li>
			<li class="yuimenuitem">
				<a class="yuimenubaritemlabel ${adminAdminAnnouncementsCurrent}" href="<c:url value="/view/support/announcements.jsp"/>">Announcements</a>
			</li>
		</kroger:securityCheck>
		<li class="yuimenuitem">
			<a class="yuimenubaritemlabel ${adminPreferencesPageCurrent}" href="<c:url value="/view/support/preferences.jsp"/>">Application
				Preferences</a>
		</li>
		<kroger:securityCheck roles="sm-csr-support">
			<li class="yuimenuitem">
				<a class="yuimenubaritemlabel ${adminLoggingPageCurrent}" href="<c:url value="/view/support/logging.jsp"/>">Logging
					Levels</a>
			</li>
		</kroger:securityCheck>
		<kroger:securityCheck roles="sm-csr-support">
			<li class="yuimenuitem">
				<a class="yuimenubaritemlabel ${adminSwitchUserPageCurrent}" href="<c:url value="/view/support/switchUser.jsp"/>">Switch
					User</a>
			</li>
		</kroger:securityCheck>
	</ul>
</stripes:layout-definition>
