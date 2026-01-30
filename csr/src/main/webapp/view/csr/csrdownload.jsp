<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
	
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="Download CSR Reports" documentType="doc3"
	documentClass="yui-t2" navbarPhoneBookCurrent="current">
	<stripes:layout-component name="head">
	</stripes:layout-component>
	<stripes:layout-component name="sidemenu">
		<stripes:layout-render 
                  name="/WEB-INF/jsp/csr/sidemenu.jsp"
			downloadReportPageCurrent="current" />
	</stripes:layout-component>
	<stripes:layout-component name="body">
		
		<stripes:useActionBean id="downloadReportActionBean" event="download"
				beanclass="com.kroger.pharmacy.csr.view.action.csr.DownloadReportBean" />
		<stripes:form action="/DownloadReport.action"
						name="downloadReportForm" id="stripesForm">
			
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>