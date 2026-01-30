package com.kroger.pharmacy.csr.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CRD3591
 * A utility class for looking up state names and codes
 */
public class StateMap {
	private Map<String,String> stateCodeMap;
	
	/**
	 * 
	 */
	public StateMap() {
		stateCodeMap = new HashMap<String,String>();
		initStateMap();
	}
	
	/**
	 * Initializes the object with each state name and code combination
	 */
	private void initStateMap() {
		stateCodeMap.put("IN", "Indiana");
		stateCodeMap.put("OH", "Ohio");
		stateCodeMap.put("CA", "California");
		stateCodeMap.put("AL", "Alabama");
		stateCodeMap.put("AK", "Alaska");
		stateCodeMap.put("AZ", "Arizona");
		stateCodeMap.put("AR", "Arkansas");
		stateCodeMap.put("CO", "Colorado");
		stateCodeMap.put("CT", "Connecticut");
		stateCodeMap.put("DE", "Delaware");
		stateCodeMap.put("FL", "Florida");
		stateCodeMap.put("GA", "Georgia");
		stateCodeMap.put("HI", "Hawaii");
		stateCodeMap.put("ID", "Idaho");
		stateCodeMap.put("IL", "Illinois");
		stateCodeMap.put("IA", "Iowa");
		stateCodeMap.put("KS", "Kansas");
		stateCodeMap.put("KY", "Kentucky");
		stateCodeMap.put("LA", "Louisiana");
		stateCodeMap.put("ME", "Maine");
		stateCodeMap.put("MD", "Maryland");
		stateCodeMap.put("MA", "Massachusetts");
		stateCodeMap.put("MI", "Michigan");
		stateCodeMap.put("MN", "Minnesota");
		stateCodeMap.put("MS", "Mississippi");
		stateCodeMap.put("MO", "Missouri");
		stateCodeMap.put("MT", "Montana");
		stateCodeMap.put("NE", "Nebraska");
		stateCodeMap.put("NV", "Nevada");
		stateCodeMap.put("NH", "New Hampshire");
		stateCodeMap.put("NJ", "New Jersey");
		stateCodeMap.put("NM", "New Mexico");
		stateCodeMap.put("NY", "New York");
		stateCodeMap.put("NC", "North Carolina");
		stateCodeMap.put("ND", "North Dakota");
		stateCodeMap.put("OK", "Oklahoma");
		stateCodeMap.put("OR", "Oregon");
		stateCodeMap.put("PA", "Pennsylvania");
		stateCodeMap.put("RI", "Rhode Island");
		stateCodeMap.put("SC", "South Carolina");
		stateCodeMap.put("SD", "South Dakota");
		stateCodeMap.put("TN", "Tennessee");
		stateCodeMap.put("TX", "Texas");
		stateCodeMap.put("UT", "Utah");
		stateCodeMap.put("VT", "Vermont");
		stateCodeMap.put("VA", "Virginia");
		stateCodeMap.put("WA", "Washington");
		stateCodeMap.put("WV", "West Virginia");
		stateCodeMap.put("WI", "Wisconsin");
		stateCodeMap.put("WY", "Wyoming");
	}
	
	/**
	 * @param stateCode Postal code
	 * @return State name
	 */
	public String getStateName(String stateCode) {
		return stateCodeMap.get(stateCode);
	}
}
