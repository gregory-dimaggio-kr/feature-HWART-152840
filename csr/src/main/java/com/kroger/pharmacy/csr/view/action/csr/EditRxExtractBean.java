package com.kroger.pharmacy.csr.view.action.csr;

import com.kroger.commons.security.SecurityBean;
import com.kroger.pharmacy.csr.domain.RxExtract;
import com.kroger.pharmacy.csr.domain.RxExtractCorrection;
import com.kroger.pharmacy.csr.service.IRxExtractService;
import java.math.BigDecimal;
import java.util.Date;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.apache.commons.lang.StringUtils;

@UrlBinding("/EditRxExtracts.action")
public class EditRxExtractBean extends BaseCalendarSupportActionBean {
    private RxExtract rxExtract;
    private String rxNumber;
    private String beginDateString;
    private String endDateString;
    private String facilityId;
    private String divisionId;
    private String displayCorrected;
    private String stateCode;
    private int scrollPosition;
    private int selectedIndex;
    private IRxExtractService rxExtractService;

    public EditRxExtractBean() {
    }

    protected IRxExtractService getRxExtractService() {
        return this.rxExtractService;
    }

    @SpringBean
    protected void setRxExtractService(IRxExtractService rxExtractService) {
        this.rxExtractService = rxExtractService;
    }

    @ValidateNestedProperties({@Validate(
            on = {"submit"}
    )})
    public RxExtract getRxExtract() {
        return this.rxExtract;
    }

    public void setRxExtract(RxExtract rxExtract) {
        this.rxExtract = rxExtract;
    }

    public String getRxNumber() {
        return this.rxNumber;
    }

    public void setRxNumber(String rxNumber) {
        this.rxNumber = rxNumber;
    }

