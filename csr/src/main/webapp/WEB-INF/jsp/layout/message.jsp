<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<stripes:layout-definition>
	<c:catch>
	<c:if test="${!empty success_messages}">
		<div id="pagemessage" class="page_message success">
			<stripes:messages key="success_messages" />
		</div>
	</c:if>
	<c:if test="${!empty sessionScope.info_messages}">
		<div id="pagemessage" class="page_message info">
			<stripes:messages key="info_messages" />
		</div>
	</c:if>
	<c:if test="${!empty sessionScope.warning_messages}">
		<div id="pagemessage" class="page_message warning">
			<stripes:messages key="warning_messages" />
		</div>
	</c:if>
	<c:if test="${!empty sessionScope.error_messages}">
		<div id="pagemessage" class="page_message error">
			<stripes:messages key="error_messages" />
		</div>
	</c:if>
	</c:catch>
	<c:if test="${!empty sessionScope.saved_exception}">
		<div id="pagemessage" class="page_message">
			<a href="javascript:exception_popup();">More Message Info...</a>
		</div>
		<script type="text/javascript">
function exception_popup () {
	var exception_window = window.open(
		"<c:url value='/view/public/exception.jsp'/>","Exception", "status=no,resizable=yes,top=200,left=200,dependent=yes,alwaysRaised=yes,scrollbars=yes"
	);
	exception_window.opener = window;
	exception_window.focus();
}
<%
Throwable t = (Throwable) session.getAttribute("saved_exception");
out.println("var exception = ");
java.io.StringWriter stringWriter = new java.io.StringWriter();
java.io.PrintWriter printWriter = new java.io.PrintWriter(stringWriter);
t.printStackTrace(printWriter);
java.io.BufferedReader reader = new java.io.BufferedReader(
	new java.io.StringReader(stringWriter.toString()));
String line = reader.readLine();
while (line != null)
{
	out.println("\"" + line.replace("\"", "\\\"") + "\\n\" +");
	line = reader.readLine();
}
out.println("\"\";");
%>
</script>
	</c:if>
	<c:remove var="success_messages" scope="session"/>
	<c:remove var="info_messages" scope="session"/>
	<c:remove var="warning_messages" scope="session"/>
	<c:remove var="error_messages" scope="session"/>
	<c:remove var="saved_exception" scope="session"/>
</stripes:layout-definition>
