package com.kroger.pharmacy.csr.view.action.csr;

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
import com.kroger.pharmacy.csr.service.ICsrService;
import com.kroger.pharmacy.csr.service.IReportService;

@UrlBinding("/EditContact.action")
public class EditContactBean extends AbstractActionBean {
	private StateReportingDetail stateReportingDetail;
	
	private Contact contact;
	
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

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@DefaultHandler
	public Resolution defaultHandler() {
		return new ForwardResolution("/view/csr/csrcontactdetails.jsp");
	}
	
	public Resolution submit() {
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
		
		String contactId = getContext().getRequest().getParameter("contactId");
		if ( contactId != null && contactId.length() > 0 )
			contact = csrService.getContact(new Integer(contactId));
		
		if ( contact == null ) {
			contact = new Contact();
			contact.setStateReportingDetail(stateReportingDetail);
		}
	}
}