    public String getFacilityId() {
        return this.facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getDivisionId() {
        return this.divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getDisplayCorrected() {
        return this.displayCorrected;
    }

    public void setDisplayCorrected(String displayCorrected) {
        this.displayCorrected = displayCorrected;
    }

    public String getStateCode() {
        return this.stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getBeginDateString() {
        return this.beginDateString;
    }

    public void setBeginDateString(String beginDateString) {
        this.beginDateString = beginDateString;
    }

    public String getEndDateString() {
        return this.endDateString;
    }

    public void setEndDateString(String endDateString) {
        this.endDateString = endDateString;
    }

    public int getScrollPosition() {
        return this.scrollPosition;
    }

    public void setScrollPosition(int scrollPosition) {
        this.scrollPosition = scrollPosition;
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public Date getRxPrescribedDate() {
        return this.rxExtract == null ? null : this.rxExtract.getRxPrescribedDate();
    }

    public void setRxPrescribedDate(Date rxPrescribedDate) {
        if(this.rxExtract != null) {
            this.rxExtract.setRxPrescribedDate(rxPrescribedDate);
        }
    }

    public Date getApprovalDate() {
        return this.rxExtract == null ? null : this.rxExtract.getApprovalDate();
    }

    public void setApprovalDate(Date approvalDate) {
        if(this.rxExtract != null) {
            this.rxExtract.setApprovalDate(approvalDate);
        }
    }


    public Date getRxDispenseDate() {
        return this.rxExtract == null ? null : this.rxExtract.getRxDispenseDate();
    }

    public void setRxDispenseDate(Date rxDispenseDate) {
        if (this.rxExtract != null) {
            this.rxExtract.setRxDispenseDate(rxDispenseDate);
        }

    }

    public Date getPatientBirthDate() {
        return this.rxExtract == null ? null : this.rxExtract.getPatientBirthDate();
    }

    public void setPatientBirthDate(Date patientBirthDate) {
        if (this.rxExtract != null) {
            this.rxExtract.setPatientBirthDate(patientBirthDate);
        }

    }

    public String getFormattedRxPrescribedDate() {
        Date rxPrescribedDate = this.getRxPrescribedDate();
        return rxPrescribedDate == null ? null : DATE_FORMAT.format(rxPrescribedDate);
    }

    public String getFormattedRxDispenseDate() {
        Date rxDispenseDate = this.getRxDispenseDate();
        return rxDispenseDate == null ? null : DATE_FORMAT.format(rxDispenseDate);
    }

    public String getFormattedApprovalDate() {
        Date approvalDate = this.getApprovalDate();
        return approvalDate == null ? null : DATE_FORMAT.format(approvalDate);
    }

    public String getFormattedPatientBirthDate() {
        Date patientBirthDate = this.getPatientBirthDate();
        return patientBirthDate == null ? null : DATE_FORMAT.format(patientBirthDate);
    }

    @DefaultHandler
    public Resolution defaultHandler() {
        return new ForwardResolution("/view/csr/rxextractdetails.jsp");
    }

    public Resolution submit() {
        this.rxExtract = this.rxExtractService.updateRxExtract(this.rxExtract);
        if (!this.rxExtractService.hasUnreportedCorrections(this.rxExtract.getExtractKey())) {
            RxExtractCorrection correction = new RxExtractCorrection();
            correction.setExtractKey(this.rxExtract.getExtractKey());
            correction.setCorrectionDate(new Date());
            correction.setUserEuid((new SecurityBean()).getUsername());
            this.rxExtractService.persistCorrection(correction);
        }

        return this.forwardToExtractSearch();
    }

    public Resolution cancel() {
        return this.forwardToExtractSearch();
    }

    private Resolution forwardToExtractSearch() {
        ForwardResolution resolution = new ForwardResolution("/view/csr/rxextracts.jsp");
        if (this.rxNumber != null) {
            resolution.addParameter("rxNumber", new Object[]{this.rxNumber});
        }

        if (this.facilityId != null) {
            resolution.addParameter("facilityId", new Object[]{this.facilityId});
        }

        if (this.divisionId != null) {
            resolution.addParameter("divisionId", new Object[]{this.divisionId});
        }

        if (this.displayCorrected != null) {
            resolution.addParameter("displayCorrected", new Object[]{this.displayCorrected});
        }

        if (this.stateCode != null) {
            resolution.addParameter("stateCode", new Object[]{this.stateCode});
        }

        if (this.beginDateString != null) {
            resolution.addParameter("beginDateRange", new Object[]{this.beginDateString});
        }

        if (this.endDateString != null) {
            resolution.addParameter("endDateRange", new Object[]{this.endDateString});
        }

        if (this.scrollPosition > 0) {
            resolution.addParameter("scrollPosition", new Object[]{this.scrollPosition});
        }

        if (this.selectedIndex > 0) {
            resolution.addParameter("selectedIndex", new Object[]{this.selectedIndex});
        }

        if (this.rxExtract != null) {
            resolution.addParameter("selectedExtractId", new Object[]{this.rxExtract.getExtractKey()});
        }

        return resolution;
    }

    @Before(
            stages = {LifecycleStage.BindingAndValidation}
    )
    public void init() {
        this.rxNumber = this.getContext().getRequest().getParameter("rxNumber");
        this.facilityId = this.getContext().getRequest().getParameter("facilityId");
        this.divisionId = this.getContext().getRequest().getParameter("divisionId");
        this.displayCorrected = this.getContext().getRequest().getParameter("displayCorrected");
        this.stateCode = this.getContext().getRequest().getParameter("stateCode");
        String scrollString = this.getContext().getRequest().getParameter("scrollPosition");
        if (!StringUtils.isEmpty(scrollString) && scrollString.matches("^[0-9]*")) {
            this.scrollPosition = Integer.parseInt(scrollString);
        }

        String indexString = this.getContext().getRequest().getParameter("selectedIndex");
        if (!StringUtils.isEmpty(indexString)) {
            this.selectedIndex = Integer.parseInt(indexString);
        }

        String extractId = this.getContext().getRequest().getParameter("extractId");
        if (extractId != null) {
            this.rxExtract = this.rxExtractService.getRxExtract(new BigDecimal(extractId));
        }

        this.beginDateString = this.getContext().getRequest().getParameter("beginDateRange");
        this.endDateString = this.getContext().getRequest().getParameter("endDateRange");
    }
}
