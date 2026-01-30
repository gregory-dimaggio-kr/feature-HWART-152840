package com.kroger.pharmacy.csr.view.action.csr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.apache.commons.lang.StringUtils;

import com.kroger.commons.calendar.FiscalDay;
import com.kroger.pharmacy.csr.domain.PartialRxExtract;
import com.kroger.pharmacy.csr.domain.StateReportingDetail;
import com.kroger.pharmacy.csr.service.ICsrService;
import com.kroger.pharmacy.csr.service.IRxExtractService;

@UrlBinding("/RxExtracts.action")
public class RxExtractsBean extends BaseCalendarSupportActionBean {
		
	private List<PartialRxExtract> rxExtracts;
	
	private List<StateReportingDetail> states;
	
	private IRxExtractService rxExtractService;
	
	private ICsrService csrService;
	
	private String beginDateRange;
	
	private String endDateRange;
	
	private Date beginDate;
	
	private Date endDate;
	
	private String rxNumber;
	
	private String facilityId;
	
	private String divisionId;
	
	private String stateCode;
	
	private boolean displayCorrected;
	
	private int scrollPosition;
	
	private Integer selectedExtractId;
	
	private Integer selectedIndex;
	
	protected IRxExtractService getRxExtractService() {
		return rxExtractService;
	}

	@SpringBean
	protected void setRxExtractService(IRxExtractService rxExtractService) {
		this.rxExtractService = rxExtractService;
	}
	
	@SpringBean
	protected void setCsrService(ICsrService csrService) {
		this.csrService = csrService;
	}

	public List<PartialRxExtract> getRxExtracts() {
		return rxExtracts;
	}

	public void setRxExtracts(List<PartialRxExtract> rxExtracts) {
		this.rxExtracts = rxExtracts;
	}

	public List<StateReportingDetail> getStates() {
		return states;
	}

	public void setStates(List<StateReportingDetail> states) {
		this.states = states;
	}

	public String getRxNumber() {
		return rxNumber;
	}

	public void setRxNumber(String rxNumber) {
		this.rxNumber = rxNumber;
	}

	public String getBeginDateRange() {
		return beginDateRange;
	}

	public void setBeginDateRange(String beginDateString) {
		this.beginDateRange = beginDateString;
	}

	public String getEndDateRange() {
		return endDateRange;
	}

	public void setEndDateRange(String endDateString) {
		this.endDateRange = endDateString;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	public boolean isDisplayCorrected() {
		return displayCorrected;
	}

	public void setDisplayCorrected(boolean displayCorrected) {
		this.displayCorrected = displayCorrected;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public int getScrollPosition() {
		return scrollPosition;
	}

	public void setScrollPosition(int scrollPosition) {
		this.scrollPosition = scrollPosition;
	}

	public boolean isScrolled() {
		return scrollPosition > 0;
	}
	
	public Integer getSelectedExtractId() {
		return selectedExtractId;
	}

	public void setSelectedExtractId(Integer selectedExtractId) {
		this.selectedExtractId = selectedExtractId;
	}

	public Integer getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(Integer selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	@DefaultHandler
	public Resolution defaultHandler() {
		return new ForwardResolution("/view/csr/rxextracts.jsp");
	}
	
	public Resolution search() {
		
//		rxExtracts = rxExtractService.searchRxExtracts(rxNumber, dispenseDate, 
//				divisionId, facilityId, stateCode, displayCorrected);
		scrollPosition = 0;
		selectedIndex = 0;
		ForwardResolution result = new ForwardResolution("/view/csr/rxextracts.jsp");
		if ( rxNumber != null)
			result.addParameter("rxNumber", rxNumber);
//		if ( dispenseDate != null )
//			result.addParameter("dispenseDate", dispenseDate.getTime());
		if ( facilityId != null)
			result.addParameter("facilityId", facilityId);
		if ( divisionId != null)
			result.addParameter("divisionId", divisionId);
		if ( stateCode != null)
			result.addParameter("stateCode", stateCode);
		if ( beginDateRange != null )
			result.addParameter("beginDateRange", beginDateRange);
		if ( endDateRange != null )
			result.addParameter("endDateRange", endDateRange);
		
		result.addParameter("displayCorrected", displayCorrected);
		return result;
	}
	
	public Resolution editExtract() {
		if ( selectedExtractId == null )
			return search();
		
		ForwardResolution result = new ForwardResolution("/view/csr/rxextractdetails.jsp");
		result.addParameter("extractId", selectedExtractId);
		if ( rxNumber != null)
			result.addParameter("rxNumber", rxNumber);
		if ( facilityId != null)
			result.addParameter("facilityId", facilityId);
		if ( divisionId != null)
			result.addParameter("divisionId", divisionId);
		if ( stateCode != null)
			result.addParameter("stateCode", stateCode);
		if ( beginDateRange != null )
			result.addParameter("beginDateRange", beginDateRange);
		if ( endDateRange != null )
			result.addParameter("endDateRange", endDateRange);

		result.addParameter("scrollPosition", scrollPosition);
		result.addParameter("displayCorrected", displayCorrected);
		return result;
	}
	
	@Before(stages = LifecycleStage.BindingAndValidation)
	public void init() {
		setBeginDateRangeObject(null);
		setEndDateRangeObject(null);
		
		rxNumber = getContext().getRequest().getParameter("rxNumber");
		facilityId = getContext().getRequest().getParameter("facilityId");
		divisionId = getContext().getRequest().getParameter("divisionId");
		stateCode = getContext().getRequest().getParameter("stateCode");
		beginDateRange = getContext().getRequest().getParameter("beginDateRange");
		endDateRange = getContext().getRequest().getParameter("endDateRange");
		String scrollString = getContext().getRequest().getParameter("scrollPosition");
		
		if ( beginDateRange != null && beginDateRange.length() > 0 )
			beginDate = parseDispenseDateString(beginDateRange);
		else
			beginDate = null;
		
		if ( endDateRange != null && endDateRange.length() > 0 )
			endDate = parseDispenseDateString(endDateRange);
		else
			endDate = null;
		
		String displayCorrectedString = getContext().getRequest().getParameter("displayCorrected");
		if ( displayCorrectedString != null && displayCorrectedString.length() > 0 )
			displayCorrected = Boolean.parseBoolean(displayCorrectedString);
		else
			displayCorrected = true;
		
		if ( rxNumber == null && beginDate == null && facilityId == null && stateCode == null )
			rxExtracts = new ArrayList<PartialRxExtract>();
		else {
			FiscalDay beginDay = (beginDate == null) ? null : new FiscalDay(beginDate);
			FiscalDay endDay = (endDate == null) ? null : new FiscalDay(endDate);
			int count = rxExtractService.countRxExtracts(rxNumber, beginDay, endDay, divisionId, facilityId, stateCode, displayCorrected);
			
			if ( count > 500 ) {
				getContext().saveInfoMessage("Too many search results returned.  Please add additional criteria and try again.");
				rxExtracts = new ArrayList<PartialRxExtract>();
			}
			else if ( count == 0 ) {
				rxExtracts = new ArrayList<PartialRxExtract>();
			}
			else {
				rxExtracts = rxExtractService.searchRxExtracts(rxNumber, beginDay, endDay, 
						divisionId, facilityId, stateCode, displayCorrected);
			}
		}
		
		if ( !StringUtils.isEmpty(scrollString) && scrollString.matches("^[0-9]*") )
			scrollPosition = Integer.parseInt(scrollString);
		
		if ( displayCorrected )
			rxExtracts = rxExtractService.populateChangedFlag(rxExtracts);
		
		states = csrService.getActiveStateReportingDetails();
	}
}
