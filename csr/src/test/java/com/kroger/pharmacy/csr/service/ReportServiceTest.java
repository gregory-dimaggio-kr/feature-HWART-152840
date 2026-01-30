package com.kroger.pharmacy.csr.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.kroger.pharmacy.csr.AbstractApplicationTestCase;
import com.kroger.pharmacy.csr.domain.Report;
import com.kroger.pharmacy.csr.domain.State;
import com.kroger.pharmacy.csr.domain.StateReportingDetail;

public class ReportServiceTest extends AbstractApplicationTestCase {

	
	@Test
	public void testReportService() throws Exception {
				
		File directory = new File(reportService.getReportDirectory());
		Assert.assertTrue(directory.exists());
		
		createReportHistoryFromFile(directory);
		
		List<Report> reports = reportService.getReports();
		Assert.assertTrue(reports.size() > 0);
		
		StateReportingDetail inDetail = csrService.getStateReportingDetail("IN");
		List<StateReportingDetail> stateReportingDetails = new ArrayList<StateReportingDetail>();
		stateReportingDetails.add(inDetail);
		stateReportingDetails = reportService.populateLatestReports(stateReportingDetails);
		
		State indiana = new State("Indiana", "IN");
		State california = new State("California", "CA");
	}
	
	private void createReportHistoryFromFile(File file) {
		if ( file.exists() ) {
			if ( file.isDirectory() ) {
				List<File> directoryListing = Arrays.asList(file.listFiles());
				for ( File dirFile : directoryListing )
					createReportHistoryFromFile(dirFile);
			}
			else {
				Report report = new Report();
				report.setFileDirectory(file.getParent());
				report.setFilename(file.getName());
				report.setGenerationDate(new Date());
				report.setStateReportingDetail(csrService.getStateReportingDetail("IN"));
				
				reportService.createReportHistoryRecord(report);
			}
		}
	}
}
