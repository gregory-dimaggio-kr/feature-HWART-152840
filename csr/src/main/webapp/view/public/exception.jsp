<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<%@ page session="false" %>
<head>
<title>Application Exception</title>
</head>
<body>
<script language="JavaScript">
if (window.opener && window.opener.exception) {
	document.writeln("<pre>");
	document.writeln(window.opener.exception);
	document.writeln("</pre>");
}
else {
	document.writeln("No exception details are available.");
}
</script>
</body>
</html>
