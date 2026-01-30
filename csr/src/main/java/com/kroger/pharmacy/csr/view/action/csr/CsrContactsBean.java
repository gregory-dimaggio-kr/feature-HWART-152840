package com.kroger.pharmacy.csr.view.action.csr;

import java.util.List;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;

//import com.kroger.commons.web.datatable.DataTable;
import com.kroger.commons.web.stripes.AbstractActionBean;
import com.kroger.pharmacy.csr.domain.Contact;
import com.kroger.pharmacy.csr.domain.StateReportingDetail;
import com.kroger.pharmacy.csr.domain.StateReportingEmailType;
import com.kroger.pharmacy.csr.service.ICsrService;
import com.kroger.pharmacy.csr.service.IReportService;

@UrlBinding("/CsrContacts.action")
public class CsrContactsBean extends AbstractActionBean {
	private List<StateReportingEmailType> emailTypes;
	
	private List<Contact> contacts;
	
	private StateReportingDetail stateReportingDetail;
	
	private ICsrService csrService;
	
	private Integer selectedContactId;
	
	private IReportService reportService;
	
	@SpringBean
	protected void setCsrService(ICsrService csrService) {
		this.csrService = csrService;
	}

	@SpringBean
	protected void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}

	public List<StateReportingEmailType> getEmailTypes() {
		return emailTypes;
	}

	public void setEmailTypes(List<StateReportingEmailType> emailTypes) {
		this.emailTypes = emailTypes;
	}

	public StateReportingDetail getStateReportingDetail() {
		return stateReportingDetail;
	}

	public void setStateReportingDetail(StateReportingDetail stateReportingDetail) {
		this.stateReportingDetail = stateReportingDetail;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Integer getSelectedContactId() {
		return selectedContactId;
	}

	public void setSelectedContactId(Integer selectedContactId) {
		this.selectedContactId = selectedContactId;
	}

	@DefaultHandler
	public Resolution defaultHandler() {
		ForwardResolution resolution = new ForwardResolution("/view/csr/csrcontacts.jsp");
		resolution.addParameter("detailId", stateReportingDetail.getId());
		return resolution;
	}
	
	public Resolution noSelectionMade() {
		getContext().saveInfoMessage("Please select a contact.");
		ForwardResolution resolution = new ForwardResolution("/view/csr/csrcontacts.jsp");
		resolution.addParameter("detailId", stateReportingDetail.getId());
		return resolution;
	}
	
	public Resolution addContact() {
		ForwardResolution resolution = new ForwardResolution("/view/csr/csrcontactdetails.jsp");
		resolution.addParameter("detailId", stateReportingDetail.getId());
		resolution.addParameter("contactId", "");
		resolution.addParameter("contact.firstName", "");
		resolution.addParameter("contact.lastName", "");
		resolution.addParameter("contact.phoneNumber", "");
		resolution.addParameter("contact.emailAddress", "");
		resolution.addParameter("contact.comments", "");
		return resolution;
	}
	
	public Resolution editEmailDetails() {
		ForwardResolution resolution = new ForwardResolution("/view/csr/csremaildetails.jsp");
		resolution.addParameter("detailId", stateReportingDetail.getId());
		return resolution;
	}
	
	public Resolution editContact() {
		if ( selectedContactId == null )
			return noSelectionMade();
		
		ForwardResolution resolution =  new ForwardResolution("/view/csr/csrcontactdetails.jsp");
		resolution.addParameter("contactId", selectedContactId);
		resolution.addParameter("detailId", stateReportingDetail.getId());
		return resolution;
	}
	
	public Resolution deleteContact() {
		if ( selectedContactId == null )
			return noSelectionMade();
		
		Contact contact = csrService.getContact(selectedContactId);
		csrService.removeContact(contact);
		contacts = csrService.getContacts(stateReportingDetail);
		getContext().saveInfoMessage(contact.getFirstName() + " " + contact.getLastName() + " removed.");
		return defaultHandler();
	}
	
	@Before(stages = LifecycleStage.BindingAndValidation)
	public void init() {
		String detailId = getContext().getRequest().getParameter("detailId");
		if ( detailId != null && detailId.length() > 0 )
			stateReportingDetail = csrService.getStateReportingDetail(Integer.parseInt(detailId));
		
		if ( stateReportingDetail == null )
			stateReportingDetail = new StateReportingDetail();
		
		emailTypes = csrService.getStateReportingEmailTypes();
		
		contacts = csrService.getContacts(stateReportingDetail);
	}
}
