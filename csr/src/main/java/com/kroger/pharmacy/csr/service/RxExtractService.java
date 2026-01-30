package com.kroger.pharmacy.csr.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.kroger.commons.calendar.DateRange;
import com.kroger.pharmacy.csr.domain.PartialRxExtract;
import com.kroger.pharmacy.csr.domain.RxExtract;
import com.kroger.pharmacy.csr.domain.RxExtractCorrection;

public class RxExtractService implements IRxExtractService {
	
protected EntityManager entityManager;
	
	public EntityManager getEntityManager() {
        return entityManager;
    }
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

	public List<PartialRxExtract> searchRxExtracts(String rxNumber, Date dispenseDate, 
			String divisionId, String facilityId, String stateCode, boolean displayCorrected) {
		
		if ( rxNumber == null && dispenseDate == null && facilityId == null )
			return null;
		
		Date nextDate = null;
		boolean and = false;
		String query = "";
		query = "from PartialRxExtract ex where ";
//		if ( displayCorrected )
//			query = "from PartialRxExtract ex where ";
//		else {
//			query = "from PartialRxExtract ex left join RxExtractCorrection rxc where ex.extractKey = rxc.extractKey ";
//			and = true;
//		}
		
		if ( rxNumber != null && rxNumber.length() > 0 ) {
			if ( and )
				query += "and";
			
			query += " ex.rxNumber like '%" + rxNumber + "%' ";
			and = true;
		}
		if ( dispenseDate != null ) {
			Calendar day = new GregorianCalendar();
			day.setTime(dispenseDate);
			
			Calendar nextDay = new GregorianCalendar(day.get(Calendar.YEAR), 
					day.get(Calendar.MONTH), day.get(Calendar.DAY_OF_MONTH));
			nextDay.add(Calendar.DAY_OF_MONTH, 1);
			
			nextDate = nextDay.getTime();
			
			if ( and )
				query += "and";
			
			query += " ex.rxDispenseDate >= ?1 and ex.rxDispenseDate < ?2 ";
			and = true;
		}
		if ( divisionId != null && divisionId.length() > 0 ) {
			if ( and )
				query += "and";
			
			while ( divisionId.length() < 3 )
				divisionId = "0" + divisionId; // pad with zeroes
			
			query += " ex.facility.facilityId like '" + divisionId + "%' ";
			and = true;
		}
		if ( facilityId != null && facilityId.length() > 0 ) {
			if ( and )
				query += "and";
			
			query += " ex.facility.facilityId like '%" + facilityId + "' ";
			and = true;
		}
		if ( stateCode != null && stateCode.length() > 0 ) {
			if ( and )
				query += "and";
			
			query += " ex.facility.state.code = '" + stateCode.toUpperCase() + "' ";
			and = true;
		}
		if ( !displayCorrected ) {
			if ( and )
				query += "and";
			
			query += " not exists (select '1' from RxExtractCorrection rxc where rxc.extractKey = ex.extractKey) ";
		}
		
		query += "order by ex.rxNumber, ex.facility.facilityId, ex.rxDispenseDate, ex.patientLastName, ex.patientFirstName";
		System.out.println(query);
		
		if ( dispenseDate == null ) {
			@SuppressWarnings("unchecked")
			List<PartialRxExtract> results = getEntityManager().createQuery(query).getResultList();
			return results;
		}
		@SuppressWarnings("unchecked")
		List<PartialRxExtract> results = getEntityManager().createQuery(query)
											.setParameter(1, dispenseDate)
											.setParameter(2, nextDate)
											.getResultList();
		return results;
	}
	
