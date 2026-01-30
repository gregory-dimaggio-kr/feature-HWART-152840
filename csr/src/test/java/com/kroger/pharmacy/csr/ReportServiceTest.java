package com.kroger.pharmacy.csr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import com.kroger.pharmacy.csr.domain.Report;
import com.kroger.pharmacy.csr.domain.State;
import com.kroger.pharmacy.csr.service.IReportService;

public class ReportServiceTest extends AbstractApplicationTestCase {

	@Test
	public void testReportService() throws Exception {
		IReportService reportService = (IReportService)
				applicationContext.getBean("reportService");
		
		
		List<Report> reports = reportService.getReports();
		assertTrue(reports.size() > 20);
		
		reports = reportService.getReports(new State("Indiana", "IN"));
		assertTrue(reports.size() > 10);
		
		reports = reportService.getReports(new State("Ohio", "OH"));
		assertTrue(reports.size() == 0);
		
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 11);
		Date date = calendar.getTime();
		
		reports = reportService.getReports(date);
		assertTrue(reports.size() == 0);
		
		calendar.set(Calendar.DAY_OF_MONTH, 18);
		date = calendar.getTime();
		
		reports = reportService.getReports(date);
		assertTrue(reports.size() == 2);
		assertEquals("CA.dat", reports.get(0).getFilename());
		assertEquals("IN.dat", reports.get(1).getFilename());
		
		State state = new State("Indiana", "IN");
		List<Date> dates = reportService.getAllDatesForState(state);
		assertTrue(dates.size() > 0);
		
//		Report report = reportService.getLatestReport(state);
//		assertEquals("IN02142008.dat", report.getFilename());
		
		List<State> authorizedStates = reportService.getAuthorizedStatesForUser("USER");
		assertTrue(authorizedStates.contains(new State("Indiana", "IN")));
		assertEquals(authorizedStates.size(), 1);
	}
}
