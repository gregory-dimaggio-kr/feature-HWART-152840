package com.kroger.pharmacy.csr.view.action.csr;

import java.util.List;
import com.kroger.pharmacy.csr.domain.StateReportingDetail;
import com.kroger.pharmacy.csr.service.ICsrService;
import com.kroger.commons.web.stripes.AbstractActionBean;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;


@UrlBinding("/EditRxTab.action")
public class EditRxTabBean extends AbstractActionBean {
    private String currentTabName;
    private ICsrService csrService;
    private List<StateReportingDetail> states;


    public EditRxTabBean() {
    }

    @SpringBean
    protected void setCsrService(ICsrService csrService) {
        this.csrService = csrService;
    }

    public String getCurrentTabName() {
        return this.currentTabName;
    }

    public void setCurrentTabName(String currentTabName) {
        this.currentTabName = currentTabName;
    }

    public Resolution saveCurrentTab() {
        this.getContext().getRequest().getSession().setAttribute("currentTabName", this.currentTabName);
        StreamingResolution resolution = new StreamingResolution("text/text", "success");
        return resolution;
    }

    @Before(
            stages = {LifecycleStage.BindingAndValidation}
    )
    public void init() {
        if (this.getContext().getRequest().getParameter("currentTabName") == null) {
            this.currentTabName = (String)this.getContext().getRequest().getSession().getAttribute("currentTabName");
        }

        this.states = this.csrService.getActiveStateReportingDetails();
    }

    public List<StateReportingDetail> getStates() {
        return this.states;
    }

    public void setStates(List<StateReportingDetail> states) {
        this.states = states;
    }
}
