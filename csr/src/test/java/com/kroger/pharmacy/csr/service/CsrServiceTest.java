package com.kroger.pharmacy.csr.service;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.kroger.pharmacy.csr.AbstractApplicationTestCase;
import com.kroger.pharmacy.csr.domain.Contact;
import com.kroger.pharmacy.csr.domain.State;
import com.kroger.pharmacy.csr.domain.StateReportingDetail;
import com.kroger.pharmacy.csr.domain.StateReportingEmailType;
import com.kroger.pharmacy.csr.domain.StateReportingMethod;
import com.kroger.pharmacy.csr.domain.StateReportingSchedule;

public class CsrServiceTest extends AbstractApplicationTestCase {
	@Test
	public void testCsrService() {
		ICsrService csrService = (ICsrService)applicationContext.getBean("csrService");
		
		List<StateReportingSchedule> schedules = csrService.getStateReportingSchedules();
		List<StateReportingMethod> methods = csrService.getStateReportingMethods();
		List<StateReportingEmailType> emailTypes = csrService.getStateReportingEmailTypes();
		
		StateReportingSchedule schedule = 
			new StateReportingSchedule(StateReportingSchedule.WEEKLY_SCHEDULE, "");
		StateReportingMethod method = 
			new StateReportingMethod(StateReportingMethod.SFTP_METHOD, "");
		StateReportingEmailType emailType = 
			new StateReportingEmailType(StateReportingEmailType.NOTIFICATION_EMAIL_TYPE, "");
		
		Assert.assertTrue(schedules.contains(schedule));
		Assert.assertTrue(methods.contains(method));
		Assert.assertTrue(emailTypes.contains(emailType));
		
		StateReportingDetail detail = new StateReportingDetail();
		State state51 = new State("Imaginationland", "IM");
		detail.setState(state51);
		detail.setSchedule(schedules.get(0));
		detail.setMethod(methods.get(0));
		detail.setEmailType(emailTypes.get(0));
		
		detail = csrService.createDetail(detail);
		Assert.assertNotNull(detail.getId());
		System.out.println(detail.getId());
		
		detail.setActive(true);
		detail.setEmailType(emailTypes.get(1));
		detail = csrService.persistDetail(detail);
		Assert.assertTrue(detail.isActive());
		
		Assert.assertTrue(csrService.getStates().contains(state51));
		
		List<StateReportingDetail> details = csrService.getActiveStateReportingDetails();
		Assert.assertTrue(details.contains(detail));
		details = csrService.getInactiveStateReportingDetails();
		Assert.assertFalse(details.contains(detail));
		
		details = Arrays.asList(new StateReportingDetail[]{csrService.getStateReportingDetail(state51.getCode())});
		Assert.assertTrue(details.contains(detail));
		
		Contact contact = new Contact();
		contact.setStateReportingDetail(detail);
		contact.setFirstName("Mayor");
		contact.setLastName("McCheese");
		contact.setPhoneNumber("8595551212");
		contact.setEmailAddress("mccheese@imaginationland.gov");
		
		contact = csrService.createContact(contact);
		Assert.assertNotNull(contact.getId());
		
		contact.setComments("95");
		contact = csrService.persistContact(contact);
		
		List<Contact> contacts = csrService.getContacts(detail);
		Assert.assertTrue(contacts.contains(contact));
		
		csrService.removeContact(contact);
		
		contacts = csrService.getContacts(detail);
		Assert.assertFalse(contacts.contains(contact));
		
		csrService.removeDetail(detail);
		
		details = csrService.getStateReportingDetails();
		Assert.assertFalse(details.contains(detail));
		
		detail = csrService.getStateReportingDetail(10);
	}
}
