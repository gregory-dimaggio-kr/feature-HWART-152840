package com.kroger.pharmacy.csr.util;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.apache.commons.lang.StringUtils;

import com.kroger.commons.calendar.DateRange;
import com.kroger.commons.calendar.FiscalDay;
import com.kroger.pharmacy.csr.domain.PartialRxExtract;
import com.kroger.pharmacy.csr.domain.RxExtract;
import com.kroger.pharmacy.csr.service.IRxExtractService;

public class AtlanticAssociatesParser {
	public static final String CORRECTION_DELIMITER = "=";
	
	public static final String REJECT_DATA_DELIMITER = ",";
	
	private Map<String,String> errorMessageMap;
	
	private IRxExtractService rxExtractService;
	
	public void setRxExtractService(IRxExtractService rxExtractService) {
		this.rxExtractService = rxExtractService;
	}

	public AtlanticAssociatesParser() {
		errorMessageMap = new HashMap<String,String>();
		errorMessageMap.put("Error: Dr DPS Number", "prescriberDpsNumber");
		errorMessageMap.put("Error: Triplicate/Control Number", "rxTriplicateSerialNumber");
		errorMessageMap.put("Error: Date Written", "rxPrescribedDate");
		errorMessageMap.put("Error: NDC Number", "productNdc");
	}
	
	public Correction suggestCorrection(String rejectFileLine) {
		Correction suggestion = new Correction();
		
		String reject = null;
		String correction = null;
		
		String[] splitLine = rejectFileLine.split(CORRECTION_DELIMITER);
		if ( splitLine.length > 0 )
			reject = splitLine[0];
		if ( splitLine.length > 1 ) {
			correction = splitLine[splitLine.length-1];
			correction = correction.trim();
		}
		
		if ( !StringUtils.isEmpty(correction) )
			suggestion.setCorrectedValue(correction);
		
		List<String> rejectTokens = Arrays.asList(reject.split(REJECT_DATA_DELIMITER));
		
		String rxDateString = null;
		DateRange rxDate = null;
		String rxNumber = null;
		String idNumber = null;
		String divisionId = null;
		String facilityId = null;
		String dataValue = null;
		String errorField = null;
		
		for ( String token : rejectTokens ) {
			token = token.trim();
			if ( token.startsWith("RX Date ") )
				rxDateString = token.replaceAll("RX Date ", "");
			else if ( token.startsWith("RX ") )
				rxNumber = token.replaceAll("RX ", "");
			else if ( token.startsWith("Data Value:  ") )
				dataValue = token.replaceAll("Data Value:  ", "");
			else if ( token.length() == 8 || token.length() == 9 )
				idNumber = token;
			else {
				for ( String key : errorMessageMap.keySet() ) {
					if ( token.indexOf(key) >= 0 ) {
						errorField = errorMessageMap.get(key);
						break;
					}
				}
			}
		}
		
		if ( idNumber != null ) {
			idNumber = rxExtractService.getFacilityIdFromOtherId(idNumber);
			if ( idNumber != null ) {
				divisionId = idNumber.substring(0,3);
				facilityId = idNumber.substring(3);
			}
		}
		if ( rxDateString != null ) {
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			try {
				rxDate = new FiscalDay(df.parse(rxDateString));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if ( rxNumber == null || rxDate == null ) {
			suggestion.setErrorMessage("Could not find RX # and/or RX date.");
			return suggestion;
		}
		
		//TODO add date back
		List<PartialRxExtract> extracts = rxExtractService.searchRxExtracts(rxNumber, rxDate, null, divisionId, facilityId, null, true);
		
		RxExtract rxExtract = null;
		Method getter = null;
		Method setter = null;
		if ( !StringUtils.isEmpty(errorField) ) {
			String getMethodName = "get" + errorField.substring(0,1).toUpperCase()
					+ errorField.substring(1);
			String setMethodName = "set" + errorField.substring(0,1).toUpperCase()
					+ errorField.substring(1);
			try {
				getter = RxExtract.class.getMethod(getMethodName);
				setter = RxExtract.class.getMethod(setMethodName, getter.getReturnType());
				
				Column column = getter.getAnnotation(Column.class);
				suggestion.setDatabaseField(column.name());
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if ( extracts.size() == 1 )
			rxExtract = rxExtractService.getRxExtract(extracts.get(0).getExtractKey());
		else if ( extracts.size() > 1 ) {
			if ( getter == null ) {
				suggestion.setErrorMessage("Too many extracts returned for RX # " + rxNumber + ", date " + rxDateString);
				return suggestion;
			}
			
			for ( PartialRxExtract extract : extracts ) {
				rxExtract = rxExtractService.getRxExtract(extract.getExtractKey());
				try {
					String value = (String)getter.invoke(rxExtract);
					if ( ( dataValue == null && value == null ) || dataValue.equals(value) )
						break;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rxExtract = null;
			}
			
			if ( rxExtract == null ) {
				suggestion.setErrorMessage("Too many extracts returned for RX # " + rxNumber + ", date " + rxDateString);
				return suggestion;
			}
		}
		
		suggestion.setRxExtract(rxExtract);
		suggestion.setCorrectedField(errorField);
		suggestion.setCorrectedValue(correction);
		
		RxExtract correctedExtract = rxExtract;
		try {
		    if ( correctedExtract != null )
		        setter.invoke(correctedExtract, correction);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		suggestion.setCorrectedRxExtract(correctedExtract);
		
		return suggestion;
	}
}
