package com.kroger.pharmacy.csr.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;

@Entity
@Table(
        name = "CSR_RXFILL_EXTRACT",
        schema = "TREXONE_DW_DATA"
)
public class RxExtract extends SelectableObject {
    private BigDecimal extractKey;
    private BigDecimal rxFillExtractNumber;
    private BigDecimal facilityKey;
    private String facilityNabpNumber;
    private String facilityDeaNumber;
    private String facilityStateLicenseNumber;
    private String facilityState;
    private String facilityName;
    private String facilityPhoneNumber;
    private String facilityNpiNumber;
    private String patientSsn;
    private String patientDriversLicenseNumber;
    private Date patientBirthDate;
    private String patientGender;
    private String patientLocationCode;
    private String patientFirstName;
    private String patientMiddleName;
    private String patientLastName;
    private String patientAddress1;
    private String patientAddress2;
    private String patientCity;
    private String patientState;
    private String patientZipCode;
    private String patientPhoneNumber;
    private String nameOfAnimal;
    private Date rxPrescribedDate;
    private String rxNumber;
    private Integer rxRefillsAuthorized;
    private BigDecimal rxOriginationNumber;
    private String rxDiagnosisCode;
    private Date rxDispenseDate;
    private Integer rxRefillNumber;
    private Integer rxMetricQuantity;
    private Integer rxDaysSupply;
    private String treatmentType;
    private String directions;
    private String rxTriplicateSerialNumber;
    private String rxPaymentCode;
    private String productNdc;
    private String prescriberDeaNumber;
    private String prescriberDeaSuffix;
    private String prescriberStateLicenseNumber;
    private String prescriberXDEA;
    private String prescriberState;
    private String prescriberPhoneNumber;
    private String prescriberStateIssuedId;
    private String prescriberNpiNumber;
    private String compoundCode;
    private Date approvalDate;
    private Date reportedDate;
    private Date partitionDate;
    private String approvalStatus;
    private BigDecimal orderNumber;
    private BigDecimal itemSequence;
    private String serialNumberState;
    private String pickupPersonFirstName;
    private String pickupPersonLastName;
    private String pickupPersonIdNumber;
    private String pickupPersonIdTypeCode;
    private String thirdPartyPlanType;
    private String patientNamePrefix;
    private String patientNameSuffix;
    private String patientDriversLicenseState;
    private String patientMilitaryId;
    private BigDecimal patientNum;
    private String patientSpeciesCode;
    private String pickupPersonIdType;
    private String pickupPersonIdIssueState;
    private String pickupPersonRelationship;
    private String pharmacistLastName;
    private String pharmacistFirstName;
    private String prescriberLastName;
    private String prescriberFirstName;
    private String prescriberMiddleName;
    private String partialFillInd;
    private String rxDosageUnit;

    public RxExtract() {
    }

    @Id
    @Column(
            name = "CRE_KEY"
    )
    public BigDecimal getExtractKey() {
        return this.extractKey;
    }

    public void setExtractKey(BigDecimal extractKey) {
        this.extractKey = extractKey;
    }

    //TH02
    @Column(
            name = "CSR_RXFILL_EXTRACT_NUM"
    )
    public BigDecimal getRxFillExtractNumber() {
        return this.rxFillExtractNumber;
    }

    public void setRxFillExtractNumber(BigDecimal rxFillExtractNumber) {
        this.rxFillExtractNumber = rxFillExtractNumber;
    }

    //key value... not currently mapped to a segment and not in UI
    @Column(
            name = "FACILITY_KEY"
    )
    public BigDecimal getFacilityKey() {
        return this.facilityKey;
    }

    public void setFacilityKey(BigDecimal facilityKey) {
        this.facilityKey = facilityKey;
    }

    //PHA02
    @Column(
            name = "CRE_FACILITY_NABP_NUMBER",
            length = 25
    )
    public String getFacilityNabpNumber() {
        return this.facilityNabpNumber;
    }

    public void setFacilityNabpNumber(String facilityNabpNumber) {
        this.facilityNabpNumber = facilityNabpNumber;
    }

    //PHA03
    @Column(
            name = "CRE_FACILITY_DEA_NUMBER",
            length = 25
    )
    public String getFacilityDeaNumber() {
        return this.facilityDeaNumber;
    }

    public void setFacilityDeaNumber(String facilityDeaNumber) {
        this.facilityDeaNumber = facilityDeaNumber;
    }

