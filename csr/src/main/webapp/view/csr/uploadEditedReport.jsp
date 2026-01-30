<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://stripes.sourceforge.net/stripes.tld"
	prefix="stripes"%>
<stripes:layout-render name="/WEB-INF/jsp/layout/layout.jsp"
	pageTitle="Upload Edited CSR Report" documentType="doc3"
	documentClass="yui-t2">
	<stripes:layout-component name="head">
		<script type="text/javascript">
			
			function uploadClick() {
				document.body.style.cursor='wait';
				document.getElementById('loadingPane').className='loadingPane';
				setTimeout('document.images["loadingImage"].src = "<c:url value="${styleUrl}/images/loading.gif"/>"', 10);
			}
		</script>
	</stripes:layout-component>
	<stripes:layout-component name="sidemenu">
		<stripes:layout-render 
					name="/WEB-INF/jsp/csr/sidemenu.jsp"
			runReportPageCurrent="current" />
	</stripes:layout-component>
	<stripes:layout-component name="body">
		
		<stripes:useActionBean id="uploadEditedReportActionBean" 
				beanclass="com.kroger.pharmacy.csr.view.action.csr.UploadEditedReportBean" />
		<stripes:form action="/UploadEditedReport.action"
						name="uploadeditedReportForm" id="stripesForm">
			<div id="editReportPane">
				<table width="100%">
					<caption>Upload Edited CSR Report</caption>
					<tbody>
						<tr>
							<td>
								<stripes:label for="editedFile">Upload edited file</stripes:label>
								<stripes:file name="editedFile"/>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="actionButtons" >
					<stripes:submit name="uploadFile" value="Upload" onclick="uploadClick();" onmouseover="buttonOver(this);" onmouseout="buttonOut(this);" />
					<div id="loadingPane" class="hidden" >
						uploading report... <img id="loadingImage" "<c:url value="${styleUrl}/images/loading.gif"/>" />
					</div>
				</div>
				<div class="noteText">
					Note: Please make sure the uploading file name matches the original file name. For e.g. Original file name: OH-03082017.dat then the uploading file name should also be: OH-03082017.dat.
				</div>
			</div>
		</stripes:form>
	</stripes:layout-component>
</stripes:layout-render>