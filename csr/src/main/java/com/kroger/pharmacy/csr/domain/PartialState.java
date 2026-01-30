package com.kroger.pharmacy.csr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * @author CRD3591
 * Domain object to represent a State
 */
@Entity
@Table(name="CSR_STATE_REPORTING_DETAIL",schema="TREXONE_DW_DATA")
public class PartialState {
	/**
	 * State name
	 */
	private String name;
	/**
	 * State postal code (KY, OH, etc.)
	 */
	private String code;
	
	/**
	 * 
	 */
	public PartialState() {
		super();
	}

	/**
	 * @param name
	 * @param code
	 */
	public PartialState(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	@Column(name="STATE_NAME")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Id
	@Column(name="STATE_CODE",nullable=false,unique=true)
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if ( other == null || !(other instanceof PartialState) )
			return false;
		
		PartialState that = (PartialState)other;
		
		if ( this.getCode() != null && that.getCode() != null )
			return this.getCode().equalsIgnoreCase(that.getCode());
		
		return new EqualsBuilder()
			.append(this.getName(), that.getName())
			.isEquals();
	}
	
	
}
