package com.kroger.pharmacy.csr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;

@Entity
@Table(name="CSR_STATE_REPORTING_METHOD",schema="TREXONE_DW_DATA")
public class StateReportingMethod {
	public static final Integer FTP_METHOD = new Integer(1);
	public static final Integer SFTP_METHOD = new Integer(2);
	public static final Integer MANUAL_METHOD = new Integer(3);
	
	private Integer id;
	
	private String description;

	public StateReportingMethod() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StateReportingMethod(Integer id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	@Id
	@Column(name="METHOD_KEY")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object arg0) {
		if ( !(arg0 instanceof StateReportingMethod) )
			return false;
		else
			return new EqualsBuilder()
				.append(this.getId(), ((StateReportingMethod)arg0).getId())
				.isEquals();
	}
	
}
