package com.kroger.pharmacy.csr.view.action.csr;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.kroger.commons.web.stripes.AbstractActionBean;
import com.kroger.pharmacy.csr.domain.Report;
import com.kroger.pharmacy.csr.service.ILookupService;
import com.kroger.pharmacy.csr.util.CSRConstants;
import com.techrx.app.trexone.batch.controlledsubstancereporting.CSRRemoteService;

/**
 * Stripes Action Bean for uploadEditedReport.jsp view
 *
 */

@UrlBinding("/UploadEditedReport.action")
public class UploadEditedReportBean extends AbstractActionBean {

	private Integer reportId;

	private String fileDirectory;

	private String fileName;

	private ILookupService lookupService;

	private FileBean editedFile;
	
	private Report selectedReport;

	@SpringBean
	protected void setLookupService(ILookupService lookupService) {
		this.lookupService = lookupService;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public FileBean getEditedFile() {
		return editedFile;
	}

	public void setEditedFile(FileBean editedFile) {
		this.editedFile = editedFile;
	}

	public String getFileDirectory() {
		return fileDirectory;
	}

	public void setFileDirectory(String fileDirectory) {
		this.fileDirectory = fileDirectory;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public Report getSelectedReport() {
		return selectedReport;
	}

	public void setSelectedReport(Report selectedReport) {
		this.selectedReport = selectedReport;
	}
	
	@DefaultHandler
	public Resolution defaultHandler() {
		return new ForwardResolution("/view/csr/csrstatelist.jsp");
	}

	/**
	 * Uploads the file by calling the remote method; uploadReportBytes.
	 * 
	 * @return Resolution
	 * @throws IOException
	 */
	public Resolution uploadFile() throws IOException {
		CSRRemoteService remoteService = null;

		if (editedFile == null || editedFile.getSize() == 0) {
			getContext().saveErrorMessage("Please select a file to upload.");
			return defaultHandler();
		} else {
			byte[] buffer = new byte[1024 * 64];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream inputStream = null;
			Boolean isFileEditSuccessful = Boolean.FALSE;

			try {
				HttpSession session = getContext().getRequest().getSession();
				if (session != null) {
					this.setReportId((Integer) session.getAttribute(CSRConstants.reportId));
					this.setFileDirectory((String) session
							.getAttribute(CSRConstants.fileDirectory));
					this.setFileName((String) session.getAttribute(CSRConstants.fileName));
				}

				if (!this.getFileName().equals(editedFile.getFileName())) {
					getContext()
							.saveErrorMessage(
									"Uploaded file name does not match with original file name.");
					return defaultHandler();
				}

				remoteService = lookupService.lookupCSRRemoteService();

				inputStream = editedFile.getInputStream();

				int read = 0;
				while ((read = inputStream.read(buffer, 0, buffer.length)) != -1) {
					baos.write(buffer, 0, read);
				}

				String dos2UnixFormat = baos.toString()
						.replaceAll("\r\n", "\n");

				isFileEditSuccessful = remoteService.uploadReportBytes(
						dos2UnixFormat.getBytes(), this.getFileDirectory(),
						this.getFileName());

				if (isFileEditSuccessful) {
					getContext().saveInfoMessage(
							"Successfully uploaded the file: "
									+ editedFile.getFileName());
				} else {
					getContext().saveErrorMessage(
							"Error saving the file: "
									+ editedFile.getFileName());
				}
			} catch (Exception e) {
				getContext().saveErrorMessage(e.getMessage());
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		}
		return defaultHandler();
	}

	/**
	 * Initializes report details such as file directory, file name and also
	 * creating session variables. Runs before every Resolution is processed.
	 */

	@Before(stages = LifecycleStage.BindingAndValidation)
	public void init() {
		HttpServletRequest request = getContext().getRequest();
		HttpSession session;

		if (request != null) {
			String reportIdString = request.getParameter(CSRConstants.reportId);
			String fileDirectory = request.getParameter(CSRConstants.fileDirectory);
			String fileName = request.getParameter(CSRConstants.fileName);

			session = request.getSession(true);

			if (reportIdString != null && reportIdString.length() > 0) {
				reportId = new Integer(reportIdString);
				session.setAttribute(CSRConstants.reportId, reportId);
			}

			if (fileDirectory != null && fileDirectory.length() > 0) {
				session.setAttribute(CSRConstants.fileDirectory, fileDirectory);
			}

			if (fileName != null && fileName.length() > 0) {
				session.setAttribute(CSRConstants.fileName, fileName);
			}
		}
	}
}
