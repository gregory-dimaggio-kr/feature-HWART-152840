package com.kroger.pharmacy.csr.domain;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * @author CRD3591
 * Domain object to represent a Controlled Substance Report
 */
@Entity
@Table(name="CSR_REPORTING_HISTORY",schema="TREXONE_DW_DATA")
public class Report extends SelectableObject {
	/**
	 * Display format of the report generation date
	 */
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("MMM d, yyyy");
	private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("MMM d, yyyy h:mm a");
	
	private Integer id;
	
	/**
	 * Report generation date
	 */
	private Date generationDate;
	
	private Date agencySubmissionDate;
	
	private StateReportingDetail stateReportingDetail;
	
	private String filename;
	
	private String fileDirectory;
	
	private Integer numRecords;
	
	/**
	 * 
	 */
	private boolean selected;
	
	/**
	 * 
	 */
	public Report() {
		super();
	}

	@Column(name="GENERATION_DATE")
	public Date getGenerationDate() {
		return generationDate;
	}

	public void setGenerationDate(Date generationDate) {
		this.generationDate = generationDate;
	}

	@Transient
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	/**
	 * @return Report generation date in the format described by DATE_FORMAT
	 */
	@Transient
	public String getFormattedGenerationDate() {
		if ( getGenerationDate() == null )
			return null;
		
		return DATE_FORMAT.format(getGenerationDate());
	}
	
	@Transient
	public String getFormattedAgencySubmissionDate() {
		if ( getAgencySubmissionDate() == null )
			return null;
		
		return DATE_FORMAT.format(getAgencySubmissionDate());
	}
	
	@Transient
	public String getFormattedGenerationDateTime() {
		if ( getGenerationDate() == null )
			return null;
		
		return DATE_TIME_FORMAT.format(getGenerationDate());
	}
	
	@Transient
	public String getFormattedAgencySubmissionDateTime() {
		if ( getAgencySubmissionDate() == null )
			return null;
		
		return DATE_TIME_FORMAT.format(getAgencySubmissionDate());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if ( !(other instanceof Report) )
			return false;
		
		Report that = (Report)other;
		
		return new EqualsBuilder()
			.append(this.getFilename(), that.getFilename())
			.append(this.getFileDirectory(), that.getFileDirectory())
			.isEquals();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="rh_sequence_generator")
	@SequenceGenerator(name="rh_sequence_generator",sequenceName="trexone_dw_data.reporting_history_seq",initialValue=1,allocationSize=1)
	@Column(name="CRH_KEY")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="STATE_CODE",referencedColumnName="STATE_CODE",updatable=false,insertable=false)
	public StateReportingDetail getStateReportingDetail() {
		return stateReportingDetail;
	}

	public void setStateReportingDetail(StateReportingDetail stateReportingDetail) {
		this.stateReportingDetail = stateReportingDetail;
	}

	@Column(name="FILE_NAME")
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name="FILE_DIRECTORY")
	public String getFileDirectory() {
		return fileDirectory;
	}

	public void setFileDirectory(String fileDirectory) {
		this.fileDirectory = fileDirectory;
	}

	@Transient
	public File getFile() {
		return new File(getFileDirectory(),getFilename());
	}

	@Column(name="AGENCY_SUBMISSION_DATE")
	public Date getAgencySubmissionDate() {
		return agencySubmissionDate;
	}

	public void setAgencySubmissionDate(Date agencySubmissionDate) {
		this.agencySubmissionDate = agencySubmissionDate;
	}
	
	@Transient
	public boolean isSubmitted() {
		return (getAgencySubmissionDate() != null);
	}

	@Column(name="NUM_ITEMS")
	public Integer getNumRecords() {
		return numRecords;
	}

	public void setNumRecords(Integer numRecords) {
		this.numRecords = numRecords;
	}
}
