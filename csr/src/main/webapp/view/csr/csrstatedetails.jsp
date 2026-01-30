<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
	
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="CSR Manager - Edit State" documentType="doc3"
	documentClass="yui-t2" navbarPhoneBookCurrent="current">
	<stripes:layout-component name="head">
	</stripes:layout-component>
	<stripes:layout-component name="sidemenu">
		<stripes:layout-render 
                  name="/WEB-INF/jsp/csr/sidemenu.jsp"
			csrStateListPageCurrent="current" />
	</stripes:layout-component>
	<stripes:layout-component name="body">
		
		<stripes:useActionBean id="editStateActionBean" 
				beanclass="com.kroger.pharmacy.csr.view.action.csr.EditStateBean" />
		<stripes:form action="/EditState.action"
						name="editStateForm" id="stripesForm">
			<div id="stateDetailsPane">
				<div id="stateView" >
					<table>
						<tr>
							<td>
								<stripes:label for="stateReportingDetail.state.name">State Name</stripes:label>
								<stripes:text name="stateReportingDetail.state.name" />
							</td>
						</tr>
						<tr>
							<td>
								<stripes:label for="stateReportingDetail.state.code">State Postal Code</stripes:label>
								<stripes:text name="stateReportingDetail.state.code" />
							</td>
						</tr>
						<tr>
							<td>
								<stripes:label for="stateReportingDetail.websiteUrl">External Website URL</stripes:label>
								<stripes:text name="stateReportingDetail.websiteUrl" />
							</td>
						</tr>
						<tr>
							<td>
								<stripes:label for="scheduleId">Reporting Schedule</stripes:label>
								<stripes:select name="scheduleId">
									<c:forEach items="${editStateActionBean.schedules}" var="schedule">
										<stripes:option label="${schedule.description}" value="${schedule.id}"  />
									</c:forEach>
								</stripes:select>
							</td>
						</tr>
						<!--  tr>
							<td>
								<stripes:label for="asapFormatId">ASAP Format</stripes:label>
								<stripes:select name="asapFormatId">
									<stripes:option label="ASAP 1995" value="1995" />
									<stripes:option label="ASAP 2005" value="2005" />
								</stripes:select>
							</td>
						</tr-->
						<tr>
							<td>
								<stripes:label for="methodId">File Transmission Method</stripes:label>
								<stripes:select name="methodId">
									<c:forEach items="${editStateActionBean.methods}" var="method">
										<stripes:option label="${method.description}" value="${method.id}"  />
									</c:forEach>
								</stripes:select>
							</td>
						</tr>
						<tr>
							<td>
								<stripes:label for="stateReportingDetail.active">Active?</stripes:label>
								<stripes:checkbox name="stateReportingDetail.active" />
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