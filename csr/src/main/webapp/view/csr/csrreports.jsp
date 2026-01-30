<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
	
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="CSR Manager - State Reports" documentType="doc3"
	documentClass="yui-t2" navbarPhoneBookCurrent="current">
	<stripes:layout-component name="head">
	</stripes:layout-component>
	<stripes:layout-component name="sidemenu">
		<stripes:layout-render 
                  name="/WEB-INF/jsp/csr/sidemenu.jsp"
			csrStateListPageCurrent="current" />
	</stripes:layout-component>
	<stripes:layout-component name="body">
		
		<stripes:useActionBean id="csrReportsActionBean" 
				beanclass="com.kroger.pharmacy.csr.view.action.csr.CsrReportsBean" />
		<stripes:form action="/CsrReports.action"
						name="csrReportsForm" id="stripesForm">
			<div id="reportsListPane">
				<table width="100%">
					<caption>${csrReportsActionBean.stateReportingDetail.state.name} CSR Reports</caption>
					<thead>
						<tr>
							<th class="selectedColumn"></th>
							<th class="reportGenerationDateColumn">Generation Date</th>
							<th class="reportFilenameColumn">Filename</th>
							<th class="reportRecordsColumn">Number of Records</th>
							<th class="reportSubmissionDateColumn">Transmission Date</th>
						</tr>
					</thead>
				</table>
				<div id="reportView" class="viewer" style="height: 200px; width: 700px overflow-x: hidden; overflow-y: auto;">
					<stripes:hidden name="selectedReportId" id="selectedId" />
					<table id="reportTable" width="100%">
						
						<tbody id="reportListResult">
							<c:forEach items="${csrReportsActionBean.reports}" 
								var="report" varStatus="loop" >
								
								<c:url var="downloadReportUrl" value="/view/csr/ranreport.jsp">
									<c:param name="reportId" value="${report.id}" />
								</c:url>
								
								<tr id="reportRow${loop.index}" onclick="selectRow(this,${loop.index},'reportId','selectedId');" class="normalRow" onmouseover="listOver(this);" onmouseout="listOut(this);">
										
									<td class="selectedColumn" >
										<input type="radio" id="selectedCheckbox${loop.index}" value="true" />
										<input type="hidden" id="reportId${loop.index}" value="${report.id}" /> 
									</td>	
									<td class="reportGenerationDateColumn" >
										${report.formattedGenerationDateTime}
									</td>
									<td class="reportFilenameColumn" >
										${report.filename}
									</td>
									<td class="reportRecordsColumn" >
										${report.numRecords}
									</td>
									<td class="reportSubmissionDateColumn" >
										${report.formattedAgencySubmissionDateTime}
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="actionButtons">
					<stripes:submit name="viewReport" value="View Report Details" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />&nbsp;
				</div>
			</div>
			<stripes:wizard-fields />
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>