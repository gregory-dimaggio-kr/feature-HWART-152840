package com.kroger.pharmacy.csr.service;

import java.util.Date;
import java.util.List;

import com.kroger.pharmacy.csr.domain.Report;
import com.kroger.pharmacy.csr.domain.State;
import com.kroger.pharmacy.csr.domain.StateReportingDetail;


/**
 * @author CRD3591
 * The IReportService interface provides methods for the retrieval report objects from the file system 
 */
public interface IReportService {
	/**
	 * @param reportDirectory Directory where reports are located
	 */
	public void setReportDirectory(String reportDirectory);
	/**
	 * @param userAuthorizationPropertiesFile File that determines which reports the user is authorized to download
	 */
	public void setUserAuthorizationPropertiesFile(String userAuthorizationPropertiesFile);
	/**
	 * @param parameters State or Date object
	 * @return Reports in the current report directory that match the State and Date parameters
	 */
	public List<Report> getReports(Object... parameters);
	/**
	 * @param state Report state
	 * @return List of all generation dates of existing report files for the state
	 */
	public List<Date> getAllDatesForState(State state);
	/**
	 * @param state
	 * @return The latest report generated for the state
	 */
	public Report getLatestReport(StateReportingDetail stateReportingDetail);
	public List<Report> getReports(StateReportingDetail stateReportingDetail);
	public String getReportDirectory();
	
	/**
	 * @param username Authenticated user ID
	 * @return List of states that the user can see reports for
	 */
	public List<State> getAuthorizedStatesForUser(String username);
	
	public Report createReportHistoryRecord(Report report);
	public Report persistReportHistoryRecord(Report report);
	public void removeReportHistoryRecord(Report report);
	
	public List<StateReportingDetail> populateLatestReports(List<StateReportingDetail> stateReportingDetails);
	public Report getReport(Integer id);
}
