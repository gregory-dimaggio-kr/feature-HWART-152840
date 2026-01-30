package com.kroger.pharmacy.csr.service;

import java.util.Date;
import java.util.List;

import com.kroger.pharmacy.csr.domain.Contact;
import com.kroger.pharmacy.csr.domain.CsrState;
import com.kroger.pharmacy.csr.domain.State;
import com.kroger.pharmacy.csr.domain.StateReportingDetail;
import com.kroger.pharmacy.csr.domain.StateReportingEmailType;
import com.kroger.pharmacy.csr.domain.StateReportingMethod;
import com.kroger.pharmacy.csr.domain.StateReportingSchedule;

public interface ICsrService {
	public List<StateReportingSchedule> getStateReportingSchedules();
	public List<StateReportingMethod> getStateReportingMethods();
	public List<StateReportingEmailType> getStateReportingEmailTypes();
	public StateReportingSchedule getStateReportingSchedule(Integer id);
	public StateReportingMethod getStateReportingMethod(Integer id);
	public StateReportingEmailType getStateReportingEmailType(Integer id);
	
	public List<State> getStates();
	public List<Contact> getContacts(StateReportingDetail stateReportingDetail);
	
	public List<StateReportingDetail> getStateReportingDetails();
	public List<StateReportingDetail> getStateReportingDetails(String orderBy);
	public StateReportingDetail getStateReportingDetail(String stateCode);
	public List<StateReportingDetail> getActiveStateReportingDetails();
	public List<StateReportingDetail> getInactiveStateReportingDetails();
	public StateReportingDetail getStateReportingDetail(Integer id);
	
	public StateReportingDetail createDetail(StateReportingDetail stateReportingDetail);
	public StateReportingDetail persistDetail(StateReportingDetail stateReportingDetail);
	public void removeDetail(StateReportingDetail stateReportingDetail);
	
	public Contact createContact(Contact contact);
	public Contact persistContact(Contact contact);
	public void removeContact(Contact contact);
	public Contact getContact(Integer id);
	
	public CsrState persistCsrState(CsrState csrState);
	public List<String> getUnusedStateCodes();
	
	public Date[] getLastReportingInterval(StateReportingDetail stateReportingDetail);
}
