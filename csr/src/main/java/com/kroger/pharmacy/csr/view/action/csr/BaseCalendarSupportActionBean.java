package com.kroger.pharmacy.csr.view.action.csr;

import com.kroger.commons.calendar.DateRange;
import com.kroger.commons.web.stripes.AbstractActionBean;
import com.kroger.commons.web.stripes.calendar.DateRangeTypeConverter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sourceforge.stripes.validation.Validate;

public class BaseCalendarSupportActionBean extends AbstractActionBean {
    protected static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    protected static final DateFormat[] ALT_DATE_FORMATS = new DateFormat[]{new SimpleDateFormat("MMddyyyy"), new SimpleDateFormat("MM-dd-yyyy"), new SimpleDateFormat("MM-dd-yyyy h:mm"), new SimpleDateFormat("MMddyyyy h:mm"), new SimpleDateFormat("MM/dd/yyyy h:mm"), new SimpleDateFormat("MMddyyyy"), new SimpleDateFormat("M/d/yyyy"), new SimpleDateFormat("M-d-yyyy h:mm"), new SimpleDateFormat("Mdyyyy h:mm"), new SimpleDateFormat("M/d/yyyy h:mm"), new SimpleDateFormat("Mdyy"), new SimpleDateFormat("M/d/yy"), new SimpleDateFormat("M-d-yy h:mm"), new SimpleDateFormat("Mdyy h:mm"), new SimpleDateFormat("M/d/yy h:mm"), new SimpleDateFormat("MMM d, yyyy"), new SimpleDateFormat("MMM d, yy")};
    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private DateRange beginDateRangeObject;
    private DateRange endDateRangeObject;
    private DateRange rxDispenseDateObject;
    private DateRange patientBirthDateObject;
    private DateRange rxPrescribedDateObject;
    private DateRange approvalDateObject;

    public BaseCalendarSupportActionBean() {
    }

    @Validate(
            converter = DateRangeTypeConverter.class,
            required = false
    )
    public DateRange getBeginDateRangeObject() {
        return this.beginDateRangeObject;
    }

    public void setBeginDateRangeObject(DateRange beginDateRangeObject) {
        this.beginDateRangeObject = beginDateRangeObject;
    }

    @Validate(
            converter = DateRangeTypeConverter.class,
            required = false
    )
    public DateRange getEndDateRangeObject() {
        return this.endDateRangeObject;
    }

    public void setEndDateRangeObject(DateRange endDateRangeObject) {
        this.endDateRangeObject = endDateRangeObject;
    }

    public String getParameterFormattedBeginDateRange() {
        return this.beginDateRangeObject == null ? null : this.dateFormat.format(this.beginDateRangeObject.getBeginDate());
    }

    public String getParameterFormattedEndDateRange() {
        return this.endDateRangeObject == null ? null : this.dateFormat.format(this.endDateRangeObject.getEndDate());
    }

    @Validate(
            converter = DateRangeTypeConverter.class,
            required = false
    )
    public DateRange getRxDispenseDateObject() {
        return this.rxDispenseDateObject;
    }

    public void setRxDispenseDateObject(DateRange rxDispenseDateObject) {
        this.rxDispenseDateObject = rxDispenseDateObject;
    }

    @Validate(
            converter = DateRangeTypeConverter.class,
            required = false
    )
    public DateRange getPatientBirthDateObject() {
        return this.patientBirthDateObject;
    }

    public void setPatientBirthDateObject(DateRange patientBirthDateObject) {
        this.patientBirthDateObject = patientBirthDateObject;
    }

    @Validate(
            converter = DateRangeTypeConverter.class,
            required = false
    )
    public DateRange getRxPrescribedDateObject() {
        return this.rxPrescribedDateObject;
    }

    public void setRxPrescribedDateObject(DateRange rxPrescribedDateObject) {
        this.rxPrescribedDateObject = rxPrescribedDateObject;
    }

    @Validate(
            converter = DateRangeTypeConverter.class,
            required = false
    )
    public DateRange getApprovalDateObject() {
        return this.approvalDateObject;
    }

    public void setApprovalDateObject(DateRange approvalDateObject) {
        this.approvalDateObject = approvalDateObject;
    }

    protected Date parseDispenseDateString(String dateString) {
        Date result = null;

        try {
            result = DATE_FORMAT.parse(dateString);
            return result;
        } catch (ParseException var7) {
            int i = 0;

            while(i < ALT_DATE_FORMATS.length) {
                try {
                    result = ALT_DATE_FORMATS[i].parse(dateString);
                    return result;
                } catch (ParseException var6) {
                    ++i;
                }
            }

            return null;
        }
    }
}
