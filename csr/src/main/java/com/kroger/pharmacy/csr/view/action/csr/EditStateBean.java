package com.kroger.pharmacy.csr.view.action.csr;

import java.util.List;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.kroger.commons.web.stripes.AbstractActionBean;
import com.kroger.pharmacy.csr.domain.StateReportingDetail;
import com.kroger.pharmacy.csr.domain.StateReportingEmailType;
import com.kroger.pharmacy.csr.domain.StateReportingMethod;
import com.kroger.pharmacy.csr.domain.StateReportingSchedule;
import com.kroger.pharmacy.csr.service.ICsrService;
import com.kroger.pharmacy.csr.service.IReportService;

@UrlBinding("/EditState.action")
public class EditStateBean extends AbstractActionBean {
	
	private StateReportingDetail stateReportingDetail;
	
	private List<StateReportingSchedule> schedules;
	
	private List<StateReportingMethod> methods;
	
	private Integer scheduleId;
	
	private Integer methodId;
	
	private String asapFormatId;
	
	private ICsrService csrService;
	
	private IReportService reportService;
	
	public List<StateReportingSchedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<StateReportingSchedule> schedules) {
		this.schedules = schedules;
	}

	@SpringBean
	protected void setCsrService(ICsrService csrService) {
		this.csrService = csrService;
	}

	@SpringBean
	protected void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}
	
	public StateReportingDetail getStateReportingDetail() {
		return stateReportingDetail;
	}

	public void setStateReportingDetail(StateReportingDetail stateReportingDetail) {
		this.stateReportingDetail = stateReportingDetail;
	}

	public List<StateReportingMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<StateReportingMethod> methods) {
		this.methods = methods;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getMethodId() {
		return methodId;
	}

	public void setMethodId(Integer methodId) {
		this.methodId = methodId;
	}

	public String getAsapFormatId() {
		return asapFormatId;
	}

	public void setAsapFormatId(String asapFormatId) {
		this.asapFormatId = asapFormatId;
	}

	@DefaultHandler
	public Resolution defaultHandler() {
		return new ForwardResolution("/view/csr/csrstatedetails.jsp");
	}
	
	public Resolution submit() {
		try{
			List<String> stateCodes = csrService.getUnusedStateCodes();
			if ( !stateCodes.contains(stateReportingDetail.getState().getCode()) ) {
				getContext().saveErrorMessage("State code " + stateReportingDetail.getState().getCode() + " not found.");
				return new ForwardResolution("/view/csr/csrstatelist.jsp");
			}
			
			/* Reporting Method was Manual by default. Changed it to take as input from the user with methodId */
			
			stateReportingDetail.setMethod(
				csrService.getStateReportingMethod(methodId));
		
			stateReportingDetail.setSchedule(
				csrService.getStateReportingSchedule(scheduleId));
			
			
//			CsrState csrState = stateReportingDetail.getCsrState();
//			csrState.setAsapFormat(asapFormatId);
//			csrService.persistCsrState(csrState);
			
			if ( stateReportingDetail.getEmailType() == null )
				stateReportingDetail.setEmailType(
						csrService.getStateReportingEmailType(
								StateReportingEmailType.NO_EMAIL_TYPE));
			
			stateReportingDetail = csrService.persistDetail(stateReportingDetail);
		}
		catch(Exception e){
			System.out.println("Exception in editing State: "+ e.getMessage());
		}
		
		return new ForwardResolution("/view/csr/csrstatelist.jsp");
		
	}
	
	public Resolution cancel() {
		return new ForwardResolution("/view/csr/csrstatelist.jsp");
	}
	
	@Before(stages = LifecycleStage.BindingAndValidation)
	public void init() {
		String detailId = getContext().getRequest().getParameter("detailId");
		if ( detailId != null && detailId.length() > 0 )
			stateReportingDetail = csrService.getStateReportingDetail(new Integer(detailId));
		
		if ( stateReportingDetail == null )
			stateReportingDetail = new StateReportingDetail();
		
		schedules = csrService.getStateReportingSchedules();
		methods = csrService.getStateReportingMethods();
		
		if ( scheduleId == null && stateReportingDetail.getSchedule() != null )
			scheduleId = stateReportingDetail.getSchedule().getId();
		if ( methodId == null && stateReportingDetail.getMethod() != null )
			methodId = stateReportingDetail.getMethod().getId();
		/*if ( asapFormatId == null && stateReportingDetail.getCsrState() != null )
			asapFormatId = stateReportingDetail.getCsrState().getAsapFormat();*/
	}
}
