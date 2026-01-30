<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
<stripes:layout-definition>
	<div id="navbar_container">
		<div id="navmenubar" class="yuimenubar yuimenubarnav">
			<div class="bd">
				<ul class="first-of-type">
					<li class="yuimenubaritem first-of-type">
						<a class="yuimenubaritemlabel ${navbarHomeCurrent}"
							href="<c:url value="/view/user/welcome.jsp"/>">Home</a>
					</li>
					<kroger:securityCheck roles="sm-csr-support">
						<li class="yuimenubaritem">
							<a class="yuimenubaritemlabel ${navbarAdminSupportCurrent}"
								href="<c:url value="/view/support/systemInfo.jsp"/>">Application
								Support</a>
						</li>
					</kroger:securityCheck>
				</ul>
			</div>
		</div>
	</div>
</stripes:layout-definition>
