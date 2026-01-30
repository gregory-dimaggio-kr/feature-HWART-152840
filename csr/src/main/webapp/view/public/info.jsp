<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<%@ page import="java.net.InetAddress" %>
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="System Info" documentType="doc3" documentClass="yui-t7">
	<stripes:layout-component name="navbar">
		<ul class="first-of-type">
			<li index="0" groupindex="0" id="yui-gen0"
				class="yuimenubaritem first-of-type current">
				<a href="#">System Info</a>
			</li>
		</ul>
	</stripes:layout-component>
	<stripes:layout-component name="body">
		<%
		            InetAddress addr = InetAddress.getLocalHost();
		            String hostname = addr.getHostName();
		            String ipAddr = addr.getHostAddress();
		%>
		<table class="report">
			<tr>
				<th align="right">
					Hostname:
				</th>
				<td align="left">
					<%=hostname%>
				</td>
			</tr>
			<tr>
				<th align="right">
					Address:
				</th>
				<td align="left">
					<%=ipAddr%>
				</td>
			</tr>
		</table>
	</stripes:layout-component>
</stripes:layout-render>
