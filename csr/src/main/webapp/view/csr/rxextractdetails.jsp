<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
<%@ taglib uri="http://calendar.commons.kroger.com/tlds/taglib.tld"
	prefix="calendar"%>
<stripes:useActionBean id="rxTabBean" 
	beanclass="com.kroger.pharmacy.csr.view.action.csr.EditRxTabBean" />
			
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="CSR Manager - Edit Rx Extract" documentType="doc3"
	documentClass="yui-t2" navbarPhoneBookCurrent="current">
	<stripes:layout-component name="head">
		<script type="text/javascript">
			<!--
				function switchTab(visibleTab) {
					var allTabs = new Array('generalRxInfo', 'facilityInfo', 'prescriberInfo', 'patientInfo');
					var allTabButtons = new Array('rxInfoButton', 'facilityInfoButton', 'prescriberInfoButton', 'patientInfoButton');
					var i;
					var index;
					
					for ( i in allTabs ) {
						document.getElementById(allTabs[i]).className = 'visibleDiv hidden';
						document.getElementById(allTabButtons[i]).className = 'inactiveTab';
						
						if ( allTabs[i] == visibleTab )
							index = i;
					}
					
					document.getElementById(visibleTab).className = 'visibleDiv';
					document.getElementById(allTabButtons[index]).className = 'activeTab';
					saveTab(document.getElementById('stripesForm').action,visibleTab);
				}
				
				<c:if test="${! empty rxTabBean.currentTabName}" >
					function switchOnLoad() {
						switchTab('${rxTabBean.currentTabName}');
					}
					
					YAHOO.util.Event.onDOMReady(switchOnLoad);
				</c:if>
				
				var rxInfoButton = new YAHOO.widget.Button('rxInfoButton');
				var facilityInfoButton = new YAHOO.widget.Button('facilityInfoButton');
				var prescriberInfoButton = new YAHOO.widget.Button('prescriberInfoButton');
				var patientInfoButton = new YAHOO.widget.Button('patientInfoButton');
				
			//-->
		</script>
	</stripes:layout-component>
	<stripes:layout-component name="sidemenu">
		<stripes:layout-render 
                  name="/WEB-INF/jsp/csr/sidemenu.jsp"
			rxExtractSearchPageCurrent="current" />
	</stripes:layout-component>
	<stripes:layout-component name="body">
		<stripes:useActionBean id="editRxExtractActionBean" 
				beanclass="com.kroger.pharmacy.csr.view.action.csr.EditRxExtractBean" />
		<stripes:form action="/EditRxExtracts.action"
						name="editRxExtractForm" id="stripesForm">
			<div id="rxExtractDetailsPane">
				<div id="rxExtractView" >
					<div id="headerDiv">
						<b>Rx Number - ${editRxExtractActionBean.rxExtract.rxNumber}</b>
					</div>
					<div id="tabSelector">
						<input type="button" id="rxInfoButton" class="activeTab" value="General Rx Info" onclick="switchTab('generalRxInfo');" />
						<input type="button" id="facilityInfoButton" class="inactiveTab" value="Facility Info" onclick="switchTab('facilityInfo');" />
						<input type="button" id="prescriberInfoButton" class="inactiveTab" value="Prescriber Info" onclick="switchTab('prescriberInfo');" />
						<input type="button" id="patientInfoButton" class="inactiveTab" value="Patient Info" onclick="switchTab('patientInfo');" />
					</div>
					<div id="headerSeparator">
						<br />
					</div>
					<div id="generalRxInfo" class="visibleDiv">
						<table width="100%">
							<caption>General Rx Info</caption>
							<tr>
								<td>
								    <!-- DSP03 -->
									<stripes:label for="rxPrescribedDate">Written Date</stripes:label>
									<calendar:fiscalCalendar name="rxPrescribedDate" value="${editRxExtractActionBean.formattedRxPrescribedDate}" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- DSP05 -->
									<stripes:label for="rxDispenseDate">Dispensing/Verification Date</stripes:label>
									<calendar:fiscalCalendar name="rxDispenseDate" value="${editRxExtractActionBean.formattedRxDispenseDate}" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- DSP17 -->
									<stripes:label for="approvalDate">Sold to Patient</stripes:label>
									<calendar:fiscalCalendar name="approvalDate" value="${editRxExtractActionBean.formattedApprovalDate}" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- DSP08 -->
									<stripes:label for="rxExtract.productNdc">Product NDC</stripes:label>
									<stripes:text name="rxExtract.productNdc" onkeypress="return forceDigits(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- DSP09 -->
									<stripes:label for="rxExtract.rxMetricQuantity">Metric Quantity</stripes:label>
									<stripes:text name="rxExtract.rxMetricQuantity" onkeypress="return forceDigits(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- DSP06 -->
									<stripes:label for="rxExtract.rxRefillNumber">Refill Number</stripes:label>
									<stripes:text name="rxExtract.rxRefillNumber" onkeypress="return forceDigits(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- DSP04 -->
									<stripes:label for="rxExtract.rxRefillsAuthorized">Refills Authorized</stripes:label>
									<stripes:text name="rxExtract.rxRefillsAuthorized" onkeypress="return forceDigits(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- DSP10 -->
									<stripes:label for="rxExtract.rxDaysSupply">Days Supply</stripes:label>
									<stripes:text name="rxExtract.rxDaysSupply" onkeypress="return forceDigits(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- DSP24 -->
									<stripes:label for="rxExtract.treatmentType">Treatment Type</stripes:label>
									<stripes:select name="rxExtract.treatmentType">
										<stripes:option value=""></stripes:option>
										<stripes:option value="01">1 - Not used for opioid dependency treatment</stripes:option>
										<stripes:option value="02">2 - Used for opioid dependency treatment</stripes:option>
										<stripes:option value="03">3 - Pain associated with active and aftercare cancer treatment</stripes:option>
										<stripes:option value="04">4 - Palliative care in conjunction with a serious illness</stripes:option>
										<stripes:option value="05">5 - End-of-life and hospice care</stripes:option>
										<stripes:option value="06">6 - A pregnant individual with a pre-existing prescription for opioids</stripes:option>
										<stripes:option value="07">7 - Acute pain for an individual with an existing opioid prescription for chronic pain</stripes:option>
										<stripes:option value="08">8 - Individuals pursuing an active taper of opioid medications</stripes:option>
										<stripes:option value="09">9 - Patient is participating in a pain management contract</stripes:option>
										<stripes:option value="10">10 - Acute Opioid Therapy</stripes:option>
										<stripes:option value="11">11 - Chronic Opioid Therapy</stripes:option>
										<stripes:option value="99">99 - Other</stripes:option>
									</stripes:select>
								</td>
							</tr>
							<tr>
							    <td>
							        <!-- DSP23 -->
									<stripes:label for="rxExtract.directions">SIG (directions)</stripes:label>
    								<stripes:textarea cols="50" rows="10" name="rxExtract.directions" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
							    </td>
							</tr>
							<tr>
								<td>
								    <!-- DSP25 -->
									<stripes:label for="rxExtract.rxDiagnosisCode">Diagnosis Code</stripes:label>
									<stripes:text name="rxExtract.rxDiagnosisCode" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- DSP16 -->
									<stripes:label for="rxExtract.rxPaymentCode">Payment Code</stripes:label>
									<stripes:select name="rxExtract.rxPaymentCode">
										<stripes:option value=""></stripes:option>
										<stripes:option value="1">1 - Private Pay (Cash)</stripes:option>
										<stripes:option value="2">2 - Medicaid</stripes:option>
										<stripes:option value="3">3 - Medicare</stripes:option>
										<stripes:option value="4">4 - Commercial Insurance</stripes:option>
										<stripes:option value="5">5 - Military Installations and VA</stripes:option>
										<stripes:option value="6">6 - Workers' Compensation</stripes:option>
										<stripes:option value="7">7 - Indian Nations</stripes:option>
										<stripes:option value="99">99 - Other</stripes:option>
									</stripes:select>
								</td>
							</tr>
							<tr>
								<td>
									<!-- DSP31 -->
									<stripes:label for="rxExtract.discountCardPrimary">Discount Card</stripes:label>
									<stripes:select name="rxExtract.discountCardPrimary">
										<stripes:option value=""></stripes:option>
										<stripes:option value="1">1 - yes</stripes:option>
										<stripes:option value="2">2 - no</stripes:option>
									</stripes:select>
								</td>
							</tr>
							<tr>
								<td>
									<!-- DSP33 -->
									<stripes:label for="rxExtract.discountCardSecondary">Discount Card for Additional Payment Type</stripes:label>
									<stripes:select name="rxExtract.discountCardSecondary">
										<stripes:option value=""></stripes:option>
										<stripes:option value="1">1 - yes</stripes:option>
										<stripes:option value="2">2 - no</stripes:option>
									</stripes:select>
								</td>
							</tr>
							<tr>
								<td>
								    <!-- AIR02 -->
									<stripes:label for="rxExtract.rxTriplicateSerialNumber">State Issued Rx Serial Number</stripes:label>
									<stripes:text name="rxExtract.rxTriplicateSerialNumber" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- AIR01 (same as PRE10) -->
									<stripes:label for="rxExtract.prescriberState">State Code for State Rx Serial Number</stripes:label>
								    <stripes:select name="rxExtract.prescriberState">
									    <stripes:option value=""></stripes:option>
									    <c:forEach items="${rxTabBean.states}" var="s">
										    <stripes:option value="${s.state.code}">${s.state.name}</stripes:option>
									    </c:forEach>
    								</stripes:select>
								</td>
							</tr>
							<tr>
								<td>
								    <!-- DSP13 -->
									<stripes:label for="rxExtract.partialFillInd">Partial Fill Indicator</stripes:label>
									<stripes:text name="rxExtract.partialFillInd" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- DSP11 -->
									<stripes:label for="rxExtract.rxDosageUnit">Dosage Unit</stripes:label>
									<stripes:select name="rxExtract.rxDosageUnit" >
										<stripes:option value=""></stripes:option>
										<stripes:option value="EA">each (EA)</stripes:option>
										<stripes:option value="GM">grams (GM)</stripes:option>
										<stripes:option value="ML">milliliters (ML)</stripes:option>
									</stripes:select>
								</td>
							</tr>
							<tr>
								<td>
								    <!-- AIR10 -->
									<stripes:label for="rxExtract.pharmacistFirstName">Pharmacist First Name</stripes:label>
									<stripes:text name="rxExtract.pharmacistFirstName" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- AIR09 -->
									<stripes:label for="rxExtract.pharmacistLastName">Pharmacist Last Name</stripes:label>
									<stripes:text name="rxExtract.pharmacistLastName" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- AIR03 -->
									<stripes:label for="rxExtract.pickupPersonIdIssueState">Person Picking Up: ID issuing jurisdiction</stripes:label>
								    <stripes:select name="rxExtract.pickupPersonIdIssueState">
									    <stripes:option value=""></stripes:option>
									    <c:forEach items="${rxTabBean.states}" var="s">
										    <stripes:option value="${s.state.code}">${s.state.name}</stripes:option>
									    </c:forEach>
    								</stripes:select>
								</td>
							</tr>
							<tr>
								<td>
								    <!-- AIR04 -->
									<stripes:label for="rxExtract.pickupPersonIdType">Person Picking Up: ID qualifier</stripes:label>
									<stripes:select name="rxExtract.pickupPersonIdType">
										<stripes:option value=""></stripes:option>
										<stripes:option value="01">1 - Military ID</stripes:option>
										<stripes:option value="02">2 - State Issued ID</stripes:option>
										<stripes:option value="03">3 - Unique System ID</stripes:option>
										<stripes:option value="04">4 - Permanent Resident Card (Green  Card)</stripes:option>
										<stripes:option value="05">5 - Passport ID</stripes:option>
										<stripes:option value="06">6 - Driver's License ID</stripes:option>
										<stripes:option value="07">7 - Social Security Number</stripes:option>
										<stripes:option value="08">8 - Tribal ID</stripes:option>
										<stripes:option value="09">9 - Vendor Specific (such as Appriss Health, Experian, LexisNexis)</stripes:option>
										<stripes:option value="10">10 - Veterinary Patient Microchip Number</stripes:option>
										<stripes:option value="99">99 - Other (trading partner agreed upon ID such as cardholder ID)</stripes:option>
									</stripes:select>
								</td>
							</tr>
							<tr>
								<td>
								    <!-- AIR05 -->
									<stripes:label for="rxExtract.pickupPersonIdNumber">Person Picking Up: ID #</stripes:label>
									<stripes:text name="rxExtract.pickupPersonIdNumber" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- AIR06 -->
									<stripes:label for="rxExtract.pickupPersonRelationship">Person Picking Up: Relationship</stripes:label>
									<stripes:select name="rxExtract.pickupPersonRelationship">
										<stripes:option value=""></stripes:option>
										<stripes:option value="01">1 - Patient</stripes:option>
										<stripes:option value="02">2 - Parent/Legal Guardian</stripes:option>
										<stripes:option value="03">3 - Spouse</stripes:option>
										<stripes:option value="04">4 - Caregiver</stripes:option>
										<stripes:option value="99">99 - Other</stripes:option>
									</stripes:select>
								</td>
							</tr>
							<tr>
								<td>
								    <!-- AIR08 -->
									<stripes:label for="rxExtract.pickupPersonFirstName">Person Picking Up: First Name</stripes:label>
									<stripes:text name="rxExtract.pickupPersonFirstName" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- AIR07 -->
									<stripes:label for="rxExtract.pickupPersonLastName">Person Picking Up: Last Name</stripes:label>
									<stripes:text name="rxExtract.pickupPersonLastName" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
						</table>
					</div>
					<div id="facilityInfo" class="visibleDiv hidden">
						<table width="100%">
							<caption>Facility Info</caption>
							<tr>
								<td>
								    <!-- not currently mapped to a segment -->
									<stripes:label for="rxExtract.facilityName">Facility Name</stripes:label>
									<stripes:text name="rxExtract.facilityName" disabled />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PHA03 -->
									<stripes:label for="rxExtract.facilityDeaNumber">Facility DEA</stripes:label>
									<stripes:text name="rxExtract.facilityDeaNumber" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PHA02 -->
									<stripes:label for="rxExtract.facilityNabpNumber">Facility NABP</stripes:label>
									<stripes:text name="rxExtract.facilityNabpNumber" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PHA01 -->
									<stripes:label for="rxExtract.facilityNpiNumber">Facility NPI</stripes:label>
									<stripes:text name="rxExtract.facilityNpiNumber" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PHA13 -->
									<stripes:label for="rxExtract.facilityStateLicenseNumber">Facility State License #</stripes:label>
									<stripes:text name="rxExtract.facilityStateLicenseNumber" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- not currently mapped to a segment -->
								    <stripes:label for="rxExtract.facilityState">Facility State</stripes:label>
								    <stripes:select name="rxExtract.facilityState">
									    <stripes:option value=""></stripes:option>
									    <c:forEach items="${rxTabBean.states}" var="s">
										    <stripes:option value="${s.state.code}">${s.state.name}</stripes:option>
									    </c:forEach>
    								</stripes:select>
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PHA10 -->
									<stripes:label for="rxExtract.facilityPhoneNumber">Facility Phone #</stripes:label>
									<stripes:text name="rxExtract.facilityPhoneNumber" onkeypress="return forceDigits(event);" onchange="trimField(this);" />
								</td>
							</tr>
						</table>
					</div>
					<div id="prescriberInfo" class="visibleDiv hidden">
						<table width="100%">
							<caption>Prescriber Info</caption>
							<tr>
								<td>
								    <!-- PRE06 -->
									<stripes:label for="rxExtract.prescriberFirstName">Prescriber First Name</stripes:label>
									<stripes:text name="rxExtract.prescriberFirstName" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PRE07 -->
									<stripes:label for="rxExtract.prescriberMiddleName">Prescriber Middle Name</stripes:label>
									<stripes:text name="rxExtract.prescriberMiddleName" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PRE05 -->
									<stripes:label for="rxExtract.prescriberLastName">Prescriber Last Name</stripes:label>
									<stripes:text name="rxExtract.prescriberLastName" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PRE02 -->
									<stripes:label for="rxExtract.prescriberDeaNumber">Prescriber DEA</stripes:label>
									<stripes:text name="rxExtract.prescriberDeaNumber" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PRE03 -->
									<stripes:label for="rxExtract.prescriberDeaSuffix">Prescriber DEA Suffix</stripes:label>
									<stripes:text name="rxExtract.prescriberDeaSuffix" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PRE01 -->
									<stripes:label for="rxExtract.prescriberNpiNumber">Prescriber NPI</stripes:label>
									<stripes:text name="rxExtract.prescriberNpiNumber" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PRE09 -->
									<stripes:label for="rxExtract.prescriberXDEA">Prescriber X-DEA</stripes:label>
									<stripes:text name="rxExtract.prescriberXDEA" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PRE04 -->
									<stripes:label for="rxExtract.prescriberStateLicenseNumber">Prescriber State License #</stripes:label>
									<stripes:text name="rxExtract.prescriberStateLicenseNumber" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- not currently mapped to a segment -->
									<stripes:label for="rxExtract.prescriberStateIssuedId">Prescriber State Issued ID</stripes:label>
									<stripes:text name="rxExtract.prescriberStateIssuedId" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PRE08 -->
									<stripes:label for="rxExtract.prescriberPhoneNumber">Prescriber Phone Number</stripes:label>
									<stripes:text name="rxExtract.prescriberPhoneNumber" onkeyup="removeNonDigits(this);" onchange="trimField(this);" />
								</td>
							</tr>
						</table>
					</div>
					<div id="patientInfo" class="visibleDiv hidden">
						<table width="100%">
							<caption>Patient Info</caption>
							<tr>
								<td>
								    <!-- PAT10 -->
									<stripes:label for="rxExtract.patientNamePrefix">Patient Name Prefix</stripes:label>
									<stripes:text name="rxExtract.patientNamePrefix" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PAT08 -->
									<stripes:label for="rxExtract.patientFirstName">Patient First Name</stripes:label>
									<stripes:text name="rxExtract.patientFirstName" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PAT09 -->
									<stripes:label for="rxExtract.patientMiddleName">Patient Middle Name</stripes:label>
									<stripes:text name="rxExtract.patientMiddleName" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PAT07 -->
									<stripes:label for="rxExtract.patientLastName">Patient Last Name</stripes:label>
									<stripes:text name="rxExtract.patientLastName" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
 							<tr>
								<td>
								    <!-- PAT11 -->
									<stripes:label for="rxExtract.patientNameSuffix">Patient Name Suffix</stripes:label>
									<stripes:text name="rxExtract.patientNameSuffix" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PAT19 -->
									<stripes:label for="rxExtract.patientGender">Patient Gender</stripes:label>
									<stripes:select name="rxExtract.patientGender" >
										<stripes:option value=""></stripes:option>
										<stripes:option value="M">Male</stripes:option>
										<stripes:option value="F">Female</stripes:option>
										<stripes:option value="U">Other</stripes:option>
									</stripes:select>
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PAT03 possibility -->
									<stripes:label for="rxExtract.patientSsn">Patient SSN</stripes:label>
									<stripes:text name="rxExtract.patientSsn" onkeypress="return forceDigits(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PAT03 possibility -->
									<stripes:label for="rxExtract.patientDriversLicenseNumber">Patient Driver's License #</stripes:label>
									<stripes:text name="rxExtract.patientDriversLicenseNumber" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- not currently mapped to a segment -->
								    <stripes:label for="rxExtract.patientDriversLicenseState">Patient Driver's License State</stripes:label>
								    <stripes:select name="rxExtract.patientDriversLicenseState">
									    <stripes:option value=""></stripes:option>
									    <c:forEach items="${rxTabBean.states}" var="s">
										    <stripes:option value="${s.state.code}">${s.state.name}</stripes:option>
									    </c:forEach>
    								</stripes:select>
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PAT03 possibility -->
									<stripes:label for="rxExtract.patientNum">Patient Num</stripes:label>
									<stripes:text name="rxExtract.patientNum" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- not currently mapped to a segment -->
									<stripes:label for="rxExtract.patientMilitaryId">Patient Military ID</stripes:label>
									<stripes:text name="rxExtract.patientMilitaryId" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PAT18 -->
									<stripes:label for="patientBirthDate">Patient Birth Date</stripes:label>
									<calendar:fiscalCalendar name="patientBirthDate" value="${editRxExtractActionBean.formattedPatientBirthDate}" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PAT21 -->
									<stripes:label for="rxExtract.patientLocationCode">Patient Location Code</stripes:label>
									<stripes:select name="rxExtract.patientLocationCode">
										<stripes:option value=""></stripes:option>
										<stripes:option value="01">01-Home</stripes:option>
										<stripes:option value="02">02-Intermediary Care</stripes:option>
										<stripes:option value="03">03-Nursing Home</stripes:option>
										<stripes:option value="04">04-Long-Term/Extended Care</stripes:option>
										<stripes:option value="05">05-Rest Home</stripes:option>
										<stripes:option value="06">06-Boarding Home</stripes:option>
										<stripes:option value="07">07-Skilled-Care Facility</stripes:option>
										<stripes:option value="08">08-Sub-Acute Care Facility</stripes:option>
										<stripes:option value="09">09-Acute-Care Facility</stripes:option>
										<stripes:option value="10">10-Outpatient</stripes:option>
										<stripes:option value="11">11-Hospice</stripes:option>
										<stripes:option value="98">98-Unknown</stripes:option>
										<stripes:option value="99">99-Other</stripes:option>
									</stripes:select>
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PAT20 -->
									<stripes:label for="rxExtract.patientSpeciesCode">Patient Species Code</stripes:label>
									<stripes:select name="rxExtract.patientSpeciesCode">
										<stripes:option value=""></stripes:option>
										<stripes:option value="1">1-Human</stripes:option>
										<stripes:option value="2">2-Veterinary Patient</stripes:option>
									</stripes:select>
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PAT12 -->
									<stripes:label for="rxExtract.patientAddress1">Patient Address 1</stripes:label>
									<stripes:text name="rxExtract.patientAddress1" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PAT13 -->
									<stripes:label for="rxExtract.patientAddress2">Patient Address 2</stripes:label>
									<stripes:text name="rxExtract.patientAddress2" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PAT14 -->
									<stripes:label for="rxExtract.patientCity">Patient City</stripes:label>
									<stripes:text name="rxExtract.patientCity" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PAT15 -->
								    <stripes:label for="rxExtract.patientState">Patient State</stripes:label>
								    <stripes:select name="rxExtract.patientState">
									    <stripes:option value=""></stripes:option>
									    <c:forEach items="${rxTabBean.states}" var="s">
										    <stripes:option value="${s.state.code}">${s.state.name}</stripes:option>
									    </c:forEach>
    								</stripes:select>
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PAT16 -->
									<stripes:label for="rxExtract.patientZipCode">Patient ZIP Code</stripes:label>
									<stripes:text name="rxExtract.patientZipCode" onkeypress="return forceDigits(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PAT17 -->
									<stripes:label for="rxExtract.patientPhoneNumber">Patient Phone Number</stripes:label>
									<stripes:text name="rxExtract.patientPhoneNumber" onkeypress="return forceDigits(event);" onchange="trimField(this);" />
								</td>
							</tr>
							<tr>
								<td>
								    <!-- PAT23 -->
									<stripes:label for="rxExtract.nameOfAnimal">Name of Animal</stripes:label>
									<stripes:text name="rxExtract.nameOfAnimal" onkeyup="convertToCaps(this);" onkeypress="return forceCaps(event);" onchange="trimField(this);" />
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="footerSeparator">
					<br />
				</div>
				<div class="actionButtons">
					<stripes:submit name="submit" value="Submit" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />&nbsp;
					<stripes:submit name="cancel" value="Cancel" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />
				</div>
			</div>
			<stripes:hidden name="scrollPosition" />
			<stripes:wizard-fields/>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>
