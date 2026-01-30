package com.kroger.pharmacy.csr.view.action.csr;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.kroger.pharmacy.csr.domain.StateReportingDetail;
import com.kroger.pharmacy.csr.service.ICsrService;
import com.kroger.pharmacy.csr.service.ILookupService;
import com.techrx.app.trexone.batch.controlledsubstancereporting.CSRRemoteService;

@UrlBinding("/RunReport.action")
public class RunReportBean extends BaseCalendarSupportActionBean {
	
	private StateReportingDetail selectedState;
	
	private ILookupService lookupService;
	
	private ICsrService csrService;
	
	private List<StateReportingDetail> states;
	
	private Date beginDateRange;
	
	private Date endDateRange;
	
	private String stateCode;
	
	private boolean changedExtractsOnly;
	
	public StateReportingDetail getSelectedState() {
		return selectedState;
	}

	public void setSelectedState(StateReportingDetail selectedState) {
		this.selectedState = selectedState;
	}

//	@SpringBean
//	protected void setReportService(IReportService reportService) {
//		this.reportService = reportService;
//	}

	@SpringBean
	protected void setCsrService(ICsrService csrService) {
		this.csrService = csrService;
	}
	
	@SpringBean
	protected void setLookupService(ILookupService lookupService) {
		this.lookupService = lookupService;
	}
	
	public List<StateReportingDetail> getStates() {
		return states;
	}

	public void setStates(List<StateReportingDetail> states) {
		this.states = states;
	}
	
	public Date getBeginDateRange() {
		return beginDateRange;
	}
	
	public void setBeginDateRange(Date startDate) {
		this.beginDateRange = startDate;
	}

	public Date getEndDateRange() {
		return endDateRange;
	}

	public void setEndDateRange(Date endDate) {
		this.endDateRange = endDate;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public boolean isChangedExtractsOnly() {
		return changedExtractsOnly;
	}

	public void setChangedExtractsOnly(boolean changedExtractsOnly) {
		this.changedExtractsOnly = changedExtractsOnly;
	}

	@DefaultHandler
	public Resolution runReportScreen() {
		return new ForwardResolution("/view/csr/runreport.jsp");
	}

	public Resolution run() {
		CSRRemoteService remoteService = null;
		Integer newReportId = null;
		
		String errorMessage = "";
		
		try {
			remoteService = lookupService.lookupCSRRemoteService();
			
			if ( beginDateRange == null || endDateRange == null ) {
				// don't allow a date range with only one date
				beginDateRange = null;
				endDateRange = null;
			}
				
			newReportId = remoteService.runReport(getStateCode(), 
					getBeginDateRange(), getEndDateRange(), isChangedExtractsOnly());
			
		} catch (Exception e) {
			errorMessage = e.getMessage();
			e.printStackTrace();
		}
		
		if ( newReportId != null ) {
			ForwardResolution result = new ForwardResolution("/view/csr/ranreport.jsp");
			result.addParameter("reportId", newReportId);
			result.addParameter("submitForced", true);
			return result;
		}
		else if ( errorMessage.length() == 0 ) {
			getContext().saveErrorMessage("No extract records matched reporting criteria.");
		}
		else {
			getContext().saveErrorMessage("Report generation failed. " + errorMessage);
		}
		
		return new RedirectResolution("/view/csr/runreport.jsp");
	}
	
	public Resolution calculateDateRange() {
		JSONObject json = new JSONObject();
		
		if ( stateCode != null ) {
			StateReportingDetail stateReportingDetail = csrService.getStateReportingDetail(stateCode);
			Date[] interval = csrService.getLastReportingInterval(stateReportingDetail);
			
			if ( interval.length == 2 && interval[0] != null && interval[1] != null ) {
				json.put("startDate", DATE_FORMAT.format(interval[0]));
				json.put("endDate", DATE_FORMAT.format(interval[1]));
			}
		}
		
		return new StreamingResolution("text/json", json.toString());
	}
	
	public String getFormattedBeginDate() {
		if ( beginDateRange == null )
			return null;
		
		return DATE_FORMAT.format(beginDateRange);
	}
	
	public String getFormattedEndDate() {
		if ( endDateRange == null )
			return null;
		
		return DATE_FORMAT.format(endDateRange);
	}
		
	@Before(stages = LifecycleStage.BindingAndValidation)
	public void init() {
		states = csrService.getActiveStateReportingDetails();
		
		String stateId = getContext().getRequest().getParameter("stateId");
		
		if ( stateId != null && stateId.length() > 0 )
			selectedState = csrService.getStateReportingDetail(new Integer(stateId));
		else
			selectedState = states.get(0);		
		
		
		if ( beginDateRange == null && endDateRange == null ) {
			Date[] reportingInterval = csrService.getLastReportingInterval(selectedState);
			beginDateRange = reportingInterval[0];
			endDateRange = reportingInterval[1];
		}
	}
	
	
}
