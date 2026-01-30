package com.kroger.pharmacy.csr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CSR_STATE_REPORTING_CONTACT",schema="TREXONE_DW_DATA")
public class Contact extends SelectableObject {
	private Integer id;
	
	private StateReportingDetail stateReportingDetail;
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNumber;
	
	private String emailAddress;
	
	private boolean autoNotified;
	
	private String comments;

	public Contact() {
		super();
	}
	
	public Contact(Integer id, StateReportingDetail stateReportingDetail,
			String firstName, String lastName, String phoneNumber,
			String emailAddress, boolean autoNotified, String comments) {
		super();
		this.id = id;
		this.stateReportingDetail = stateReportingDetail;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.autoNotified = autoNotified;
		this.comments = comments;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="contact_sequence_generator")
	@SequenceGenerator(name="contact_sequence_generator",sequenceName="trexone_dw_data.reporting_contact_seq",initialValue=1,allocationSize=1)
	@Column(name="CSRC_KEY")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="CSRD_KEY",referencedColumnName="CSRD_KEY")
	public StateReportingDetail getStateReportingDetail() {
		return stateReportingDetail;
	}

	public void setStateReportingDetail(StateReportingDetail stateReportingDetail) {
		this.stateReportingDetail = stateReportingDetail;
	}

	@Column(name="FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="PHONE_NUMBER")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name="EMAIL_ADDRESS")
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Column(name="COMMENTS")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name="AUTO_NOTIFY_FLAG")
	public boolean isAutoNotified() {
		return autoNotified;
	}

	public void setAutoNotified(boolean autoNotified) {
		this.autoNotified = autoNotified;
	}
	
	
}
