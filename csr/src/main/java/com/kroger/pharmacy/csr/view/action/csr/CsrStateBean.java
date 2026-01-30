package com.kroger.pharmacy.csr.view.action.csr;

import java.util.List;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import com.kroger.commons.security.SecurityBean;
import com.kroger.commons.web.stripes.AbstractActionBean;
import com.kroger.pharmacy.csr.domain.Report;
import com.kroger.pharmacy.csr.domain.State;
import com.kroger.pharmacy.csr.domain.StateReportingDetail;
import com.kroger.pharmacy.csr.domain.StateReportingEmailType;
import com.kroger.pharmacy.csr.domain.StateReportingMethod;
import com.kroger.pharmacy.csr.domain.StateReportingSchedule;
import com.kroger.pharmacy.csr.service.ICsrService;
import com.kroger.pharmacy.csr.service.IReportService;

@UrlBinding("/CsrState.action")
public class CsrStateBean extends AbstractActionBean {
	private List<StateReportingDetail> stateReportingDetails;
	
	private List<State> states;
	
	private List<Report> reports;
	
	private List<StateReportingSchedule> schedules;
	
	private List<StateReportingMethod> methods;
	
	private List<StateReportingEmailType> emailTypes;
	
	private ICsrService csrService;
	
	private IReportService reportService;
	
	private Integer selectedDetailId;
	
	private String username;
	
	private String sortColumn;
	
	@SpringBean
	protected void setCsrService(ICsrService csrService) {
		this.csrService = csrService;
	}

	@SpringBean
	protected void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

	public List<StateReportingDetail> getStateReportingDetails() {
		return stateReportingDetails;
	}

	public void setStateReportingDetails(
			List<StateReportingDetail> stateReportingDetails) {
		this.stateReportingDetails = stateReportingDetails;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
	
	public List<StateReportingSchedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<StateReportingSchedule> schedules) {
		this.schedules = schedules;
	}

	public List<StateReportingMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<StateReportingMethod> methods) {
		this.methods = methods;
	}

	public List<StateReportingEmailType> getEmailTypes() {
		return emailTypes;
	}

	public void setEmailTypes(List<StateReportingEmailType> emailTypes) {
		this.emailTypes = emailTypes;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getSelectedDetailId() {
		return selectedDetailId;
	}

	public void setSelectedDetailId(Integer selectedDetailId) {
		this.selectedDetailId = selectedDetailId;
	}

	@DefaultHandler
	public Resolution defaultHandler() {
//		if ( sortColumn == null )
//			sortColumn = "state.name";
//		
//		stateReportingDetails = csrService.getStateReportingDetails(sortColumn);
//		stateReportingDetails = reportService.populateLatestReports(stateReportingDetails);
		
		return new ForwardResolution("/view/csr/csrstatelist.jsp");
	}
	
	public Resolution noSelectionMade() {
		getContext().saveInfoMessage("Please select a state.");
		return defaultHandler();
	}
	
	public Resolution addState() {
		return new ForwardResolution("/view/csr/csrstatedetails.jsp");
	}
	
	public Resolution editState() {
		if ( selectedDetailId == null )
			return noSelectionMade();
		
		ForwardResolution resolution =  new ForwardResolution("/view/csr/csrstatedetails.jsp");
		resolution.addParameter("detailId", selectedDetailId);
		return resolution;
	}
	
	public Resolution deleteState() {
		if ( selectedDetailId == null )
			return noSelectionMade();
		
		StateReportingDetail stateReportingDetail = csrService.getStateReportingDetail(selectedDetailId);
		
		if ( reportService.getReports(stateReportingDetail).size() > 0 ) {
			getContext().saveErrorMessage("Cannot delete a state that has reports.  Please delete all associated reports first.");
			return defaultHandler();
		}
		if ( csrService.getContacts(stateReportingDetail).size() > 0 ) {
			getContext().saveErrorMessage("Cannot delete a state that has contacts.  Please delete all associated contacts first.");
			return defaultHandler();
		}
		
		csrService.removeDetail(stateReportingDetail);
		return defaultHandler();
	}
	
	public Resolution viewContacts() {
		if ( selectedDetailId == null )
			return noSelectionMade();
		
		ForwardResolution resolution =  new ForwardResolution("/view/csr/csrcontacts.jsp");
		resolution.addParameter("detailId", selectedDetailId);
		return resolution;
	}
	
	public Resolution sortList() {
		return defaultHandler();
	}
	
	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	@Before(stages = LifecycleStage.BindingAndValidation)
	public void init() {
		try{
			sortColumn = getContext().getRequest().getParameter("sortColumn");
			if ( sortColumn == null || sortColumn.length() == 0 )	{
				stateReportingDetails = csrService.getStateReportingDetails();
			}
			else {
				stateReportingDetails = csrService.getStateReportingDetails(sortColumn);
			}
			
			stateReportingDetails = reportService.populateLatestReports(stateReportingDetails);
			
			username = new SecurityBean().getUsername();
		}
		catch(Exception e){
			System.out.println("Exception: "+ e.toString());
			
		}
		
	}
	
	/*public DataTable createStateReportingDetailDataTable(List<StateReportingDetail> stateReportingDetails) {
		DataTable result = new DataTable("stateReportingDetailDataTable", stateReportingDetails);
		
		result.addReadOnlyColumn("State", "state.name");
		result.addCheckboxColumn("Active", "active");
		
		return result;
	}*/
}
