<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
	
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="CSR Manager - State Contacts" documentType="doc3"
	documentClass="yui-t2" navbarPhoneBookCurrent="current">
	<stripes:layout-component name="head">
	</stripes:layout-component>
	<stripes:layout-component name="sidemenu">
		<stripes:layout-render 
                  name="/WEB-INF/jsp/csr/sidemenu.jsp"
			csrStateListPageCurrent="current" />
	</stripes:layout-component>
	<stripes:layout-component name="body">
		
		<stripes:useActionBean id="csrContactsActionBean" 
				beanclass="com.kroger.pharmacy.csr.view.action.csr.CsrContactsBean" />
		<stripes:form action="/CsrContacts.action"
						name="csrContactsForm" id="stripesForm">
			<div id="contactsListPane">
				<table width="700px">
					<caption>${csrContactsActionBean.stateReportingDetail.state.name} CSR Contacts</caption>
					<thead>
						<tr>
							<th class="selectedColumn"></th>
							<th class="contactNameColumn">Name</th>
							<th class="contactPhoneColumn">Phone</th>
							<th class="contactEmailColumn">Email</th>
							<th class="contactCommentsColumn">Comments</th>
						</tr>
					</thead>
				</table>
				<div id="contactView" class="viewer" style="height: 200px; width: 700px; overflow-x: hidden; overflow-y: auto;">
					<stripes:hidden name="selectedContactId" id="selectedId" />
					<table class="contactTable" id="contactTable" width="700px">
						
						<tbody id="contactListResult">
							<c:forEach items="${csrContactsActionBean.contacts}" 
								var="contact" varStatus="loop" >
								
								<c:url var="editContactUrl" value="/view/csr/csrcontactdetails.jsp">
									<c:param name="contactId" value="${contact.id}" />
									<c:param name="detailId" value="${csrContactsActionBean.stateReportingDetail.id}" />
								</c:url>
								
								<tr id="contactRow${loop.index}" onclick="selectRow(this,${loop.index},'contactId','selectedId');" class="normalRow" onmouseover="listOver(this);" onmouseout="listOut(this);">
										
									<td class="selectedColumn" >
										<input type="radio" id="selectedCheckbox${loop.index}" value="true" />
										<input type="hidden" id="contactId${loop.index}" value="${contact.id}" /> 
									</td>	
									<td class="contactNameColumn" >
										${contact.lastName}, ${contact.firstName}
									</td>
									<td class="contactPhoneColumn" >
										${contact.phoneNumber}
									</td>
									<td class="contactEmailColumn" >
										${contact.emailAddress}
									</td>
									<td class="contactCommentsColumn" >
										${contact.comments}&nbsp;
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="actionButtons">
					<stripes:submit name="addContact" value="New Contact" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />&nbsp;
					<stripes:submit name="editContact" value="Edit Contact" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />&nbsp;
					<stripes:submit name="deleteContact" value="Delete Contact" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" onclick="return confirm('Delete the selected contact?');"/>&nbsp;
					<stripes:submit name="editEmailDetails" value="Configure Email Alert" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />
				</div>
			</div>
			<stripes:wizard-fields />
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>