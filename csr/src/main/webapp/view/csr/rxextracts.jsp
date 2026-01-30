<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
<%@ taglib uri="http://calendar.commons.kroger.com/tlds/taglib.tld"
		prefix="calendar"%>
<stripes:useActionBean id="rxExtractsActionBean" 
	beanclass="com.kroger.pharmacy.csr.view.action.csr.RxExtractsBean" />		
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="CSR Manager - Rx Extract Search" documentType="doc3"
	documentClass="yui-t2" navbarPhoneBookCurrent="current">
	<stripes:layout-component name="head">
		<script type="text/javascript" >
			function saveScrollPosition() {
				document.getElementById('scrollPosition').value = document.getElementById('extractView').scrollTop;
			}
			
			function saveIndex() {
				document.getElementById('selectedIndex').value = selectedIndex;
			}
			
			function createKeyListeners() {
				document.onkeydown = arrowScrollingKeyListener;
			}
			
			function initialFocus() {
				document.getElementById('extractView').focus();
			}
			
			function searchClick() {
				document.body.style.cursor='wait';
				document.getElementById('loadingPane').className='loadingPane';
				setTimeout('document.images["loadingImage"].src = "<c:url value="${styleUrl}/images/loading.gif"/>"', 10);
			}
			
			YAHOO.util.Event.onDOMReady(createKeyListeners);
			YAHOO.util.Event.onDOMReady(initialFocus);
			
			<c:if test="${rxExtractsActionBean.scrolled}">
				
				function setScrollPosition() {
					var scrollPos = <c:out value="${rxExtractsActionBean.scrollPosition}" />;
					document.getElementById('extractView').scrollTop=scrollPos;
				}
				
				YAHOO.util.Event.onDOMReady(setScrollPosition);
				
			</c:if>
			
			<c:if test="${! empty rxExtractsActionBean.selectedIndex}">
				
				function setSelectedRow() {
					var index = <c:out value="${rxExtractsActionBean.selectedIndex}" />
					selectRow(document.getElementById('extractRow'+index),index,'extractId','selectedId');
				}
				
				YAHOO.util.Event.onDOMReady(setSelectedRow);
				
			</c:if>
		</script>
	</stripes:layout-component>
	<stripes:layout-component name="sidemenu">
		<stripes:layout-render 
                  name="/WEB-INF/jsp/csr/sidemenu.jsp"
			rxExtractSearchPageCurrent="current" />
	</stripes:layout-component>
	<stripes:layout-component name="body">
		
		
		<stripes:form action="/RxExtracts.action"
						name="rxExtractsForm" id="stripesForm">
			<div id="extractSearchPane">
				<table width="100%">
					<caption>Rx Data Search</caption>
					<tbody>
						<tr>
							<td>
								<stripes:label for="rxNumber">Rx Number</stripes:label>
								<stripes:text name="rxNumber" />
							</td>
						</tr>
						<tr>
							<td>
								<stripes:label for="beginDateString">Fill Date Begin</stripes:label>
								<calendar:fiscalCalendar name="beginDateRange" calendarType="PERIOD" />
							</td>
						</tr>
						<tr>
							<td>
								<stripes:label for="endDateString">Fill Date End</stripes:label>
								<calendar:fiscalCalendar name="endDateRange" calendarType="PERIOD" />
							</td>
						</tr>
						<tr>
							<td>
								<stripes:label for="divisionId">Store Number</stripes:label>
								Div# <stripes:text name="divisionId" maxlength="3" size="4" value="014"/> Store# <stripes:text name="facilityId" maxlength="5" size="8" value="00915"/>
							</td>
						</tr>
						<tr>
							<td>
								<stripes:label for="stateCode">State</stripes:label>
								<stripes:select name="stateCode">
									<stripes:option value=""></stripes:option>
									<c:forEach items="${rxExtractsActionBean.states}" var="s">
										<stripes:option value="${s.state.code}">${s.state.name}</stripes:option>
									</c:forEach>
								</stripes:select>
							</td>
						</tr>
						<tr>
							<td>
								<stripes:label for="displayCorrected">Display Filter</stripes:label>
								<stripes:select name="displayCorrected">
									<stripes:option value="true">Display All Records</stripes:option>
									<stripes:option value="false">Hide Modified Records</stripes:option>
								</stripes:select>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="actionButtons" >
					<stripes:submit name="search" value="Search" id="searchButton" onclick="searchClick();" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />
					<div id="loadingPane" class="hidden" >
						Searching... <img id="loadingImage" <c:url value="${styleUrl}/images/loading.gif"/> />
					</div>
				</div>
			</div>
			<div></div>
			<div id="extractsListPane">
				<table width="700px">
					<caption>Matched Rx Data</caption>
					<thead>
						<tr>
							<th class="selectedColumn"></th>
							<th class="rxStateColumn">State</th>
							<th class="rxNumberColumn">Rx Number</th>
							<th class="rxFacilityColumn">Store Number</th>
							<th class="rxDispenseDateColumn">Fill Date</th>
							<th class="rxRefillColumn">Refill Number</th>
							<th class="rxNameColumn">Patient Name</th>
						</tr>
					</thead>
				</table>
				<div id="extractView" class="viewer" style="height: 200px; width: 700px; overflow-x: hidden; overflow-y: auto;" >
					<stripes:hidden name="selectedExtractId" id="selectedId" />
					<stripes:hidden name="selectedIndex" id="selectedIndex" />
					<stripes:hidden name="scrollPosition" id="scrollPosition" />
					<table class="extractTable" id="extractTable" width="700px">
						
						<tbody id="extractListResult">
							<c:forEach items="${rxExtractsActionBean.rxExtracts}" 
								var="extract" varStatus="loop" >
								
								<tr id="extractRow${loop.index}" <c:if test="${extract.changed}">class="changed"</c:if> onmouseover="listOver(this);" onmouseout="listOut(this);" onclick="selectRow(this,${loop.index},'extractId','selectedId');" >
										
									<td class="selectedColumn" >
										<input type="radio" id="selectedCheckbox${loop.index}" value="true" />
										<input type="hidden" id="extractId${loop.index}" value="${extract.extractKey}" /> 
									</td>	
									<td class="rxStateColumn" >
										${extract.facility.state.name}
									</td>
									<td class="rxNumberColumn" >
										${extract.rxNumber}
									</td>
									<td class="rxFacilityColumn" >
										${extract.facility.facilityId}
									</td>
									<td class="rxDispenseDateColumn" >
										${extract.formattedDispenseDate}
									</td>
									<td class="rxRefillColumn" >
										${extract.rxRefillNumber}
									</td>
									<td class="rxNameColumn" >
										${extract.patientLastName}, ${extract.patientFirstName}
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="actionButtons">
					<stripes:submit name="editExtract" id="editButton" value="Edit Rx Data" onclick="saveScrollPosition();saveIndex();" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />&nbsp;
				</div>
			</div>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>