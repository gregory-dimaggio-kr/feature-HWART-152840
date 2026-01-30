<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
  prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
  prefix="kroger"%>
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
  pageTitle="csr Application Preferences" documentType="doc3"
  documentClass="yui-t2" navbarAdminSupportCurrent="current">
  <stripes:layout-component name="sidemenu">
    <stripes:layout-render name="/WEB-INF/jsp/support/sidemenu.jsp"
      adminPreferencesPageCurrent="current" />
  </stripes:layout-component>
  <stripes:layout-component name="head">
    <style type="text/css">
  table#Application_Preferences  {
    width: 100%;
  }
  #Application_Preferences input  {
    width: 100%;
  }
  #Application_Preferences th.Name {
    width: 25%;
  }
  #Application_Preferences th.Description {
    width: 60%;
  }
  #Application_Preferences th.Actions {
    width: 15%;
  }
  #Application_Preferences td {
		vertical-align: top;
	}
  #Application_Preferences_New {
  }
  </style>
  </stripes:layout-component>
  <stripes:layout-component name="body">
    <stripes:useActionBean id="preferencesActionBean"
      beanclass="com.kroger.pharmacy.csr.view.action.preference.PreferencesActionBean"
      event="init" />
    <c:forEach items="${preferencesActionBean.dataTables}" var="dataTable"
      varStatus="loop">
      <stripes:form action="/preference/Preferences.action"
        name="preferencesForm${loop.index}">
        <kroger:stripesDataTable dataTable="${dataTable}"
          formName="preferencesForm${loop.index}"
          actionProperty="preferences" 
          clickToEdit="true" 
          showSuccessIcon="false"/>
        <br />
        <br />
      </stripes:form>
    </c:forEach>
  </stripes:layout-component>
</stripes:layout-render>
