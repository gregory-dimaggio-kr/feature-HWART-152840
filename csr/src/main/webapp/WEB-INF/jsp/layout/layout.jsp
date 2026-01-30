<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ taglib uri="http://web.commons.kroger.com/tlds/taglib.tld"
	prefix="kroger"%>
<jsp:include page="/WEB-INF/jsp/layout/styleUrl.jsp" />
<jsp:useBean id="buildNumber" class="com.kroger.commons.BuildNumber"
	scope="application" />
<stripes:layout-definition>
	<html>
		<head>
			<!-- Build Number: <%=buildNumber.getBuildNumber()%> -->
			<title>${pageTitle}</title>
			<kroger:includeScript type="text/javascript" context="${styleUrl}"
				src="/yui251/yahoo/yahoo-min.js" includeTimestamp="true" />
			<kroger:includeScript type="text/javascript" context="${styleUrl}"
				src="/yui251/dom/dom-min.js" includeTimestamp="true" />
			<kroger:includeScript type="text/javascript" context="${styleUrl}"
				src="/yui251/yahoo-dom-event/yahoo-dom-event.js" includeTimestamp="true" />
			<kroger:includeScript type="text/javascript" context="${styleUrl}"
				src="/yui251/event/event-min.js" includeTimestamp="true" />
			<kroger:includeScript type="text/javascript" context="${styleUrl}"
				src="/yui251/connection/connection-min.js" includeTimestamp="true" />
			<kroger:includeScript type="text/javascript" context="${styleUrl}"
				src="/yui251/dragdrop/dragdrop-min.js" includeTimestamp="true" />
			<kroger:includeScript type="text/javascript" context="${styleUrl}"
				src="/yui251/animation/animation-min.js" includeTimestamp="true" />
			<kroger:includeScript type="text/javascript" context="${styleUrl}"
				src="/yui251/container/container-min.js" includeTimestamp="true" />
			<kroger:includeScript type="text/javascript" context="${styleUrl}"
				src="/yui251/menu/menu-min.js" includeTimestamp="true" />
			<kroger:includeScript type="text/javascript" context="${styleUrl}"
				src="/yui251/element/element-beta-min.js" includeTimestamp="true" />
			<kroger:includeScript type="text/javascript" context="${styleUrl}"
				src="/yui251/button/button-min.js" includeTimestamp="true" />

			<kroger:includeScript type="text/javascript" context="${styleUrl}"
				src="/default/default.js" includeTimestamp="true" />
			<kroger:includeScript type="text/javascript" context="${styleUrl}"
				src="/js/about.js" includeTimestamp="true" />
			<kroger:includeScript type="text/javascript" context="${styleUrl}"
				src="/js/stripesDatatable.js" includeTimestamp="true" />
			<kroger:includeScript type="text/javascript" context="${styleUrl}"
				src="/js/helpOnline.js" includeTimestamp="true"/>
			<kroger:includeScript type="text/javascript" context="${styleUrl}"
				src="/js/bodyResize.js" includeTimestamp="true"/>

			<kroger:includeStyle rel="stylesheet" type="text/css"
				context="${styleUrl}" href="/yui/reset-fonts-grids/reset-fonts-grids.css"
				includeTimestamp="true" />
			<kroger:includeStyle rel="stylesheet" type="text/css"
				context="${styleUrl}" href="/yui/container/assets/container.css"
				includeTimestamp="true" />
			<kroger:includeStyle rel="stylesheet" type="text/css"
				context="${styleUrl}" href="/yui/menu/assets/menu.css"
				includeTimestamp="true" />
			<kroger:includeStyle rel="stylesheet" type="text/css"
				context="${styleUrl}" href="/default/default.css"
				includeTimestamp="true" />
			<kroger:includeStyle rel="stylesheet" type="text/css"
				href="/css/csr.css" media="all"
				includeTimestamp="true" />
			
			<kroger:includeScript type="text/javascript"
				src="/js/csr.js" includeTimestamp="true"/>

			<script type="text/javascript">
				YAHOO.namespace("application.container");
				YAHOO.util.Event.addListener(window, "load", aboutInit);
				//YAHOO.util.Event.addListener(window, "load", __bodyResizeOnLoad);
			</script>

			<stripes:layout-component name="head" />
		</head>
		<body id="kroger" <stripes:layout-component name="bodyAttributes"/> >
			<div id="${documentType}" class="${documentClass}">
				<div id="hd">
					<!-- HEADER BEGIN -->
					<div id="hd_bd">
						<stripes:layout-component name="header">
							<stripes:layout-render name="/WEB-INF/jsp/layout/header.jsp" />
						</stripes:layout-component>
					</div>
					<!-- HEADER END -->
					<div id="menubar_container">
						<div id="mymenubar" class="yuimenubar yuimenubarnav">
							<div class="bd">
								<!-- NAVBAR BEGIN -->
								<stripes:layout-component name="navbar">
									<stripes:layout-render name="/WEB-INF/jsp/layout/navbar.jsp"
										navbarHomeCurrent="${navbarHomeCurrent}"
										navbarAdminSupportCurrent="${navbarAdminSupportCurrent}" />
								</stripes:layout-component>
								<!-- NAVBAR END -->
							</div>
						</div>
					</div>
				</div>
				<div id="bd">
					<div id="yui-main">
						<div class="yui-b">
							<!-- MESSAGE BEGIN -->
							<stripes:layout-component name="message">
								<stripes:layout-render name="/WEB-INF/jsp/layout/message.jsp" />
							</stripes:layout-component>
							<!-- MESSAGE END -->
							<!-- BODY BEGIN -->
							<stripes:layout-component name="body">
							This is the body. Replace me.
							</stripes:layout-component>
							<!-- BODY END -->
						</div>
					</div>
					<div class="yui-b">
						<div id="mymenu" class="yuimenu">
							<div class="bd"><stripes:layout-component name="sidemenu" /></div>
						</div>
					</div>
				</div>
				<div id="ft">
					<div class="links">
						<a href="#hd">&uarr; Return to Top</a>
					</div>
					<div class="nav">
						<br />
					</div>
					<div class="copyright">
						&copy; Copyright 2008 The Kroger Co. All Rights Reserved.
					</div>
				</div>
			</div>
			<div style="width: 500px;" id="aboutPanel">
				<div class="hd">
					About
				</div>
				<div id="about" class="bd"></div>
				<div class="ft"></div>
			</div>
			<stripes:layout-component name="announcement">
			<kroger:announcementDiv />
			</stripes:layout-component>
		</body>
	</html>
</stripes:layout-definition>
