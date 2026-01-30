<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
	
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="Ran CSR Report" documentType="doc3"
	documentClass="yui-t2" navbarPhoneBookCurrent="current">
	<stripes:layout-component name="head">
		<script type="text/javascript">
			function setUpUnloadEvent(e) {
				if ( document.getElementById('submitForced').value == 'true' ) {
					window.onbeforeunload = unloadEvent;
				}
			}
		
			function unloadEvent(e) {
				return 'This recently generated report has not been submitted or downloaded.';
			}
			
			YAHOO.util.Event.onDOMReady(setUpUnloadEvent);
		</script>
	</stripes:layout-component>
	<stripes:layout-component name="sidemenu">
		<stripes:layout-render 
                  name="/WEB-INF/jsp/csr/sidemenu.jsp"
			runReportPageCurrent="current" />
	</stripes:layout-component>
	<stripes:layout-component name="body">
		
		<stripes:useActionBean id="ranReportActionBean" 
				beanclass="com.kroger.pharmacy.csr.view.action.csr.RanReportBean" />
		<stripes:form action="/RanReport.action"
						name="ranReportForm" id="stripesForm">
			<div id="ranReportPane">
				<stripes:hidden name="submitForced" id="submitForced" />
				<input type="hidden" name="submitted" id="submitted" value="false" />
				<table>
					<caption>Report Details</caption>
					<tr>
						<td class="reportDetailLabel">State</td>
						<td class="reportDetailValue">${ranReportActionBean.selectedReport.stateReportingDetail.state.name}</td>
					</tr>
					<tr>
						<td class="reportDetailLabel">Filename</td>
						<td class="reportDetailValue">${ranReportActionBean.selectedReport.filename}</td>
					</tr>
					<tr>
						<td class="reportDetailLabel">Number of Records Reported</td>
						<td class="reportDetailValue" align="right">${ranReportActionBean.selectedReport.numRecords}</td>
					</tr>
					<tr>
						<td class="reportDetailLabel">Generation Date</td>
						<td class="reportDetailValue">${ranReportActionBean.selectedReport.formattedGenerationDateTime}</td>
					</tr>
					<tr>
						<td class="reportDetailLabel">Agency Submission Date</td>
						<td class="reportDetailValue">${ranReportActionBean.selectedReport.formattedAgencySubmissionDateTime}</td>
					</tr>
				</table>
				<br/>
				<div class="actionButtons" >
					<c:choose>
						<c:when test="${ranReportActionBean.automatedTransferSupported}">
							
								<c:choose>
									<c:when test="${! empty ranReportActionBean.selectedReport.agencySubmissionDate}" >
										<stripes:submit value="Resubmit Report to State Agency" name="transmit" onclick="document.body.style.cursor='wait';document.getElementById('submitted').value='true';window.onbeforeunload=null;" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />
									</c:when>
									<c:otherwise>
										<stripes:submit value="Submit Report to State Agency" name="transmit" onclick="document.body.style.cursor='wait';" onmouseover="buttonOver(this);document.getElementById('submitted').value='true';window.onbeforeunload=null;" onmouseout="buttonOut(this);" />
									</c:otherwise>
								</c:choose>
						</c:when>
						<c:otherwise>
							<c:if test="${empty ranReportActionBean.selectedReport.agencySubmissionDate}">
								<stripes:submit name="markAsTransmitted" value="Mark as Transmitted" onclick="document.body.style.cursor='wait';" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />
							</c:if>
						</c:otherwise>
					</c:choose>
					<c:if test="${ranReportActionBean.emailAlertSupported}">
						<stripes:submit name="transmit" value="Send Notification" onclick="document.body.style.cursor='wait';document.getElementById('submitted').value='true';window.onbeforeunload=null;" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />
					</c:if>
					<stripes:submit name="download" value="Download Report" onclick="document.getElementById('submitted').value='true';window.onbeforeunload=null;" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />
					<stripes:submit name="upload" value="Upload Edited Report" onclick="window.onbeforeunload=null;return confirm('Are you sure you want to replace file: ${ranReportActionBean.selectedReport.filename}?');" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />
					<stripes:submit name="delete" value="Delete Report" onclick="window.onbeforeunload=null;return confirm('Are you sure you want to delete ${ranReportActionBean.selectedReport.filename}?');" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />
				</div>
			</div>
			<stripes:wizard-fields/>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>