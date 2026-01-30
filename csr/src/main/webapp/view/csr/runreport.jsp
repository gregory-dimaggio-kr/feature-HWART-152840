<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
<%@ taglib uri="http://calendar.commons.kroger.com/tlds/taglib.tld"
	prefix="calendar"%>
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="Run CSR Report" documentType="doc3"
	documentClass="yui-t2" navbarPhoneBookCurrent="current">
	<stripes:layout-component name="head">
		<script type="text/javascript">
			function changeState(element,e) {
				var recordsDropDown = document.getElementById('changedDropDown');
				if ( recordsDropDown.value == 'false' ) {
					var form = document.getElementById('stripesForm');
					AjaxDateRangeObject.startRequest(form.action,element.value,'beginDateRange','endDateRange');
				}
				
			}
			
			function changeRecordsFlag(element,e) {
				var stateDropDown = document.getElementById('stateCode');
				var begin = document.getElementById('beginDateRange');
				var end = document.getElementById('endDateRange');
				var datePane = document.getElementById('datePane');
				
				if ( element.value == 'true' ) {
					begin.value = '';
					end.value = '';
					begin.disabled = true;
					end.disabled = true;
					datePane.className = 'hidden';
				}
				else {
					begin.disabled = false;
					end.disabled = false;
					datePane.className = '';
					var form = document.getElementById('stripesForm');
					AjaxDateRangeObject.startRequest(form.action,stateDropDown.value,'beginDateRange','endDateRange');
				}
			}
			
			function runClick() {
				document.body.style.cursor='wait';
				document.getElementById('loadingPane').className='loadingPane';
				setTimeout('document.images["loadingImage"].src = "<c:url value="${styleUrl}/images/loading.gif"/>"', 10);
			}
		</script>
	</stripes:layout-component>
	<stripes:layout-component name="sidemenu">
		<stripes:layout-render 
                  name="/WEB-INF/jsp/csr/sidemenu.jsp"
			runReportPageCurrent="current" />
	</stripes:layout-component>
	<stripes:layout-component name="body">
		
		<stripes:useActionBean id="runReportActionBean" 
				beanclass="com.kroger.pharmacy.csr.view.action.csr.RunReportBean" />
		<stripes:form action="/RunReport.action"
						name="runReportForm" id="stripesForm">
			<div id="runReportPane">
				<table width="100%">
					<caption>Run CSR Report</caption>
					<tbody>
						<tr>
							<td>
								<stripes:label for="stateCode">State</stripes:label>
								<stripes:select name="stateCode" id="stateCode" onchange="changeState(this,event);" >
									<c:forEach items="${runReportActionBean.states}" var="stateDetail">
										<stripes:option value="${stateDetail.state.code}">${stateDetail.state.name}</stripes:option>	
									</c:forEach>
								</stripes:select>
							</td>
						</tr>
						<tr>
							<td>
								<stripes:label for="changedExtractsOnly">Records Included</stripes:label>
								<stripes:select name="changedExtractsOnly" id="changedDropDown" onchange="changeRecordsFlag(this,event);">
									<stripes:option value="false">All Records</stripes:option>	
									<stripes:option value="true">Only Changed Records</stripes:option>
								</stripes:select>
							</td>
						</tr>
					</tbody>
				</table>
				<div id="datePane">
				<table width="100%">
					<tbody>
						<tr>
							<td>
								<stripes:label for="beginDateRange">Start Date</stripes:label>
								<calendar:fiscalCalendar name="beginDateRange" value="${runReportActionBean.formattedBeginDate}" calendarType="PERIOD" />
							</td>
						</tr>
						<tr>
							<td>
								<stripes:label for="endDateRange">End Date</stripes:label>
								<calendar:fiscalCalendar name="endDateRange" value="${runReportActionBean.formattedEndDate}" calendarType="PERIOD" />
							</td>
						</tr>
					</tbody>
				</table>
				</div>
				<div class="actionButtons" >
					<stripes:submit name="run" value="Run" onclick="runClick();" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />
					<div id="loadingPane" class="hidden" >
						Running report... <img id="loadingImage" "<c:url value="${styleUrl}/images/loading.gif"/>" />
					</div>
				</div>
			</div>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>