	public List<PartialRxExtract> searchRxExtracts(String rxNumber, DateRange beginDate, DateRange endDate, 
			String divisionId, String facilityId, String stateCode, boolean displayCorrected) {
		
		if ( StringUtils.isEmpty(rxNumber) && beginDate == null && StringUtils.isEmpty(facilityId) 
				&& StringUtils.isEmpty(divisionId) && StringUtils.isEmpty(stateCode) )
			return null;
		
		if ( beginDate != null && endDate == null )
			endDate = beginDate;
		
		boolean and = false;
		String query = "";
		query = "from PartialRxExtract ex where ";
//		if ( displayCorrected )
//			query = "from PartialRxExtract ex where ";
//		else {
//			query = "from PartialRxExtract ex left join RxExtractCorrection rxc where ex.extractKey = rxc.extractKey ";
//			and = true;
//		}
		
		if ( rxNumber != null && rxNumber.length() > 0 ) {
			if ( and )
				query += "and";
			
			query += " ex.rxNumber like '%" + rxNumber + "%' ";
			and = true;
		}
		if ( beginDate != null && endDate != null ) {
			if ( and )
				query += "and";
			
			query += " ex.rxDispenseDate between ?1 and ?2 ";
			and = true;
		}
		if ( divisionId != null && divisionId.length() > 0 ) {
			if ( and )
				query += "and";
			
			while ( divisionId.length() < 3 )
				divisionId = "0" + divisionId; // pad with zeroes
			
			query += " ex.facility.facilityId like '" + divisionId + "%' ";
			and = true;
		}
		if ( facilityId != null && facilityId.length() > 0 ) {
			if ( and )
				query += "and";
			
			query += " ex.facility.facilityId like '%" + facilityId + "' ";
			and = true;
		}
		if ( stateCode != null && stateCode.length() > 0 ) {
			if ( and )
				query += "and";
			
			query += " ex.facility.state.code = '" + stateCode.toUpperCase() + "' ";
			and = true;
		}
		if ( !displayCorrected ) {
			if ( and )
				query += "and";
			
			query += " not exists (select '1' from RxExtractCorrection rxc where rxc.extractKey = ex.extractKey) ";
		}
		
		query += "order by ex.facility.state.name, ex.rxNumber, ex.facility.facilityId, ex.rxDispenseDate, ex.patientLastName, ex.patientFirstName";
		System.out.println(query);
		
		if ( beginDate == null ) {
			@SuppressWarnings("unchecked")
			List<PartialRxExtract> results = getEntityManager().createQuery(query).getResultList();
			return results;
		}

		@SuppressWarnings("unchecked")
		List<PartialRxExtract> results = getEntityManager().createQuery(query)
											.setParameter(1, beginDate.getBeginDate())
											.setParameter(2, endDate.getEndDate())
											.getResultList();
		return results;
	}

	@Transactional(readOnly=false)
	public RxExtract updateRxExtract(RxExtract rxExtract) {
		RxExtract mergedExtract = getEntityManager().merge(rxExtract);
		getEntityManager().persist(mergedExtract);
		getEntityManager().flush();
		
		return mergedExtract;
	}

	public RxExtract getRxExtract(BigDecimal extractKey) {
		return getEntityManager().find(RxExtract.class, extractKey);
	}

	@Transactional(readOnly=false)
	public RxExtractCorrection persistCorrection(
			RxExtractCorrection rxExtractCorrection) {
		
		getEntityManager().persist(rxExtractCorrection);
		getEntityManager().flush();
		
		return rxExtractCorrection;
	}

	public List<RxExtractCorrection> getCorrections(BigDecimal extractKey) {
		@SuppressWarnings("unchecked")
		List<RxExtractCorrection> resultList = getEntityManager().createQuery(
				"from RxExtractCorrection rxc where rxc.extractKey = ?1").setParameter(1, extractKey).getResultList();
		
		return resultList;
	}

	public boolean hasUnreportedCorrections(BigDecimal extractKey) {
		List<RxExtractCorrection> corrections = getCorrections(extractKey);
		return ( corrections != null && corrections.size() > 0 );
	}

	public List<PartialRxExtract> populateChangedFlag(
			List<PartialRxExtract> rxExtracts) {
		
		List<PartialRxExtract> result = new ArrayList<PartialRxExtract>(rxExtracts.size());
		
		for ( PartialRxExtract rxExtract : rxExtracts ) {
			rxExtract.setChanged(hasUnreportedCorrections(rxExtract.getExtractKey()));
			result.add(rxExtract);
		}
		
		return result;
	}

