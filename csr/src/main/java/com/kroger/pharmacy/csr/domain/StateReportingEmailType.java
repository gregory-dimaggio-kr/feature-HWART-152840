package com.kroger.pharmacy.csr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;

@Entity
@Table(name="CSR_STATE_REPORTING_EMAIL_TYPE",schema="TREXONE_DW_DATA")
public class StateReportingEmailType {
	public static final Integer NOTIFICATION_EMAIL_TYPE = new Integer(1);
	public static final Integer ATTACHMENT_EMAIL_TYPE = new Integer(2);
	public static final Integer NO_EMAIL_TYPE = new Integer(0);
	
	private Integer id;
	
	private String description;

	public StateReportingEmailType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StateReportingEmailType(Integer id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	@Id
	@Column(name="EMAIL_TYPE_KEY")
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
		if ( !(arg0 instanceof StateReportingEmailType) )
			return false;
		else
			return new EqualsBuilder()
				.append(this.getId(), ((StateReportingEmailType)arg0).getId())
				.isEquals();
	}
}
