package com.kroger.pharmacy.csr.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CSR_RXFILL_EXTRACT_CORRECTIONS", schema="TREXONE_DW_DATA")
public class RxExtractCorrection {
	private Integer correctionKey;
	
	private BigDecimal extractKey;
	
	private Date correctionDate;
	
	private String userEuid;

	public RxExtractCorrection() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="correction_sequence_generator")
	@SequenceGenerator(name="correction_sequence_generator",sequenceName="trexone_dw_data.CSR_RXFILL_EXTRACT_CORR_SEQ",initialValue=1,allocationSize=1)
	@Column(name="CORRECTION_KEY")
	public Integer getCorrectionKey() {
		return correctionKey;
	}

	public void setCorrectionKey(Integer correctionKey) {
		this.correctionKey = correctionKey;
	}

	@Column(name="CRE_KEY", nullable=false)
	public BigDecimal getExtractKey() {
		return extractKey;
	}

	public void setExtractKey(BigDecimal rxExtractKey) {
		this.extractKey = rxExtractKey;
	}

	@Column(name="CORRECTION_DATE")
	public Date getCorrectionDate() {
		return correctionDate;
	}

	public void setCorrectionDate(Date correctionDate) {
		this.correctionDate = correctionDate;
	}

	@Column(name="USER_EUID")
	public String getUserEuid() {
		return userEuid;
	}

	public void setUserEuid(String userEuid) {
		this.userEuid = userEuid;
	}
	
	
}