	public int countRxExtracts(String rxNumber, DateRange beginDate,
			DateRange endDate, String divisionId, String facilityId,
			String stateCode, boolean displayCorrected) {
		
		if ( StringUtils.isEmpty(rxNumber) && beginDate == null && StringUtils.isEmpty(facilityId) 
				&& StringUtils.isEmpty(divisionId) && StringUtils.isEmpty(stateCode) )
			return Integer.MAX_VALUE;
		
		if ( beginDate != null && endDate == null )
			endDate = beginDate;
		
		boolean and = false;
		String query = "";
		query = "select count(ex.id) from PartialRxExtract ex where ";
//		if ( displayCorrected )
//			query = "from PartialRxExtract ex where ";
//		else {
//			query = "from PartialRxExtract ex left join RxExtractCorrection rxc where ex.extractKey = rxc.extractKey ";
//			and = true;
//		}
		
		if ( rxNumber != null && rxNumber.length() > 0 ) {
			if ( and )
				query += "and";
			
			query += " ex.rxNumber like '%" + rxNumber + "%' ";
			and = true;
		}
		if ( beginDate != null && endDate != null ) {
			if ( and )
				query += "and";
			
			query += " ex.rxDispenseDate between ?1 and ?2 ";
			and = true;
		}
		if ( divisionId != null && divisionId.length() > 0 ) {
			if ( and )
				query += "and";
			
			while ( divisionId.length() < 3 )
				divisionId = "0" + divisionId; // pad with zeroes
			
			query += " ex.facility.facilityId like '" + divisionId + "%' ";
			and = true;
		}
		if ( facilityId != null && facilityId.length() > 0 ) {
			if ( and )
				query += "and";
			
			query += " ex.facility.facilityId like '%" + facilityId + "' ";
			and = true;
		}
		if ( stateCode != null && stateCode.length() > 0 ) {
			if ( and )
				query += "and";
			
			query += " ex.facility.state.code = '" + stateCode.toUpperCase() + "' ";
			and = true;
		}
		if ( !displayCorrected ) {
			if ( and )
				query += "and";
			
			query += " not exists (select '1' from RxExtractCorrection rxc where rxc.extractKey = ex.extractKey) ";
		}
		
		System.out.println(query);
		
		Query q = entityManager.createQuery(query);
		
		if ( beginDate == null ) {
			Long count = (Long)q.getSingleResult();
			return count.intValue();
		}
		
		q.setParameter(1, beginDate.getBeginDate());
		q.setParameter(2, endDate.getEndDate());
				
		Long count = (Long)q.getSingleResult();
		return count.intValue();
	}

	public String getFacilityIdFromOtherId(String idNumber) {
		String q = "select pf.facilityId from PartialFacility pf, FacilityId fi where pf.facilityNum = fi.facilityNum and fi.idValue = ?1";
		getEntityManager().createQuery(q).setParameter(1, idNumber);
		
//		String queryString = "select f.FD_FACILITY_ID from trexone_dw_data.facility f " +
//				"inner join trexone_dw_data.facility_id fid on fid.FD_FACILITY_NUM = f.FD_FACILITY_NUM where fid.FD_VALUE = ? ";
//		
//		Query query = getJpaTemplate().getEntityManagerFactory().createEntityManager()
//				.createNativeQuery(queryString);
//		query.setParameter(1, idNumber);
		
		String result = null;
		try {
			@SuppressWarnings("unchecked")
			List<String> resultList = getEntityManager().createQuery(q).setParameter(1, idNumber).getResultList();
			if ( resultList.size() > 0 )
				result = resultList.get(0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if ( result == null ) {
//			queryString = "select distinct f.FD_FACILITY_ID from trexone_dw_data.facility f " +
//					"where f.FD_DEA_NUMBER = ? ";
//			
//			query = getJpaTemplate().getEntityManagerFactory().createEntityManager()
//					.createNativeQuery(queryString);
//			query.setParameter(1, idNumber);
			
			q = "select pf.facilityId from PartialFacility pf where pf.deaNumber = ?1";
			
			try {
				@SuppressWarnings("unchecked")
				List<String> resultList = getEntityManager().createQuery(q).setParameter(1, idNumber).getResultList();
				if ( resultList.size() > 0 )
					result = resultList.get(0);
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
