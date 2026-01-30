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
import com.kroger.pharmacy.csr.domain.Report;
import com.kroger.pharmacy.csr.domain.StateReportingDetail;
import com.kroger.pharmacy.csr.service.ICsrService;
import com.kroger.pharmacy.csr.service.IReportService;

@UrlBinding("/CsrReports.action")
public class CsrReportsBean extends AbstractActionBean {
	private StateReportingDetail stateReportingDetail;
	
	private List<Report> reports;
	
	private ICsrService csrService;
	
	private IReportService reportService;
	
	private Integer selectedReportId;
	
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

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public Integer getSelectedReportId() {
		return selectedReportId;
	}

	public void setSelectedReportId(Integer selectedReportId) {
		this.selectedReportId = selectedReportId;
	}

	@DefaultHandler
	public Resolution defaultHandler() {
		return new ForwardResolution("/view/csr/csrreports.jsp");
	}
	
	public Resolution noSelectionMade() {
		getContext().saveInfoMessage("Please select a report.");
		return defaultHandler();
	}
	
	public Resolution viewReport() {
		if ( selectedReportId == null )
			return noSelectionMade();
		
		ForwardResolution resolution =  new ForwardResolution("/view/csr/ranreport.jsp");
		resolution.addParameter("reportId", selectedReportId);
		return resolution;
	}
	
	@Before(stages = LifecycleStage.BindingAndValidation)
	public void init() {
		String detailId = getContext().getRequest().getParameter("detailId");
		if ( detailId != null && detailId.length() > 0 )
			stateReportingDetail = csrService.getStateReportingDetail(Integer.parseInt(detailId));
		
		if ( stateReportingDetail == null )
			stateReportingDetail = new StateReportingDetail();
		
		reports = reportService.getReports(stateReportingDetail);
	}
}
