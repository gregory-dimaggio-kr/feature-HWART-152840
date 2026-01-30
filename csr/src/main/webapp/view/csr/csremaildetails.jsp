<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
	
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="CSR Manager - Email Alert" documentType="doc3"
	documentClass="yui-t2" navbarPhoneBookCurrent="current">
	<stripes:layout-component name="head">
	</stripes:layout-component>
	<stripes:layout-component name="sidemenu">
		<stripes:layout-render 
                  name="/WEB-INF/jsp/csr/sidemenu.jsp"
			csrStateListPageCurrent="current" />
	</stripes:layout-component>
	<stripes:layout-component name="body">
		
		<stripes:useActionBean id="editEmailDetailsActionBean" 
				beanclass="com.kroger.pharmacy.csr.view.action.csr.EditEmailDetailsBean" />
		<stripes:form action="/EditEmailDetails.action"
						name="editEmailDetailsForm" id="stripesForm">
			<div id="emailDetailsDetailsPane">
				<div id="emailDetailsView" >
					<table>
						<tr>
							<td>
								<stripes:hidden name="detailId" value="${stateReportingDetail.id}" />
								<stripes:label for="emailTypeId">Email Type</stripes:label>
								<stripes:select name="emailTypeId">
									<c:forEach items="${editEmailDetailsActionBean.emailTypes}" var="emailType">
										<stripes:option label="${emailType.description}" value="${emailType.id}"  />
									</c:forEach>
								</stripes:select>
							</td>
						</tr>
						<c:forEach items="${editEmailDetailsActionBean.contacts}" var="contact" varStatus="loop">
							<tr>
								<td>
									<stripes:label for="contacts[${loop.index}].autoNotified">${contact.firstName} ${contact.lastName}</stripes:label>
									<stripes:checkbox name="contacts[${loop.index}].autoNotified" />
								</td>
							</tr>
						</c:forEach>
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