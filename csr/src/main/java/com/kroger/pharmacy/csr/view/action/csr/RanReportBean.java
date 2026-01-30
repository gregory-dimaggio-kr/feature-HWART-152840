package com.kroger.pharmacy.csr.view.action.csr;

import java.util.Date;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.kroger.commons.web.stripes.AbstractActionBean;
import com.kroger.pharmacy.csr.domain.Report;
import com.kroger.pharmacy.csr.domain.StateReportingEmailType;
import com.kroger.pharmacy.csr.service.ICsrService;
import com.kroger.pharmacy.csr.service.ILookupService;
import com.kroger.pharmacy.csr.service.IReportService;
import com.kroger.pharmacy.csr.util.CSRConstants;
import com.techrx.app.trexone.batch.controlledsubstancereporting.CSRRemoteService;

@UrlBinding("/RanReport.action")
public class RanReportBean extends AbstractActionBean {
	private ICsrService csrService;
	
	private IReportService reportService;
	
	private ILookupService lookupService;
	
	private Report selectedReport;
	
	private String reportId;
	
	private boolean automatedTransferSupported;
	
	private boolean emailAlertSupported;
	
	private boolean submitForced;
	
	@SpringBean
	protected void setCsrService(ICsrService csrService) {
		this.csrService = csrService;
	}

	@SpringBean
	protected void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

	@SpringBean
	protected void setLookupService(ILookupService lookupService) {
		this.lookupService = lookupService;
	}

	public Report getSelectedReport() {
		return selectedReport;
	}

	public void setSelectedReport(Report selectedReport) {
		this.selectedReport = selectedReport;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public boolean isAutomatedTransferSupported() {
		return automatedTransferSupported;
	}

	public void setAutomatedTransferSupported(boolean automatedTransferSupported) {
		this.automatedTransferSupported = automatedTransferSupported;
	}

	public boolean isEmailAlertSupported() {
		return emailAlertSupported;
	}

	public void setEmailAlertSupported(boolean emailAlertSupported) {
		this.emailAlertSupported = emailAlertSupported;
	}

	public boolean isSubmitForced() {
		return submitForced;
	}

	public void setSubmitForced(boolean submitForced) {
		this.submitForced = submitForced;
	}

	@DefaultHandler
	public Resolution ranReportScreen() {
		ForwardResolution result = new ForwardResolution("/view/csr/ranreport.jsp");
		if ( reportId != null )
			result.addParameter("reportId", reportId);
		return result;
	}

	public Resolution download() {
		ForwardResolution result = new ForwardResolution("/DownloadReport.action");
		result.addParameter("download","yes");
		if ( reportId != null )
			result.addParameter("reportId", reportId);
		return result;
	}
	
	/**
	 * Shows the info message if no file has been selected to upload.
	 * 
	 * @return
	 */
	public Resolution noSelectionMade() {
		getContext().saveInfoMessage("Please select a Report.");
		return ranReportScreen();
	}
	
	/**
	 * Forwards the request with added parameters to uploadEditedReport.jsp view allowing 
	 * the user to upload the edited file.
	 * 
	 * @return Resolution
	 */
	public Resolution upload() {
		if (selectedReport == null)
			return noSelectionMade();

		ForwardResolution resolution = new ForwardResolution("/view/csr/uploadEditedReport.jsp");
		resolution.addParameter(CSRConstants.reportId, selectedReport.getId());
		resolution.addParameter(CSRConstants.fileDirectory, selectedReport.getFileDirectory());
		resolution.addParameter(CSRConstants.fileName, selectedReport.getFilename());

		return resolution;
	}
	
	public Resolution delete() {
		if ( selectedReport != null ) {
			reportService.removeReportHistoryRecord(selectedReport);
			getContext().saveInfoMessage("Report file " + selectedReport.getFilename() + " deleted.");
		}
		
		ForwardResolution result = new ForwardResolution("/view/csr/csrstatelist.jsp");
		return result;
	}
	
	public Resolution transmit() {
		if ( selectedReport == null )
			return ranReportScreen();
		
		CSRRemoteService remoteService = null;
		
		try {
			remoteService = lookupService.lookupCSRRemoteService();
			int transferResult = remoteService.sendReport(selectedReport.getId());
			
//			if ( transferResult == CSRLocalServiceImpl.TRANSFER_SUCCESS_CODE )
				getContext().saveInfoMessage("Report transfer successful.");
//			else
//				getContext().saveErrorMessage("Error occurred during report transfer.");
		} catch (Exception e) {
			getContext().saveErrorMessage("Error occurred during report transfer: " + e.getMessage());
			e.printStackTrace();
		}
		
		return ranReportScreen();
	}
	
	public Resolution markAsTransmitted() {
		if ( selectedReport == null )
			return ranReportScreen();
		
		selectedReport.setAgencySubmissionDate(new Date());
		selectedReport = reportService.persistReportHistoryRecord(selectedReport);
		
		return ranReportScreen();
	}
		
	@Before(stages = LifecycleStage.BindingAndValidation)
	public void init() {
		reportId = getContext().getRequest().getParameter("reportId");
		if ( reportId != null && reportId.length() > 0 )
			selectedReport = reportService.getReport(new Integer(reportId));
		
		if ( selectedReport != null ) { 
			automatedTransferSupported = selectedReport.getStateReportingDetail()
					.hasAutomatedTransferMethod();
		
			if ( !automatedTransferSupported )
				emailAlertSupported = 
					( selectedReport.getStateReportingDetail().getEmailType() != null &&
							selectedReport.getStateReportingDetail().getEmailType().getId() 
								!= StateReportingEmailType.NO_EMAIL_TYPE );
		}
	}
}
