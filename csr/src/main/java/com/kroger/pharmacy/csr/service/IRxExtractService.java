package com.kroger.pharmacy.csr.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.kroger.commons.calendar.DateRange;
import com.kroger.pharmacy.csr.domain.PartialRxExtract;
import com.kroger.pharmacy.csr.domain.RxExtract;
import com.kroger.pharmacy.csr.domain.RxExtractCorrection;

public interface IRxExtractService {
	public List<PartialRxExtract> searchRxExtracts(String rxNumber, Date dispenseDate, String divisionId, String facilityId, String stateCode, boolean displayCorrected);
	public List<PartialRxExtract> searchRxExtracts(String rxNumber, DateRange beginDate, DateRange endDate, String divisionId, String facilityId, String stateCode, boolean displayCorrected);
	public int countRxExtracts(String rxNumber, DateRange beginDate, DateRange endDate, String divisionId, String facilityId, String stateCode, boolean displayCorrected);
	public RxExtract updateRxExtract(RxExtract rxExtract);
	
	public RxExtract getRxExtract(BigDecimal extractKey);
	
	public RxExtractCorrection persistCorrection(RxExtractCorrection rxExtractCorrection);
	public List<RxExtractCorrection> getCorrections(BigDecimal extractKey);
	public boolean hasUnreportedCorrections(BigDecimal extractKey);
	
	public List<PartialRxExtract> populateChangedFlag(List<PartialRxExtract> rxExtracts);
	
	public String getFacilityIdFromOtherId(String idNumber);
}
