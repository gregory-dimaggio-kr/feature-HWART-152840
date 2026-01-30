package com.kroger.pharmacy.csr.util;

import com.kroger.pharmacy.csr.domain.RxExtract;

public class Correction {
	private RxExtract rxExtract;
	
	private RxExtract correctedRxExtract;
	
	private Object correctedValue;
	
	private String correctedField;
	
	private String errorMessage;
	
	private String databaseField;

	public RxExtract getRxExtract() {
		return rxExtract;
	}

	public void setRxExtract(RxExtract rxExtract) {
		this.rxExtract = rxExtract;
	}

	public RxExtract getCorrectedRxExtract() {
		return correctedRxExtract;
	}

	public void setCorrectedRxExtract(RxExtract correctedRxExtract) {
		this.correctedRxExtract = correctedRxExtract;
	}

	public Object getCorrectedValue() {
		return correctedValue;
	}

	public void setCorrectedValue(Object correctedValue) {
		this.correctedValue = correctedValue;
	}

	public String getCorrectedField() {
		return correctedField;
	}

	public void setCorrectedField(String correctedField) {
		this.correctedField = correctedField;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDatabaseField() {
		return databaseField;
	}

	public void setDatabaseField(String databaseField) {
		this.databaseField = databaseField;
	}
	
	
}
