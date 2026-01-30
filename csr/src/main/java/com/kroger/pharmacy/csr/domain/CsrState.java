package com.kroger.pharmacy.csr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CSR_STATE",schema="TREXONE_DW_DATA")
public class CsrState {
	public static final String ASAP_1995_FORMAT = "1995";
	public static final String ASAP_2005_FORMAT = "2005";
	public static final String ASAP_2007_FORMAT = "2007";
	public static final String ASAP_2009_FORMAT = "2009";
	public static final String ASAP_2011_FORMAT = "2011";
	
	private String stateCode;
	
	private String asapFormat;

	public CsrState() {
		super();
	}

	public CsrState(String stateCode, String asapFormat) {
		super();
		this.stateCode = stateCode;
		this.asapFormat = asapFormat;
	}

	@Id
	@Column(name="CS_STATE_CODE")
	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	@Column(name="CS_CSR_ASAP_VERSION")
	public String getAsapFormat() {
		return asapFormat;
	}

	public void setAsapFormat(String asapFormat) {
		this.asapFormat = asapFormat;
	}
	
	
}
