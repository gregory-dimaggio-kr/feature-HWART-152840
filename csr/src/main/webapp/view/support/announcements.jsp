<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="csr Application Announcements" documentType="doc3"
	documentClass="yui-t2" navbarAdminSupportCurrent="current">
	<stripes:layout-component name="sidemenu">
		<stripes:layout-render name="/WEB-INF/jsp/support/sidemenu.jsp"
			adminAdminAnnouncementsCurrent="current" />
	</stripes:layout-component>
	<stripes:layout-component name="head">
		<kroger:includeScript type="text/javascript" context="${styleUrl}"
			src="/fckeditor/fckeditor.js" includeTimestamp="true" />
	</stripes:layout-component>
	<stripes:layout-component name="body">
		<stripes:useActionBean id="announcementActionBean"
			beanclass="com.kroger.commons.web.stripes.AnnouncementActionBean"
			event="getAnnouncements" />
		<c:if test="${! empty announcementActionBean.welcomeMessageText}">
			<b>Welcome Message Preview:</b>
			<div class="page_message info">
				${announcementActionBean.welcomeMessageText}
			</div>
		</c:if>
		<stripes:form action="/Announcement.action">
			<b>Welcome Message</b>
			<script type="text/javascript">
		<!--
		var sBasePath = '${styleUrl}/fckeditor/';
		// The form field name
		var oFCKeditor = new FCKeditor( 'welcomeMessageText' ) ;
		oFCKeditor.BasePath	= sBasePath ;
		oFCKeditor.Height = 100 ;
		oFCKeditor.ToolbarSet = 'Basic' ;
		// Prefill the form_field with some content
		oFCKeditor.Value = '${announcementActionBean.welcomeMessageTextAsJavaScriptVariable}' ;
		oFCKeditor.Create() ;
		//-->
	</script>
			<br/>
			<b>Immediate Announcement</b>
			<script type="text/javascript">
		<!--
		var sBasePath = '${styleUrl}/fckeditor/';
		// The form field name
		var oFCKeditor = new FCKeditor( 'announcementText' ) ;
		oFCKeditor.BasePath	= sBasePath ;
		oFCKeditor.Height = 100 ;
		oFCKeditor.ToolbarSet = 'Basic' ;
		// Prefill the form_field with some content
		oFCKeditor.Value = '${announcementActionBean.announcementTextAsJavaScriptVariable}' ;
		oFCKeditor.Create() ;
		//-->
	</script>
	<div class="actionButtons actionButtonsBottom">
		<stripes:submit name="Save" title="Post Announcements"
				value="Post Announcements"/>
	</div>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>
