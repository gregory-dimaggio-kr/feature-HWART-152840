<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld" prefix="kroger"%>
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="csr Application Logging Support" documentType="doc3"
	documentClass="yui-t2" navbarAdminSupportCurrent="current">
	<stripes:layout-component name="sidemenu">
		<stripes:layout-render name="/WEB-INF/jsp/support/sidemenu.jsp"
			adminLoggingPageCurrent="current" />
	</stripes:layout-component>
	<stripes:layout-component name="head">
		<style type="text/css">
	table#LoggerLevels  {
		width: 100%;
	}
	#LoggerLevels input  {
		width: 100%;
	}
	#LoggerLevels th.Logger {
		width: 90%;
	}
	#LoggerLevels th.Level {
		width: 10%;
	}
	
	table#RollingFileAppenders  {
		width: 100%;
	}
	#RollingFileAppenders input  {
		width: 100%;
	}
	#RollingFileAppenders th.Filename {
		width: 90%;
	}
	#RollingFileAppenders th.Backups {
		width: 10%;
	}
	</style>
	</stripes:layout-component>
	<stripes:layout-component name="body">
		<kroger:setLogLevel logger="${param.logger}" level="${param.level}"/>
		<c:set var="setLevelAction"><c:url value="/view/support/logging.jsp"/></c:set>
		<kroger:displayLoggerLevels caption="Logging Levels" action="${setLevelAction}"/>
		<br/>
		<kroger:setRollingFileAppender logger="${param.logger}" appender="${param.appender}" numberOfBackups="${param.backups}"/>
		<c:set var="setAppenderAction"><c:url value="/view/support/logging.jsp"/></c:set>
		<kroger:displayRollingFileAppenders caption="File Appenders" action="${setAppenderAction}"/>
	</stripes:layout-component>
</stripes:layout-render>
