package com.kroger.pharmacy.csr.view.action.csr;

import java.util.List;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.kroger.commons.web.stripes.AbstractActionBean;
import com.kroger.pharmacy.csr.domain.Contact;
import com.kroger.pharmacy.csr.domain.StateReportingDetail;
import com.kroger.pharmacy.csr.domain.StateReportingEmailType;
import com.kroger.pharmacy.csr.service.ICsrService;
import com.kroger.pharmacy.csr.service.IReportService;

@UrlBinding("/EditEmailDetails.action")
public class EditEmailDetailsBean extends AbstractActionBean {
	private StateReportingDetail stateReportingDetail;
	
	private Integer emailTypeId;
	
	private List<StateReportingEmailType> emailTypes;
	
	private List<Contact> contacts;
	
	private ICsrService csrService;
	
	private IReportService reportService;
	
	@SpringBean
	protected void setCsrService(ICsrService csrService) {
		this.csrService = csrService;
	}

	@SpringBean
	protected void setReportService(IReportService reportService) {
		this.reportService = reportService;
	}
	
	public StateReportingDetail getStateReportingDetail() {
		return stateReportingDetail;
	}

	public void setStateReportingDetail(StateReportingDetail stateReportingDetail) {
		this.stateReportingDetail = stateReportingDetail;
	}

	public List<StateReportingEmailType> getEmailTypes() {
		return emailTypes;
	}

	public void setEmailTypes(List<StateReportingEmailType> emailTypes) {
		this.emailTypes = emailTypes;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public Integer getEmailTypeId() {
		return emailTypeId;
	}

	public void setEmailTypeId(Integer emailTypeId) {
		this.emailTypeId = emailTypeId;
	}

	@DefaultHandler
	public Resolution defaultHandler() {
		return new ForwardResolution("/view/csr/csremaildetails.jsp");
	}
	
	public Resolution submit() {
		stateReportingDetail.setEmailType(
				csrService.getStateReportingEmailType(emailTypeId));
		stateReportingDetail = csrService.persistDetail(stateReportingDetail);
		
		for ( Contact contact : contacts )
			contact = csrService.persistContact(contact);
		
		return forwardToContactList();
	}
	
	public Resolution cancel() {
		return forwardToContactList();
	}
	
	private Resolution forwardToContactList() {
		ForwardResolution resolution = new ForwardResolution("/view/csr/csrcontacts.jsp");
		if ( stateReportingDetail != null )
			resolution.addParameter("detailId", stateReportingDetail.getId());
		
		return resolution;
	}
	
	@Before(stages = LifecycleStage.BindingAndValidation)
	public void init() {
		String detailId = getContext().getRequest().getParameter("detailId");
		if ( detailId != null && detailId.length() > 0 )
			stateReportingDetail = csrService.getStateReportingDetail(new Integer(detailId));
		
		if ( stateReportingDetail == null )
			stateReportingDetail = new StateReportingDetail();
		
		emailTypes = csrService.getStateReportingEmailTypes();
		contacts = csrService.getContacts(stateReportingDetail);
		
		if ( emailTypeId == null && stateReportingDetail.getEmailType() != null )
			emailTypeId = stateReportingDetail.getEmailType().getId();
	}
}
