<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
	
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="CSR Manager - Edit Contact" documentType="doc3"
	documentClass="yui-t2" navbarPhoneBookCurrent="current">
	<stripes:layout-component name="head">
	</stripes:layout-component>
	<stripes:layout-component name="sidemenu">
		<stripes:layout-render 
                  name="/WEB-INF/jsp/csr/sidemenu.jsp"
			csrStateListPageCurrent="current" />
	</stripes:layout-component>
	<stripes:layout-component name="body">
		
		<stripes:useActionBean id="editContactActionBean" 
				beanclass="com.kroger.pharmacy.csr.view.action.csr.EditContactBean" />
		<stripes:form action="/EditContact.action"
						name="editContactForm" id="stripesForm">
			<div id="contactDetailsPane">
				<div id="contactView" >
					<table>
						<tr>
							<td>
								<stripes:label for="contact.lastName">Last Name</stripes:label>
								<stripes:text name="contact.lastName" />
							</td>
						</tr>
						<tr>
							<td>
								<stripes:label for="contact.firstName">First Name</stripes:label>
								<stripes:text name="contact.firstName" />
							</td>
						</tr>
						<tr>
							<td>
								<stripes:label for="contact.phoneNumber">Phone Number</stripes:label>
								<stripes:text name="contact.phoneNumber" />
							</td>
						</tr>
						<tr>
							<td>
								<stripes:label for="contact.emailAddress">Email Address</stripes:label>
								<stripes:text name="contact.emailAddress" />
							</td>
						</tr>
						<tr>
							<td>
								<stripes:label for="contact.comments">Comments</stripes:label>
								<stripes:textarea name="contact.comments" />
							</td>
						</tr>
					</table>
				</div>
				<div class="actionButtons">
					<stripes:submit name="submit" value="Submit" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />&nbsp;
					<stripes:submit name="cancel" value="Cancel" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />
				</div>
			</div>
			<stripes:wizard-fields />
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>