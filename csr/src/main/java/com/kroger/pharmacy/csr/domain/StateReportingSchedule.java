package com.kroger.pharmacy.csr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;

@Entity
@Table(name="CSR_STATE_REPORTING_SCHEDULE",schema="TREXONE_DW_DATA")
public class StateReportingSchedule {
	public static final Integer WEEKLY_SCHEDULE = new Integer(1);
	public static final Integer BIWEEKLY_SCHEDULE = new Integer(2);
	public static final Integer MONTHLY_SCHEDULE = new Integer(3);
	public static final Integer BIMONTHLY_SCHEDULE = new Integer(4);
	public static final Integer SEMIMONTHLY_SCHEDULE = new Integer(5);
	
	private Integer id;
	
	private String description;

	public StateReportingSchedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StateReportingSchedule(Integer id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	@Id
	@Column(name="SCHEDULE_KEY")
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
		if ( !(arg0 instanceof StateReportingSchedule) )
			return false;
		else
			return new EqualsBuilder()
				.append(this.getId(), ((StateReportingSchedule)arg0).getId())
				.isEquals();
	}
	
	
}
