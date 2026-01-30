package com.kroger.pharmacy.csr.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.kroger.pharmacy.csr.domain.Contact;
import com.kroger.pharmacy.csr.domain.CsrState;
import com.kroger.pharmacy.csr.domain.State;
import com.kroger.pharmacy.csr.domain.StateReportingDetail;
import com.kroger.pharmacy.csr.domain.StateReportingEmailType;
import com.kroger.pharmacy.csr.domain.StateReportingMethod;
import com.kroger.pharmacy.csr.domain.StateReportingSchedule;

public class CsrService implements ICsrService {
	
	protected EntityManager entityManager;
	
	public EntityManager getEntityManager() {
        return entityManager;
    }
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	
	public StateReportingDetail createDetail(
			StateReportingDetail stateReportingDetail) {
		
		//insertCsrStateRecord(stateReportingDetail.getState().getCode());
		return persistDetail(stateReportingDetail);
	}
	
//	private void insertCsrStateRecord(String stateCode) {
//		//EntityManager entityManager = getJpaTemplate().getEntityManagerFactory().createEntityManager(); 
//		Query insertQuery = entityManager.createNativeQuery("insert into trexone_dw_data.csr_state ( cs_state_code, cs_csr_asap_version ) values ( '" + stateCode + "', '1995' );");
//		
//		entityManager.getTransaction().begin();
//		insertQuery.executeUpdate();
//		entityManager.getTransaction().commit();
//	}

	public List<StateReportingDetail> getStateReportingDetails() {
		return getStateReportingDetails("state.name");
	}
	
	public List<StateReportingDetail> getStateReportingDetails(String orderBy) {
		String query = "from StateReportingDetail d";
		if ( orderBy != null && orderBy.length() > 0 ) {
			query += " order by d." + orderBy;
			if ( !orderBy.equals("state.name") )
				query += ", d.state.name";
		}
		@SuppressWarnings("unchecked")
		List<StateReportingDetail> result = getEntityManager().createQuery(query).getResultList();
		
		return result;
	}
	
	public StateReportingDetail getStateReportingDetail(String stateCode) {
		@SuppressWarnings("unchecked")
		List<StateReportingDetail> result = getEntityManager().createQuery("from StateReportingDetail d where d.state.code = ?1")
												.setParameter(1, stateCode)
												.getResultList();
		
		if ( result == null || result.size() == 0 )
			return null;
		
		return result.get(0);
	}
	
	public List<StateReportingDetail> getActiveStateReportingDetails() {
		return getStateReportingDetails(true);
	}
	
	public List<StateReportingDetail> getInactiveStateReportingDetails() {
		return getStateReportingDetails(false);
	}
	
	private List<StateReportingDetail> getStateReportingDetails(boolean activeFlag) {
		@SuppressWarnings("unchecked")
		List<StateReportingDetail> result = getEntityManager().createQuery("from StateReportingDetail d where d.active = ?1 order by d.state.name")
													.setParameter(1, activeFlag)
													.getResultList();
		
		return result;
	}

	public List<StateReportingEmailType> getStateReportingEmailTypes() {
		@SuppressWarnings("unchecked")
		List<StateReportingEmailType> result = getEntityManager().createQuery("from StateReportingEmailType et order by et.id")
													.getResultList();
		
		return result;
	}

	public List<StateReportingMethod> getStateReportingMethods() {
		@SuppressWarnings("unchecked")
		List<StateReportingMethod> result = getEntityManager()
			.createQuery("from StateReportingMethod m order by m.id").getResultList();
		
		return result;
	}

	public List<StateReportingSchedule> getStateReportingSchedules() {
		@SuppressWarnings("unchecked")
		List<StateReportingSchedule> result = getEntityManager()
			.createQuery("from StateReportingSchedule s order by s.id").getResultList();
		
		return result;
	}

	public List<State> getStates() {
		@SuppressWarnings("unchecked")
		List<State> result = getEntityManager().createQuery("select distinct srd.state from StateReportingDetail srd order by srd.state.name").getResultList();
		
		return result;
	}

	@Transactional(readOnly=false)
	public StateReportingDetail persistDetail(
			StateReportingDetail stateReportingDetail) {
		stateReportingDetail = getEntityManager().merge(stateReportingDetail);
		getEntityManager().persist(stateReportingDetail);
		getEntityManager().flush();
		
		return stateReportingDetail;
	}

	@Transactional(readOnly=false)
	public void removeDetail(StateReportingDetail stateReportingDetail) {
		getEntityManager().remove(stateReportingDetail);
		getEntityManager().flush();
	}

	public List<Contact> getContacts(StateReportingDetail stateReportingDetail) {
			@SuppressWarnings("unchecked")
			List<Contact> result = getEntityManager().createQuery("from Contact c where c.stateReportingDetail = ?1 order by c.lastName, c.firstName")
										.setParameter(1, stateReportingDetail)
										.getResultList();
			return result;
	}

	public Contact createContact(Contact contact) {
		return persistContact(contact);
	}

	@Transactional(readOnly=false)
	public Contact persistContact(Contact contact) {
		contact = getEntityManager().merge(contact);
		getEntityManager().persist(contact);
		getEntityManager().flush();
		
		return contact;
	}

	@Transactional(readOnly=false)
	public void removeContact(Contact contact) {
		getEntityManager().remove(contact);
		getEntityManager().flush();
	}

	public StateReportingDetail getStateReportingDetail(Integer id) {
		return getEntityManager().find(StateReportingDetail.class, id);
	}

	public Contact getContact(Integer id) {
		return getEntityManager().find(Contact.class, id);
	}

	public StateReportingEmailType getStateReportingEmailType(Integer id) {
		return getEntityManager().find(StateReportingEmailType.class, id);
	}

	public StateReportingMethod getStateReportingMethod(Integer id) {
		return getEntityManager().find(StateReportingMethod.class, id);
	}

	public StateReportingSchedule getStateReportingSchedule(Integer id) {
		return getEntityManager().find(StateReportingSchedule.class, id);
	}

	@Transactional(readOnly=false)
	public CsrState persistCsrState(CsrState csrState) {
		csrState = getEntityManager().merge(csrState);
		getEntityManager().persist(csrState);
		getEntityManager().flush();
		
		return csrState;
	}
	
	public List<String> getUnusedStateCodes() {
		@SuppressWarnings("unchecked")
		List<String> result = getEntityManager().createQuery("select cs.stateCode from CsrState cs").getResultList();
		
		return result;
	}

	public Date[] getLastReportingInterval(
			StateReportingDetail stateReportingDetail) {

		StateReportingSchedule schedule = stateReportingDetail.getSchedule();
		Date startDate = null;
		Date endDate = null;
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR, calendar.getActualMaximum(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		
		if ( StateReportingSchedule.WEEKLY_SCHEDULE.equals(schedule.getId()) ) {
			calendar.add(Calendar.WEEK_OF_YEAR, -1);
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			startDate = calendar.getTime();
			
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			endDate = calendar.getTime();
		}
		else if ( StateReportingSchedule.MONTHLY_SCHEDULE.equals(schedule.getId()) ) {
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			startDate = calendar.getTime();
			
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			endDate = calendar.getTime();
		}
		
		return new Date[] { startDate, endDate };
	}
	
}
