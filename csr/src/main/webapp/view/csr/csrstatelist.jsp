<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
	
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="CSR Manager" documentType="doc3"
	documentClass="yui-t2" navbarPhoneBookCurrent="current">
	<stripes:layout-component name="head">
		<script type="text/javascript">
			function changeSort(col) {
				document.getElementById('sortColumn').value = col;
				document.getElementById('sortButton').click();
				
				return false;
			}
		</script>
	</stripes:layout-component>
	<stripes:layout-component name="sidemenu">
		<stripes:layout-render 
                  name="/WEB-INF/jsp/csr/sidemenu.jsp"
			csrStateListPageCurrent="current" />
	</stripes:layout-component>
	<stripes:layout-component name="body">
		
		<stripes:useActionBean id="csrStateActionBean" 
				beanclass="com.kroger.pharmacy.csr.view.action.csr.CsrStateBean" executeResolution="defaultHandler"/>
		<stripes:form action="/CsrState.action"
						name="csrStateForm" id="stripesForm">
			
			<div id="legendPane" align="right">
				<table class="legend" width="180">
					<caption>Legend</caption>
					<tr>
						<td style="color:green;">&#9632;</td>
						<td>Transferred Reports</td>
					</tr>
					<tr>
						<td style="color:red;">&#9632;</td>
						<td>Untransferred Reports</td>
					</tr>
				</table>
			</div>
			<div id="stateListPane">
				<table width="100%">
					<caption>CSR States</caption>
					<thead>
						<tr>
							<th class="selectedColumn"></th>
							<th class="stateNameColumn">
								State
								<a href="#" class="sortButton" onclick="return changeSort('state.name');">&#9660;</a>
							</th>
							<th class="stateActiveColumn">
								Active
								<a href="#" class="sortButton" onclick="return changeSort('active');">&#9660;</a>
							</th>
							<th class="stateScheduleColumn">
								Schedule
								<a href="#" class="sortButton" onclick="return changeSort('schedule.description');">&#9660;</a>
							</th>
							<th class="stateFormatColumn">
								ASAP Format
								<a href="#" class="sortButton" onclick="return changeSort('csrState.asapFormat');">&#9660;</a>
							</th>
							<th class="stateMethodColumn">
								Transfer Method
								<a href="#" class="sortButton" onclick="return changeSort('method.description');">&#9660;</a>
							</th>
							<th class="stateWebLinkColumn">
								Agency Website
							</th>
							<!-- th class="stateContactsColumn">
								Kroger Responsible Contacts
							</th -->
							<th class="stateReportColumn">
								Latest Report Details
							</th>
						</tr>
					</thead>
				</table>
				<div class="hidden">
					<stripes:hidden name="sortColumn" id="sortColumn" />
					<stripes:submit name="sortList" id="sortButton" />
				</div>
				<div id="stateView" class="viewer" style="height: 200px; width: 100% overflow-x: hidden; overflow-y: auto;">
					<stripes:hidden name="selectedDetailId" id="selectedId" />
					<table class="stateTable" id="stateTable" width="100%">
						
						<tbody id="stateListResult">
							<c:forEach items="${csrStateActionBean.stateReportingDetails}" 
								var="detail" varStatus="loop" >
								
								<c:url var="viewContactsUrl" value="/view/csr/csrcontacts.jsp" >
									<c:param name="detailId" value="${detail.id}" />
								</c:url>
								<c:url var="downloadReportUrl" value="/view/csr/ranreport.jsp" >
									<c:param name="reportId" value="${detail.latestReport.id}" />
								</c:url>
								<c:url var="reportArchiveUrl" value="/view/csr/csrreports.jsp" >
									<c:param name="detailId" value="${detail.id}" />
								</c:url>
								<c:url var="externalUrl" value="${detail.websiteUrl}" />
								
								<tr id="detailRow${loop.index}" onclick="selectRow(this,${loop.index},'detailId','selectedId');" class="normalRow" onmouseover="listOver(this);" onmouseout="listOut(this);">
									
									<td class="selectedColumn" >
										<input type="radio" id="selectedCheckbox${loop.index}" value="true" />
										<input type="hidden" id="detailId${loop.index}" value="${detail.id}" /> 
									</td>	
									<td class="stateNameColumn" >
										${detail.state.name}
									</td>
									<td class="stateActiveColumn" >
										<c:choose>
											<c:when test="${detail.active}"><span class="activeState">Y</span></c:when>
											<c:otherwise><span class="inactiveState">N</span></c:otherwise>
										</c:choose>
									</td>
									<td class="stateScheduleColumn" >
										${detail.schedule.description}
									</td>
									<td class="stateFormatColumn" >
										${detail.csrState.asapFormat}
									</td>
									<td class="stateMethodColumn" >
										${detail.method.description}
									</td>
									<td class="stateWebLinkColumn" >
										<c:choose>
											<c:when test="${! empty detail.websiteUrl}">
												<a href="<c:out value="${externalUrl}" />" onclick="window.open('<c:out value="${externalUrl}" />','externalWindow');return false;">Visit Site</a>
											</c:when>
											<c:otherwise>
												<a href="#"></a>
											</c:otherwise>
										</c:choose>
										
									</td>
									<!-- td class="stateContactsColumn" >
										<a href="<c:out value="${viewContactsUrl}" />">View Contacts</a>
									</td -->
									<td class="stateReportColumn" >
										<c:choose>
											<c:when test="${! empty detail.latestReport.formattedGenerationDate}">
												<span class="<c:if test="${detail.latestReport.submitted}">sentReport</c:if>
																<c:if test="${!detail.latestReport.submitted}">unsentReport</c:if>"
																onmouseover="document.getElementById('reportBalloon${detail.id}').styleClass='openBalloon';"
																onmouseout="document.getElementById('reportBalloon${detail.id}').styleClass='balloon';" >
													<a href="<c:out value="${downloadReportUrl}" />" >
														${detail.latestReport.formattedGenerationDate}
													</a>
												</span>
												<div class="balloon" id="reportBalloon${detail.id}">
													<table>
														<tr>
															<td>Submitted to ${detail.state.name} on ${detail.latestReport.formattedAgencySubmissionDate}</td>
														</tr>
													</table>
												</div>
											</c:when>
											<c:otherwise>
												Not Available
											</c:otherwise>
										</c:choose>
										(<a href="<c:out value="${reportArchiveUrl}" />" >Archive</a>)
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="actionButtons">
					<stripes:submit name="addState" value="Add State" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />&nbsp;
					<stripes:submit name="editState" value="Edit State" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />&nbsp;
					<stripes:submit name="viewContacts" value="View State Contacts" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />&nbsp;
					<stripes:submit name="deleteState" value="Delete State" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" onclick="return confirm('Are you sure you wish to delete the selected state?');" />&nbsp;
				</div>
			</div>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>