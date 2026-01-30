package com.kroger.pharmacy.csr.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FACILITY_ID", schema="TREXONE_DW_DATA")
public class FacilityId {
	private BigDecimal id;
	
	private BigDecimal facilityNum;
	
	private String idValue;

	@Id
	@Column(name="FD_FACILITY_ID_NUM")
	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	@Column(name="FD_FACILITY_NUM")
	public BigDecimal getFacilityNum() {
		return facilityNum;
	}

	public void setFacilityNum(BigDecimal facilityNum) {
		this.facilityNum = facilityNum;
	}

	@Column(name="FD_VALUE")
	public String getIdValue() {
		return idValue;
	}

	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}
	
	
}
