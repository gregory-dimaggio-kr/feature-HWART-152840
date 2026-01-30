package com.kroger.pharmacy.csr.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.Entity;

@Entity
@Table(name="FACILITY", schema="TREXONE_DW_DATA")
public class PartialFacility {
	private BigDecimal facilityKey;
	
	private String facilityId;
	
	private BigDecimal facilityNum;
	
	private String deaNumber;
	
	private PartialState state;
	
	public PartialFacility() {
		super();
	}

	@Id
	@Column(name="FD_FACILITY_KEY")
	public BigDecimal getFacilityKey() {
		return facilityKey;
	}

	public void setFacilityKey(BigDecimal facilityKey) {
		this.facilityKey = facilityKey;
	}

	@Column(name="FD_FACILITY_ID")
	public String getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}

	@ManyToOne
	@JoinColumn(name="FD_STATE_CODE",referencedColumnName="STATE_CODE")
	public PartialState getState() {
		return state;
	}

	public void setState(PartialState state) {
		this.state = state;
	}

	@Column(name="FD_FACILITY_NUM")
	public BigDecimal getFacilityNum() {
		return facilityNum;
	}

	public void setFacilityNum(BigDecimal facilityNum) {
		this.facilityNum = facilityNum;
	}

	@Column(name="FD_DEA_NUMBER")
	public String getDeaNumber() {
		return deaNumber;
	}

	public void setDeaNumber(String deaNumber) {
		this.deaNumber = deaNumber;
	}

}
