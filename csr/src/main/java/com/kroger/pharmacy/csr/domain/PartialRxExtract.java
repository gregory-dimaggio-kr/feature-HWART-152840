package com.kroger.pharmacy.csr.domain;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.persistence.Entity;

@Entity
@Table(name="CSR_RXFILL_EXTRACT", schema="TREXONE_DW_DATA")
public class PartialRxExtract {
	private BigDecimal extractKey;
	
	private String patientFirstName;
	
	private String patientLastName;
	
	private String rxNumber;
	
	private Date rxDispenseDate;
	
	private Integer rxRefillNumber;
	
	private PartialFacility facility;
	
	boolean changed;
	
	public PartialRxExtract() {
		super();
	}

	@Id
	@Column(name="CRE_KEY")
	public BigDecimal getExtractKey() {
		return extractKey;
	}

	public void setExtractKey(BigDecimal extractKey) {
		this.extractKey = extractKey;
	}

	@Column(name="CRE_PATIENT_FIRST_NAME", length=30)
	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	@Column(name="CRE_PATIENT_LAST_NAME", length=30)
	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	@Column(name="CRE_RX_NUMBER", length=20)
	public String getRxNumber() {
		return rxNumber;
	}

	public void setRxNumber(String rxNumber) {
		this.rxNumber = rxNumber;
	}

	@Column(name="CRE_RX_FILL_DISPENSE_DATE")
	public Date getRxDispenseDate() {
		return rxDispenseDate;
	}

	public void setRxDispenseDate(Date rxDispenseDate) {
		this.rxDispenseDate = rxDispenseDate;
	}

	@Column(name="CRE_RX_FILL_REFILL_NUM")
	public Integer getRxRefillNumber() {
		return rxRefillNumber;
	}

	public void setRxRefillNumber(Integer rxRefillNumber) {
		this.rxRefillNumber = rxRefillNumber;
	}

	@OneToOne
	@JoinColumn(name="FACILITY_KEY",referencedColumnName="FD_FACILITY_KEY")
	public PartialFacility getFacility() {
		return facility;
	}

	public void setFacility(PartialFacility facility) {
		this.facility = facility;
	}

	@Transient
	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	
	@Transient
	public String getFormattedDispenseDate() {
		DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
		return dateFormat.format(getRxDispenseDate());
	}
	
}
