package com.kroger.pharmacy.csr.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;







import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.kroger.commons.util.convert.DelimiterStringTokenizer;
import com.kroger.commons.util.convert.StringTokenizer;
import com.kroger.pharmacy.csr.domain.Report;
import com.kroger.pharmacy.csr.domain.State;
import com.kroger.pharmacy.csr.domain.StateReportingDetail;
import com.kroger.pharmacy.csr.util.StateMap;

/**
 * @author CRD3591
 * Implementation of IReportService interface
 */
public class ReportService implements IReportService {
	/**
	 * 
	 */
	private String reportDirectory;
	
	private String userAuthorizationPropertiesFile;
	
	protected EntityManager entityManager;
	
	public EntityManager getEntityManager() {
        return entityManager;
    }
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	
	public String getReportDirectory() {
		return reportDirectory;
	}

	/**
	 * @param reportDirectory Directory where reports are located
	 */
	public void setReportDirectory(String reportDirectory) {
		this.reportDirectory = reportDirectory;
	}

	/**
	 * @param parameters State or Date object
	 * @return Reports in the current report directory that match the State and Date parameters
	 */
	public List<Report> getReports(Object... parameters) {
		@SuppressWarnings("unchecked")
		List<Report> result = getEntityManager().createQuery("from Report r").getResultList();
		
		return result;
	}

	/**
	 * @param state Report state
	 * @return List of all generation dates of existing report files for the state
	 */
	public List<Date> getAllDatesForState(State state) {
		List<Date> dates = new ArrayList<Date>();
		File reportDirectoryFile = new File(getReportDirectory());
		if ( reportDirectoryFile.isDirectory() && reportDirectoryFile.exists() ) {
			DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
			List<File> dateDirectories = Arrays.asList(reportDirectoryFile.listFiles());
			for ( File dateDirectory : dateDirectories ) {
				if ( dateDirectory.isDirectory() ) {
					List<String> fileList = Arrays.asList(dateDirectory.list());
					for ( String filename : fileList ) {
						if ( filename.startsWith(state.getCode()) ) {
							try {
								dates.add(dateFormat.parse(dateDirectory.getName()));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							
							break;
						}
					}
				}
			}
		}
		return dates;
	}
	
	/**
	 * @param state
	 * @return The latest report generated for the state
	 */
	public Report getLatestReport(StateReportingDetail stateReportingDetail) {			
			@SuppressWarnings("unchecked")
			List<Report> result = getEntityManager().createQuery("from Report r where r.stateReportingDetail = ?1 order by r.generationDate desc")
										.setParameter(1, stateReportingDetail)
										.setMaxResults(1)
										.getResultList();
			if ( result.size() > 0 ) 
				return result.get(0);
			
			return null;
	}
	
	/**
	 * @param dates
	 * @return The most recent date
	 */
	private Date getLatestDate(List<Date> dates) {
		Date result = null;
		
		if ( dates != null && dates.size() > 0 ) {
			result = dates.get(0);
			
			for ( Date date : dates )
				if ( date.getTime() > result.getTime() )
					result = date;
		}
		
		return result;
	}

	public List<State> getAuthorizedStatesForUser(String username) {
		Properties properties = new Properties();
		List<State> result = new ArrayList<State>();
		
		try {
			properties.load(new FileInputStream(userAuthorizationPropertiesFile));
			String stateListString = properties.getProperty(username.toUpperCase());
			
			StringTokenizer stringTokenizer = new DelimiterStringTokenizer(",");
			List<String> stateCodes = Arrays.asList(stringTokenizer.tokenizeString(stateListString));
			StateMap stateMap = new StateMap();
			
			for ( String stateCode : stateCodes )
				result.add(new State(stateMap.getStateName(stateCode), stateCode));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public void setUserAuthorizationPropertiesFile(
			String userAuthorizationPropertiesFile) {
		this.userAuthorizationPropertiesFile = userAuthorizationPropertiesFile;
	}

	public Report createReportHistoryRecord(Report report) {
		return persistReportHistoryRecord(report);
	}

	@Transactional(readOnly=false)
	public Report persistReportHistoryRecord(Report report) {
		report = getEntityManager().merge(report);
		getEntityManager().persist(report);
		getEntityManager().flush();
		
		return report;
	}

	@Transactional(readOnly=false)
	public void removeReportHistoryRecord(Report report) {
		getEntityManager().remove(report);
		getEntityManager().flush();
	}

	public List<StateReportingDetail> populateLatestReports(
			List<StateReportingDetail> stateReportingDetails) {
		
		List<StateReportingDetail> result = new ArrayList<StateReportingDetail>();
		for ( StateReportingDetail stateReportingDetail : stateReportingDetails ) {
			stateReportingDetail = populateLatestReport(stateReportingDetail);
			result.add(stateReportingDetail);
		}
				
		return result;
	}
	
	private StateReportingDetail populateLatestReport(
			StateReportingDetail stateReportingDetail) {
		stateReportingDetail.setLatestReport(
				getLatestReport(stateReportingDetail));
		return stateReportingDetail;
	}

	public List<Report> getReports(StateReportingDetail stateReportingDetail) {
			@SuppressWarnings("unchecked")
			List<Report> reports = getEntityManager().createQuery("from Report r where r.stateReportingDetail = ?1 order by r.generationDate desc")
										.setParameter(1, stateReportingDetail)
										.getResultList();
			return reports;
		
	}

	public Report getReport(Integer id) {
		return getEntityManager().find(Report.class, id);
	}
}
