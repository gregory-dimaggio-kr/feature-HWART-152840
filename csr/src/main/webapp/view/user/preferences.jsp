<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="csr Preferences Page" documentType="doc3"
	documentClass="yui-t7" navbarHomeCurrent="current">
	<stripes:layout-component name="head">
		<style type="text/css">
	table#Application_Preferences  {
		width: 60%;
	}
	#Application_Preferences input  {
		width: 100%;
	}
	#Application_Preferences th.Name {
		width: 25%;
	}
	#Application_Preferences th.Value {
		width: 55%;
	}
	#Application_Preferences th.Actions {
		width: 20%;
	}
	#Application_Preferences td {
		vertical-align: top;
	}
	</style>
	</stripes:layout-component>
	<stripes:layout-component name="body">
		<stripes:useActionBean id="preferenceValuesActionBean"
			beanclass="com.kroger.pharmacy.csr.view.action.preference.PreferenceValuesActionBean"
			event="init" />
		<c:forEach items="${preferenceValuesActionBean.dataTables}"
			var="dataTable" varStatus="loop">
			<stripes:form action="/preference/PreferenceValues.action"
				name="preferenceValuesForm${loop.index}">
				<kroger:stripesDataTable dataTable="${dataTable}"
					formName="preferenceValuesForm${loop.index}"
					actionProperty="preferenceValues" clickToEdit="true"
					showSuccessIcon="false" />
				<br />
				<br />
			</stripes:form>
		</c:forEach>
	</stripes:layout-component>
</stripes:layout-render>
