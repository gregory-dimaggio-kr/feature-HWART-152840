package com.kroger.pharmacy.csr.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="CSR_STATE_REPORTING_DETAIL",schema="TREXONE_DW_DATA")
public class StateReportingDetail extends SelectableObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1361060976837537028L;

	private Integer id;
	
	private State state;
	
	private StateReportingSchedule schedule;
	
	private StateReportingMethod method;
	
	private String username;
	
	private String password;
	
	private String filenameFormat;
	
	private String dateFormat;
	
	private String remoteUrl;
	
	private String remoteDirectory;
	
	private String localDirectory;
	
	private StateReportingEmailType emailType;
	
	private boolean active;
	
	private Report latestReport;
	
	private CsrState csrState;
	
	private String stateCode;
	
	private String websiteUrl;

	public StateReportingDetail() {
		super();
	}

	public StateReportingDetail(Integer id, State state,
			StateReportingSchedule schedule, StateReportingMethod method,
			String username, String password, String filenameFormat,
			String dateFormat, String remoteUrl, String remoteDirectory,
			String localDirectory, StateReportingEmailType emailType) {
		super();
		this.id = id;
		this.state = state;
		this.schedule = schedule;
		this.method = method;
		this.username = username;
		this.password = password;
		this.filenameFormat = filenameFormat;
		this.dateFormat = dateFormat;
		this.remoteUrl = remoteUrl;
		this.remoteDirectory = remoteDirectory;
		this.localDirectory = localDirectory;
		this.emailType = emailType;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="srd_sequence_generator")
	@SequenceGenerator(name="srd_sequence_generator",sequenceName="trexone_dw_data.reporting_details_seq",initialValue=1,allocationSize=1)
	@Column(name="CSRD_KEY")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Embedded
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@ManyToOne
	@JoinColumn(name="SCHEDULE_KEY",nullable=false)
	public StateReportingSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(StateReportingSchedule schedule) {
		this.schedule = schedule;
	}

	@ManyToOne
	@JoinColumn(name="METHOD_KEY",nullable=false)
	public StateReportingMethod getMethod() {
		return method;
	}

	public void setMethod(StateReportingMethod method) {
		this.method = method;
	}

	@Column(name="REPORTING_USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="REPORTING_PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="REPORTING_FILENAME_FORMAT")
	public String getFilenameFormat() {
		return filenameFormat;
	}

	public void setFilenameFormat(String filenameFormat) {
		this.filenameFormat = filenameFormat;
	}

	@Column(name="REPORTING_DATE_FORMAT")
	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Column(name="REPORTING_REMOTE_URL")
	public String getRemoteUrl() {
		return remoteUrl;
	}

	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}

	@Column(name="REPORTING_REMOTE_DIRECTORY")
	public String getRemoteDirectory() {
		return remoteDirectory;
	}

	public void setRemoteDirectory(String remoteDirectory) {
		this.remoteDirectory = remoteDirectory;
	}

	@Column(name="REPORTING_LOCAL_DIRECTORY")
	public String getLocalDirectory() {
		return localDirectory;
	}

	public void setLocalDirectory(String localDirectory) {
		this.localDirectory = localDirectory;
	}

	@ManyToOne
	@JoinColumn(name="EMAIL_TYPE_KEY",nullable=false)
	public StateReportingEmailType getEmailType() {
		return emailType;
	}

	public void setEmailType(StateReportingEmailType emailType) {
		this.emailType = emailType;
	}

	@Column(name="REPORTING_ACTIVE_FLAG")
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public boolean equals(Object arg0) {
		if ( !(arg0 instanceof StateReportingDetail) )
			return false;
		
		StateReportingDetail that = (StateReportingDetail)arg0;
		
		if ( this.getId() != null && that.getId() != null )
			return this.getId().equals(that.getId());
		
		return this.getState().equals(that.getState());
	}

	@Transient
	public Report getLatestReport() {
		return latestReport;
	}

	public void setLatestReport(Report latestReport) {
		this.latestReport = latestReport;
	}

	@OneToOne
	@JoinColumn(name="STATE_CODE",insertable=false,updatable=false,referencedColumnName="CS_STATE_CODE")
	public CsrState getCsrState() {
		return csrState;
	}

	public void setCsrState(CsrState csrState) {
		this.csrState = csrState;
	}

	@Column(name="STATE_CODE",unique=true,insertable=false,updatable=false)
	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@Column(name="REPORTING_WEBSITE_URL")
	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	
	@Transient
	public boolean hasAutomatedTransferMethod() {
		return !method.getId().equals(StateReportingMethod.MANUAL_METHOD);
	}
}