    //PHA13
    @Column(
            name = "CRE_FACILITY_STATE_LICENSE_NUM",
            length = 25
    )
    public String getFacilityStateLicenseNumber() {
        return this.facilityStateLicenseNumber;
    }

    public void setFacilityStateLicenseNumber(String facilityStateLicenseNumber) {
        this.facilityStateLicenseNumber = facilityStateLicenseNumber;
    }

    //not currently mapped to a segment but IS in UI
    @Column(
            name = "CRE_FACILITY_STATE",
            length = 2
    )
    public String getFacilityState() {
        return this.facilityState;
    }

    public void setFacilityState(String facilityState) {
        this.facilityState = facilityState;
    }

    //not currently mapped to a segment but IS in UI
    @Column(
            name = "CRE_FACILITY_NAME",
            length = 30
    )
    public String getFacilityName() {
        return this.facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    //PHA10
    @Column(
            name = "CRE_FACILITY_PHONE_NUMBER",
            length = 10
    )
    public String getFacilityPhoneNumber() {
        return this.facilityPhoneNumber;
    }

    public void setFacilityPhoneNumber(String facilityPhoneNumber) {
        this.facilityPhoneNumber = facilityPhoneNumber;
    }

    //PHA01
    @Column(
            name = "CRE_FACILITY_NPI_NUMBER",
            length = 10
    )
    public String getFacilityNpiNumber() {
        return this.facilityNpiNumber;
    }

    public void setFacilityNpiNumber(String facilityNpiNumber) {
        this.facilityNpiNumber = facilityNpiNumber;
    }

    //PAT03 possibility
    @Column(
            name = "CRE_PATIENT_SSN",
            length = 9
    )
    public String getPatientSsn() {
        return this.patientSsn;
    }

    public void setPatientSsn(String patientSsn) {
        this.patientSsn = patientSsn;
    }

    //PAT03 possibility
    @Column(
            name = "CRE_PTNT_DRIVERS_LICENSE_NUM",
            length = 15
    )
    public String getPatientDriversLicenseNumber() {
        return this.patientDriversLicenseNumber;
    }

    public void setPatientDriversLicenseNumber(String patientDriversLicenseNumber) {
        this.patientDriversLicenseNumber = patientDriversLicenseNumber;
    }

    //PAT18
    @Column(
            name = "CRE_PATIENT_DATE_OF_BIRTH"
    )
    public Date getPatientBirthDate() {
        return this.patientBirthDate;
    }

    public void setPatientBirthDate(Date patientBirthDate) {
        this.patientBirthDate = patientBirthDate;
    }

    //PAT19
    @Column(
            name = "CRE_PATIENT_GENDER",
            length = 1
    )
    public String getPatientGender() {
        return this.patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    //PAT21
    @Column(
            name = "CRE_PATIENT_LOCATION_CODE",
            length = 2
    )
    public String getPatientLocationCode() {
        return this.patientLocationCode;
    }

    public void setPatientLocationCode(String patientLocationCode) {
        this.patientLocationCode = patientLocationCode;
    }

    //PAT08
    @Column(
            name = "CRE_PATIENT_FIRST_NAME",
            length = 30
    )
    public String getPatientFirstName() {
        return this.patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    //PAT09
    @Column(
            name = "CRE_PATIENT_MIDDLE_INITIAL",
            length = 30
    )
    public String getPatientMiddleName() {
        return this.patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    //PAT07
    @Column(
            name = "CRE_PATIENT_LAST_NAME",
            length = 30
    )
    public String getPatientLastName() {
        return this.patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    //PAT12
    @Column(
            name = "CRE_PATIENT_ADDRESS_1",
            length = 100
    )
    public String getPatientAddress1() {
        return this.patientAddress1;
    }

    public void setPatientAddress1(String patientAddress1) {
        this.patientAddress1 = patientAddress1;
    }

    //PAT13
    @Column(
            name = "PATIENT_ADDRESS_2",
            length = 100
    ) //database column does NOT have "CRE_" in front of it for this field.

    public String getPatientAddress2() {
        return this.patientAddress2;
    }

    public void setPatientAddress2(String patientAddress2) {
        this.patientAddress2 = patientAddress2;
    }

    //PAT14
    @Column(
            name = "CRE_PATIENT_CITY",
            length = 20
    )
    public String getPatientCity() {
        return this.patientCity;
    }

    public void setPatientCity(String patientCity) {
        this.patientCity = patientCity;
    }

    //PAT15
    @Column(
            name = "CRE_PATIENT_STATE",
            length = 2
    )
    public String getPatientState() {
        return this.patientState;
    }

    public void setPatientState(String patientState) {
        this.patientState = patientState;
    }

    //PAT16
    @Column(
            name = "CRE_PATIENT_ZIP_CODE",
            length = 20
    )
    public String getPatientZipCode() {
        return this.patientZipCode;
    }

    public void setPatientZipCode(String patientZipCode) {
        this.patientZipCode = patientZipCode;
    }

    //PAT17
    @Column(
            name = "CRE_PATIENT_PHONE_NUMBER",
            length = 10
    )
    public String getPatientPhoneNumber() {
        return this.patientPhoneNumber;
    }

    public void setPatientPhoneNumber(String patientPhoneNumber) {
        this.patientPhoneNumber = patientPhoneNumber;
    }

    //PAT23
    @Column(
            name = "NAME_OF_ANIMAL",
            length = 10
    )
    public String getNameOfAnimal() {
        return this.nameOfAnimal;
    }

    public void setNameOfAnimal(String nameOfAnimal) {
        this.nameOfAnimal = nameOfAnimal;
    }

    //DSP03
    @Column(
            name = "CRE_RX_PRESCRIBED_DATE"
    )
    public Date getRxPrescribedDate() {
        return this.rxPrescribedDate;
    }

    public void setRxPrescribedDate(Date rxPrescribedDate) {
        this.rxPrescribedDate = rxPrescribedDate;
    }

    //DSP02 - in UI but not editable (used in header for info purposes only)
    @Column(
            name = "CRE_RX_NUMBER",
            length = 20
    )
    public String getRxNumber() {
        return this.rxNumber;
    }

    public void setRxNumber(String rxNumber) {
        this.rxNumber = rxNumber;
    }

    //DSP04
    @Column(
            name = "CRE_RX_REFILLS_AUTHORIZED"
    )
    public Integer getRxRefillsAuthorized() {
        return this.rxRefillsAuthorized;
    }

    public void setRxRefillsAuthorized(Integer rxRefillsAuthorized) {
        this.rxRefillsAuthorized = rxRefillsAuthorized;
    }

    //DSP12 but not in UI
    @Column(
            name = "CRE_RX_ORIGINATION_NUM"
    )
    public BigDecimal getRxOriginationNumber() {
        return this.rxOriginationNumber;
    }

    public void setRxOriginationNumber(BigDecimal rxOriginationNumber) {
        this.rxOriginationNumber = rxOriginationNumber;
    }

    //DSP25
    @Column(
            name = "CRE_RX_DIAGNOSIS_CODE",
            length = 30
    )
    public String getRxDiagnosisCode() {
        return this.rxDiagnosisCode;
    }

    public void setRxDiagnosisCode(String rxDiagnosisCode) {
        this.rxDiagnosisCode = rxDiagnosisCode;
    }

    //DSP05
    @Column(
            name = "CRE_RX_FILL_DISPENSE_DATE"
    )
    public Date getRxDispenseDate() {
        return this.rxDispenseDate;
    }

    public void setRxDispenseDate(Date rxDispenseDate) {
        this.rxDispenseDate = rxDispenseDate;
    }

    //DSP06
    @Column(
            name = "CRE_RX_FILL_REFILL_NUM"
    )
    public Integer getRxRefillNumber() {
        return this.rxRefillNumber;
    }

    public void setRxRefillNumber(Integer rxRefillNumber) {
        this.rxRefillNumber = rxRefillNumber;
    }

    //DSP09
    @Column(
            name = "CRE_RX_FILL_METRIC_QUANTITY"
    )
    public Integer getRxMetricQuantity() {
        return this.rxMetricQuantity;
    }

    public void setRxMetricQuantity(Integer rxMetricQuantity) {
        this.rxMetricQuantity = rxMetricQuantity;
    }

    //DSP10
    @Column(
            name = "CRE_RX_FILL_DAYS_SUPPLY"
    )
    public Integer getRxDaysSupply() {
        return this.rxDaysSupply;
    }

    public void setRxDaysSupply(Integer rxDaysSupply) {
        this.rxDaysSupply = rxDaysSupply;
    }

    //DSP24
    @Column(
            name = "TREATMENT_TYPE",
            length = 10
    )
    public String getTreatmentType() {
        return this.treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    //DSP23
    @Column(
            name = "DIRECTIONS",
            length = 2000
    )
    public String getDirections() {
        return this.directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    //AIR02
    @Column(
            name = "CRE_RX_FILL_TRPLCT_SERIAL_NUM",
            length = 30
    )
    public String getRxTriplicateSerialNumber() {
        return this.rxTriplicateSerialNumber;
    }

    public void setRxTriplicateSerialNumber(String rxTriplicateSerialNumber) {
        this.rxTriplicateSerialNumber = rxTriplicateSerialNumber;
    }

    //DSP16
    @Column(
            name = "CRE_RX_FILL_PAYMENT_CODE",
            length = 1
    )
    public String getRxPaymentCode() {
        return this.rxPaymentCode;
    }

    public void setRxPaymentCode(String rxPaymentCode) {
        this.rxPaymentCode = rxPaymentCode;
    }

    //DSP08
    @Column(
            name = "CRE_PRODUCT_NDC",
            length = 11
    )
    public String getProductNdc() {
        return this.productNdc;
    }

    public void setProductNdc(String productNdc) {
        this.productNdc = productNdc;
    }

    //PRE02
    @Column(
            name = "CRE_PRESCRIBER_DEA_NUMBER",
            length = 30
    )
    public String getPrescriberDeaNumber() {
        return this.prescriberDeaNumber;
    }

    public void setPrescriberDeaNumber(String prescriberDeaNumber) {
        this.prescriberDeaNumber = prescriberDeaNumber;
    }

    //PRE03
    @Column(
            name = "CRE_PRESCRIBER_DEA_SUFFIX",
            length = 20
    )
    public String getPrescriberDeaSuffix() {
        return this.prescriberDeaSuffix;
    }

    public void setPrescriberDeaSuffix(String prescriberDeaSuffix) {
        this.prescriberDeaSuffix = prescriberDeaSuffix;
    }

    //PRE04
    @Column(
            name = "CRE_PSCBR_STATE_LICENSE_NUM",
            length = 30
    )
    public String getPrescriberStateLicenseNumber() {
        return this.prescriberStateLicenseNumber;
    }

    public void setPrescriberStateLicenseNumber(String prescriberStateLicenseNumber) {
        this.prescriberStateLicenseNumber = prescriberStateLicenseNumber;
    }

    //PRE09
    @Column(
            name = "CRE_PRESCRIBER_NADEAN",
            length = 30
    )
    public String getPrescriberXDEA() {
        return this.prescriberXDEA;
    } //column name is still "NADEAN", but no one calls it that anymore.

    public void setPrescriberXDEA(String prescriberXDEA) {
        this.prescriberXDEA = prescriberXDEA;
    }

    //PRE10 and AIR01
    @Column(
            name = "CRE_PRESCRIBER_STATE_CODE",
            length = 2
    )
    public String getPrescriberState() {
        return this.prescriberState;
    }

    public void setPrescriberState(String prescriberState) {
        this.prescriberState = prescriberState;
    }

    //PRE08
    @Column(
            name = "PRESCRIBER_PHONE_NUMBER",
            length = 10
    )

    public String getPrescriberPhoneNumber() {
        return this.prescriberPhoneNumber;
    }

    public void setPrescriberPhoneNumber(String prescriberPhoneNumber) {
        this.prescriberPhoneNumber = prescriberPhoneNumber;
    }

    //not currently mapped to a segment but IS in UI
    @Column(
            name = "CRE_PRESCRIBER_STATE_ISSUED_ID",
            length = 30
    )

    public String getPrescriberStateIssuedId() {
        return this.prescriberStateIssuedId;
    }

    public void setPrescriberStateIssuedId(String prescriberStateIssuedId) {
        this.prescriberStateIssuedId = prescriberStateIssuedId;
    }

    //PRE01
    @Column(
            name = "CRE_PRESCRIBER_NPI_NUMBER",
            length = 10
    )
    public String getPrescriberNpiNumber() {
        return this.prescriberNpiNumber;
    }

    public void setPrescriberNpiNumber(String prescriberNpiNumber) {
        this.prescriberNpiNumber = prescriberNpiNumber;
    }

    //DSP07 but not in UI
    @Column(
            name = "CRE_IS_COMPOUND",
            length = 1
    )
    public String getCompoundCode() {
        return this.compoundCode;
    }

    public void setCompoundCode(String compoundCode) {
        this.compoundCode = compoundCode;
    }

    //DSP17
    @Column(
            name = "CRE_APPROVAL_DATE"
    )
    public Date getApprovalDate() {
        return this.approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    //not currently mapped to a segment and not in UI
    @Column(
            name = "CRE_DATE_REPORTED"
    )
    public Date getReportedDate() {
        return this.reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }

    //not currently mapped to a segment and not in UI
    @Column(
            name = "PARTITION_DATE"
    )
    public Date getPartitionDate() {
        return this.partitionDate;
    }

    public void setPartitionDate(Date partitionDate) {
        this.partitionDate = partitionDate;
    }

    //used to derive DSP01 but not in UI
    @Column(
            name = "CRE_APPROVAL_STATUS",
            length = 10
    )
    public String getApprovalStatus() {
        return this.approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    //used in derivation of DSP13 but not in UI
    @Column(
            name = "CRE_ORDER_NUM"
    )
    public BigDecimal getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(BigDecimal orderNumber) {
        this.orderNumber = orderNumber;
    }

    //used in DSP13 derivation, but not in UI
    @Column(
            name = "CRE_ITEM_SEQ"
    )
    public BigDecimal getItemSequence() {
        return this.itemSequence;
    }

    public void setItemSequence(BigDecimal itemSequence) {
        this.itemSequence = itemSequence;
    }

    //AIR08
    @Column(
            name = "CRE_PICKUP_PERSON_FIRST_NAME",
            length = 30
    )
    public String getPickupPersonFirstName() {
        return this.pickupPersonFirstName;
    }

    public void setPickupPersonFirstName(String pickupPersonFirstName) {
        this.pickupPersonFirstName = pickupPersonFirstName;
    }

    //AIR07
    @Column(
            name = "CRE_PICKUP_PERSON_LAST_NAME",
            length = 30
    )
    public String getPickupPersonLastName() {
        return this.pickupPersonLastName;
    }

    public void setPickupPersonLastName(String pickupPersonLastName) {
        this.pickupPersonLastName = pickupPersonLastName;
    }

    //AIR05
    @Column(
            name = "CRE_PICKUP_PERSON_ID_NUMBER",
            length = 20
    )
    public String getPickupPersonIdNumber() {
        return this.pickupPersonIdNumber;
    }

    public void setPickupPersonIdNumber(String pickupPersonIdNumber) {
        this.pickupPersonIdNumber = pickupPersonIdNumber;
    }

    //not currently mapped to a segment and not in UI
    @Column(
            name = "CRE_ASAP_ID_TYPE_CODE",
            length = 2
    )
    public String getPickupPersonIdTypeCode() {
        return this.pickupPersonIdTypeCode;
    }

    public void setPickupPersonIdTypeCode(String pickupPersonIdTypeCode) {
        this.pickupPersonIdTypeCode = pickupPersonIdTypeCode;
    }

    //AIR03
    @Column(
            name = "PICKUP_PERSON_ID_ISSUE_STATE",
            length = 2
    )
    public String getPickupPersonIdIssueState() {
        return this.pickupPersonIdIssueState;
    }

    public void setPickupPersonIdIssueState(String pickupPersonIdIssueState) {
        this.pickupPersonIdIssueState = pickupPersonIdIssueState;
    }

    //not currently mapped to a segment and not in UI
    @Column(
            name = "CRE_TP_PLAN_TYPE",
            length = 10
    )
    public String getThirdPartyPlanType() {
        return this.thirdPartyPlanType;
    }

    public void setThirdPartyPlanType(String thirdPartyPlanType) {
        this.thirdPartyPlanType = thirdPartyPlanType;
    }

    //PAT10
    @Column(
            name = "CRE_PATIENT_NAME_PREFIX",
            length = 10
    )
    public String getPatientNamePrefix() {
        return this.patientNamePrefix;
    }

    public void setPatientNamePrefix(String patientNamePrefix) {
        this.patientNamePrefix = patientNamePrefix;
    }

    //PAT11
    @Column(
            name = "CRE_PATIENT_NAME_SUFFIX",
            length = 10
    )
    public String getPatientNameSuffix() {
        return this.patientNameSuffix;
    }

    public void setPatientNameSuffix(String patientNameSuffix) {
        this.patientNameSuffix = patientNameSuffix;
    }

    //not currently mapped to a segment but IS in UI
    @Column(
            name = "CRE_PAT_DRIVER_LICENSE_STATE",
            length = 2
    )
    public String getPatientDriversLicenseState() {
        return this.patientDriversLicenseState;
    }

    public void setPatientDriversLicenseState(String patientDriversLicenseState) {
        this.patientDriversLicenseState = patientDriversLicenseState;
    }

    //not currently mapped to a segment but IS in UI
    @Column(
            name = "CRE_PATIENT_MILITARY_ID",
            length = 20
    )
    public String getPatientMilitaryId() {
        return this.patientMilitaryId;
    }

    public void setPatientMilitaryId(String patientMilitaryId) {
        this.patientMilitaryId = patientMilitaryId;
    }

    //PAT03 possibility
    @Column(
            name = "CRE_PATIENT_NUM"
    )
    public BigDecimal getPatientNum() {
        return this.patientNum;
    }

    public void setPatientNum(BigDecimal patientNum) {
        this.patientNum = patientNum;
    }

    //PAT20
    @Column(
            name = "CRE_PATIENT_SPECIES_CODE",
            length = 1
    )
    public String getPatientSpeciesCode() {
        return this.patientSpeciesCode;
    }

    public void setPatientSpeciesCode(String patientSpeciesCode) {
        this.patientSpeciesCode = patientSpeciesCode;
    }

    //AIR04
    @Column(
            name = "CRE_PICKUP_PERSON_ID_TYPE",
            length = 2
    )
    public String getPickupPersonIdType() {
        return this.pickupPersonIdType;
    }

    public void setPickupPersonIdType(String pickupPersonIdType) {
        this.pickupPersonIdType = pickupPersonIdType;
    }

    //AIR06
    @Column(
            name = "CRE_PICKUP_PERSON_RELATIONSHIP",
            length = 2
    )
    public String getPickupPersonRelationship() {
        return this.pickupPersonRelationship;
    }

    public void setPickupPersonRelationship(String pickupPersonRelationship) {
        this.pickupPersonRelationship = pickupPersonRelationship;
    }

    //AIR09
    @Column(
            name = "CRE_PHARMACIST_LAST_NAME",
            length = 50
    )
    public String getPharmacistLastName() {
        return this.pharmacistLastName;
    }

    public void setPharmacistLastName(String pharmacistLastName) {
        this.pharmacistLastName = pharmacistLastName;
    }

    //AIR10
    @Column(
            name = "CRE_PHARMACIST_FIRST_NAME",
            length = 50
    )
    public String getPharmacistFirstName() {
        return this.pharmacistFirstName;
    }

    public void setPharmacistFirstName(String pharmacistFirstName) {
        this.pharmacistFirstName = pharmacistFirstName;
    }

    //PRE05
    @Column(
            name = "CRE_PRESCRIBER_LAST_NAME",
            length = 50
    )
    public String getPrescriberLastName() {
        return this.prescriberLastName;
    }

    public void setPrescriberLastName(String prescriberLastName) {
        this.prescriberLastName = prescriberLastName;
    }

    //PRE06
    @Column(
            name = "CRE_PRESCRIBER_FIRST_NAME",
            length = 50
    )
    public String getPrescriberFirstName() {
        return this.prescriberFirstName;
    }

    public void setPrescriberFirstName(String prescriberFirstName) {
        this.prescriberFirstName = prescriberFirstName;
    }

    //PRE07
    @Column(
            name = "CRE_PRESCRIBER_MIDDLE_NAME",
            length = 30
    )
    public String getPrescriberMiddleName() {
        return this.prescriberMiddleName;
    }

    public void setPrescriberMiddleName(String prescriberMiddleName) {
        this.prescriberMiddleName = prescriberMiddleName;
    }

    //DSP13
    @Column(
            name = "CRE_PARTIAL_FILL_IND",
            length = 1
    )
    public String getPartialFillInd() {
        return this.partialFillInd;
    }

    public void setPartialFillInd(String partialFillInd) {
        this.partialFillInd = partialFillInd;
    }

    //DSP11
    @Column(
            name = "CRE_RX_DOSAGE_UNIT",
            length = 20
    )
    public String getRxDosageUnit() {
        return this.rxDosageUnit;
    }

    public void setRxDosageUnit(String rxDosageUnit) {
        this.rxDosageUnit = rxDosageUnit;
    }

    //extra function(s)
    public boolean equals(Object otherObject) {
        return EqualsBuilder.reflectionEquals(this, otherObject);
    }
}
