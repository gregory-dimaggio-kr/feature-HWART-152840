package com.kroger.pharmacy.csr.view.action.csr;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.kroger.commons.web.stripes.AbstractActionBean;
import com.kroger.pharmacy.csr.domain.Report;
import com.kroger.pharmacy.csr.service.ILookupService;
import com.kroger.pharmacy.csr.service.IReportService;
import com.techrx.app.trexone.batch.controlledsubstancereporting.CSRRemoteService;

/**
 * @author CRD3591
 * Stripes Action Bean for csrdownload.jsp view
 */
@UrlBinding("/DownloadReport.action")
public class DownloadReportBean extends AbstractActionBean {
	
	private Integer reportId;
	
	private IReportService reportService;
	
	private ILookupService lookupService;
	
	@SpringBean
	protected void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

	@SpringBean
	protected void setLookupService(ILookupService lookupService) {
		this.lookupService = lookupService;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	/**
	 * Deletes currently selected report file and its parent directory if the directory is empty
	 * @return Forwards to csrdownload.jsp view
	 */
	public Resolution purge() {
//		File file = getSelectedReport().getFile();
//		File parentFile = file.getParentFile();
//		
//		file.delete();
//		
//		if ( parentFile.list() == null || parentFile.list().length == 0 )
//			parentFile.delete();
		
		return new ForwardResolution("/view/downloadreport/csrdownload.jsp");
	}

	/**
	 * Downloads currently selected report file
	 * @return Streams selected report file to the browser
	 */
	@DefaultHandler
	public Resolution download() {
		CSRRemoteService remoteService = null;
		byte[] reportBytes = null;
		String reportText = null;
		Report report = null;
		
		if ( getReportId() != null )
			report = reportService.getReport(getReportId());
		
		try {
			remoteService = lookupService.lookupCSRRemoteService();
			reportBytes = remoteService.retrieveReportBytes(report.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if ( reportBytes == null ) {
			getContext().saveErrorMessage("Error downloading report.");
			return new ForwardResolution("/view/csr/csrstatelist.jsp");
		}
		
		List<Byte> bytes = new ArrayList<Byte>();
		for ( int i=0; i<reportBytes.length; i++ ) {
			byte b = reportBytes[i];
			if ( b == 10 )
				bytes.add(new Byte((byte)13));
			
			bytes.add(new Byte(b));
		}
		
		Byte[] bs = new Byte[bytes.size()];
		bytes.toArray(bs);
		reportBytes = new byte[bs.length];
		for ( int i=0; i<bs.length; i++ )
			reportBytes[i] = bs[i].byteValue();
		
		InputStream inputStream = new ByteArrayInputStream(reportBytes);
		StreamingResolution resolution = new StreamingResolution("application/octet-stream", 
				inputStream);
		if ( report != null )
			resolution.setFilename(report.getFilename());
		else
			resolution.setFilename("report.dat");
		return resolution;
	}
	
	/**
	 * Returns the int value of a number between brackets in the input string.  For example,
	 * the input "foo[42]" would generate the output 42.
	 * @param bracketedString Input string
	 * @return Parsed index value or -1 if no brackets exist in the input
	 */
	private int parseIndex(String bracketedString) {
		int leftBracketIndex = bracketedString.indexOf("[");
		int rightBracketIndex = bracketedString.indexOf("]");
		
		if ( leftBracketIndex > -1 && rightBracketIndex > (leftBracketIndex + 1) ) {
			String indexString = bracketedString.substring(leftBracketIndex+1, rightBracketIndex);
			
			return Integer.parseInt(indexString);
		}
		else
			return -1;
	}
	
	/**
	 * Initializes authorizedStates, latestReports, and archivedReports list.
	 * Runs before every Resolution is processed.
	 */
	@Before(stages = LifecycleStage.BindingAndValidation)
	public void init() {
		String reportIdString = getContext().getRequest().getParameter("reportId");
		if ( reportIdString != null && reportIdString.length() > 0 )
			reportId = new Integer(reportIdString);
		
	}
}
