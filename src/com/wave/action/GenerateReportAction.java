package com.wave.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.wave.constants.ReportConstants;
import com.wave.daoInterfaces.RefCourseManager;
import com.wave.daoInterfaces.StudentPaymentDetailsManager;
import com.wave.pojo.RefCenter;
import com.wave.pojo.RefCourse;
import com.wave.pojo.StudentPaymentDetails;
import com.wave.utils.Util;
import com.wave.action.BaseAction;

public class GenerateReportAction extends BaseAction implements RequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2545567707002146523L;
	
	protected Map<String, Object> request;
	
	private InputStream fileStream;
	private String contentType;
	private String fileName;
	private String reportFormatType;
	private DataSource dataSource;
	private ServletContext servletContext;
	private Integer studentPaymentId;
	private RefCourseManager refCourseManager;
	private StudentPaymentDetailsManager studentPaymentDetailsManager;
	private List<RefCourse> refCourseList;
	private String candidateName;
	private String parentName;
	private String address;
	private Long totalCourseFees;
	private Long amountRecieved;
	private Long balanceAmount;
	private Character paymentMode;
	private String bankDetails;
	private Long chequeDDNumber;
	private Date chequeDated;
	private String nameOfAuthSign;
	private Integer courseId;
	private RefCenter centerId;
	
	
	// generate student payment receipt
	public String studentPaymentReceipt(){
		
		Map<String, Object> parameters = new HashMap<String, Object>(0);

		try{
			
			refCourseList = refCourseManager.findByStudentRegisteredCourses();
			StudentPaymentDetails studentPaymentDetails = studentPaymentDetailsManager.findById(studentPaymentId);
		
			//parameters.put(ReportConstants.reportGroupTitle, ReportConstants.REPORT_GROUP_TITLE_BASIC_REPORTS + " - " + ReportConstants.REPORT_1);
			parameters.put(ReportConstants.reportTitle, ReportConstants.REPORT_1_TITLE);		
			
			parameters.put("courseName", studentPaymentDetails.getCourseId().getName());
			parameters.put("centerCode", studentPaymentDetails.getCenterId().getCenterCode());
			parameters.put("candiateName", studentPaymentDetails.getCandidateName());
			parameters.put("parentName", studentPaymentDetails.getParentName());
			parameters.put("address", studentPaymentDetails.getAddress());
			parameters.put("totalCourseFees", studentPaymentDetails.getTotalCourseFees().toString());
			parameters.put("amountRecieved", studentPaymentDetails.getAmountRecieved().toString());
			parameters.put("balanceAmount", studentPaymentDetails.getBalanceAmount().toString());
			
			if(studentPaymentDetails.getPaymentMode() == '1'){
				
				parameters.put("paymentMode", "Cash");
				parameters.put("bankDetails", "N/A");
				parameters.put("chequeDDNumber", "N/A");
				parameters.put("chequeDated", "N/A");
				parameters.put("chequeDDnameOfAuthSign", "N/A");
				
			}else if(studentPaymentDetails.getPaymentMode() == '2'){
				
				parameters.put("paymentMode", "A/C Payee Cheque");
				parameters.put("bankDetails", studentPaymentDetails.getBankDetails());
				parameters.put("chequeDDNumber", studentPaymentDetails.getChequeDDNumber().toString());
				parameters.put("chequeDated", studentPaymentDetails.getChequeDated());
				parameters.put("chequeDDnameOfAuthSign", studentPaymentDetails.getNameOfAuthSign());
			
			}else if(studentPaymentDetails.getPaymentMode() == '3'){
				
				parameters.put("paymentMode", "A/C Payee Demand Draft");
				parameters.put("bankDetails", studentPaymentDetails.getBankDetails());
				parameters.put("chequeDDNumber", studentPaymentDetails.getChequeDDNumber().toString());
				parameters.put("chequeDated", studentPaymentDetails.getChequeDated());
				parameters.put("chequeDDnameOfAuthSign", studentPaymentDetails.getNameOfAuthSign());
			
			}else{
				
				addActionError("Error while generating report");
				return ERROR;
			}
			
			//System.out.println("Parameters are: "+parameters);
			
			InputStream jrxmlInputStream = ServletActionContext.getServletContext().getResourceAsStream("/WEB-INF/jrxmls/receipt.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlInputStream);

			reportFormatType = "1";//pdf
			fileStream = exportReport(studentPaymentDetails.getCandidateName(), reportFormatType, jasperReport, parameters);

			return SUCCESS;
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
				
		
		return ERROR;
	}
	
	
	/*
	 * Export report using database connection.
	 */
	private InputStream exportReport(String reportFileName, String reportFormatType, JasperReport jasperReport, Map<String, Object> parameters) {

		return exportReport(null, reportFileName, reportFormatType, jasperReport, parameters);
	}

	/*
	 * Export report using either a database connection or custom data source. To use a database connection pass null in customDataSource parameter.
	 */
	private InputStream exportReport(JRDataSource customDataSource, String reportFileName, String reportFormatType, JasperReport jasperReport, Map<String, Object> parameters) {

		parameters.put(ReportConstants.SUBREPORT_DIR, servletContext.getRealPath(ReportConstants.SUBREPORT_DIR_VALUE) + "/");
		try {

			if(ReportConstants.PDF_EXPORT.equals(reportFormatType)) {

				return exportReportToPDF(customDataSource, reportFileName, jasperReport, parameters);
			}
			else if(ReportConstants.EXCEL_EXPORT.equals(reportFormatType)) {

				return exportReportToExcel(customDataSource, reportFileName, jasperReport, parameters);
			}else if(ReportConstants.CSV_EXPORT.equals(reportFormatType)) {

				return exportReportToCSV(customDataSource, reportFileName, jasperReport, parameters);
			}

		}
		catch (Exception e) {

			e.printStackTrace();		
		}

		return null;
	}

	private InputStream exportReportToPDF(JRDataSource customDataSource, String reportFileName, JasperReport jasperReport, Map<String, Object> parameters) throws JRException, SQLException {

		contentType = ReportConstants.PDF_CONTENT_TYPE;
		fileName = reportFileName + " " + Util.getTimeStamp() + ".pdf";

		byte [] pdfReportBytes = null;

		if(customDataSource == null) {//No custom data source provided, use a database connection

			Connection connection = null;

			try {

				connection = dataSource.getConnection();

				pdfReportBytes = JasperRunManager.runReportToPdf(jasperReport, parameters, connection);
			}
			finally {

				connection.close();
			}
		}
		else {//Use provided custom data source

			pdfReportBytes = JasperRunManager.runReportToPdf(jasperReport, parameters, customDataSource);
		}

		return new ByteArrayInputStream(pdfReportBytes);
	}

	private InputStream exportReportToExcel(JRDataSource customDataSource, String reportFileName, JasperReport jasperReport, Map<String, Object> parameters) throws JRException, SQLException {

		contentType = ReportConstants.EXCEL_XLSX_CONTENT_TYPE;
		fileName = reportFileName + " " + Util.getTimeStamp() + ".xlsx";

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		//Ignore pagination for excel export
		parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);

		JasperPrint jasperPrint = null;

		if(customDataSource == null) {//No custom data source provided, use a database connection

			Connection connection = null;

			try {

				connection = dataSource.getConnection();

				jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
			}
			finally {

				connection.close();
			}
		}
		else {//Use provided custom data source

			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, customDataSource);
		}

		JRXlsxExporter jrXLSXExporter = new JRXlsxExporter();

		jrXLSXExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		jrXLSXExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
		jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
		jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_IMAGE_BORDER_FIX_ENABLED, Boolean.TRUE);
		jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
		jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, true);

		jrXLSXExporter.exportReport();

		return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
	}

	private InputStream exportReportToCSV(JRDataSource customDataSource, String reportFileName, JasperReport jasperReport, Map<String, Object> parameters) throws JRException, SQLException {

		contentType = ReportConstants.CSV_CONTENT_TYPE;
		fileName = reportFileName + " " + Util.getTimeStamp() + ".csv";

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		//Ignore pagination for excel export
		parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);

		JasperPrint jasperPrint = null;
		JRCsvExporter exporter = new JRCsvExporter();

		if(customDataSource == null) {//No custom data source provided, use a database connection

			Connection connection = null;

			//try {

			connection = dataSource.getConnection();



			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
			//}
			/*finally {

				connection.close();
			}*/
		}
		else {//Use provided custom data source

			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, customDataSource);
		}


		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_IMAGE_BORDER_FIX_ENABLED, Boolean.TRUE);

		//exporter.setParameter(JRXlsExporterParameter.MAXIMUM_ROWS_PER_SHEET, 100);

		/* Export the Report */
		exporter.exportReport();




		return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
	}
	
	
	
	/**
	 * @return the request
	 */
	public Map<String, Object> getRequest() {
		return request;
	}
	/**
	 * @param request the request to set
	 */
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	/**
	 * @return the fileStream
	 */
	public InputStream getFileStream() {
		return fileStream;
	}
	/**
	 * @param fileStream the fileStream to set
	 */
	public void setFileStream(InputStream fileStream) {
		this.fileStream = fileStream;
	}
	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}
	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the reportFormatType
	 */
	public String getReportFormatType() {
		return reportFormatType;
	}
	/**
	 * @param reportFormatType the reportFormatType to set
	 */
	public void setReportFormatType(String reportFormatType) {
		this.reportFormatType = reportFormatType;
	}
	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}
	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	/**
	 * @return the servletContext
	 */
	public ServletContext getServletContext() {
		return servletContext;
	}
	/**
	 * @param servletContext the servletContext to set
	 */
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}


	/**
	 * @return the studentPaymentId
	 */
	public Integer getStudentPaymentId() {
		return studentPaymentId;
	}


	/**
	 * @param studentPaymentId the studentPaymentId to set
	 */
	public void setStudentPaymentId(Integer studentPaymentId) {
		this.studentPaymentId = studentPaymentId;
	}


	/**
	 * @return the refCourseManager
	 */
	public RefCourseManager getRefCourseManager() {
		return refCourseManager;
	}


	/**
	 * @param refCourseManager the refCourseManager to set
	 */
	public void setRefCourseManager(RefCourseManager refCourseManager) {
		this.refCourseManager = refCourseManager;
	}


	/**
	 * @return the refCourseList
	 */
	public List<RefCourse> getRefCourseList() {
		return refCourseList;
	}


	/**
	 * @param refCourseList the refCourseList to set
	 */
	public void setRefCourseList(List<RefCourse> refCourseList) {
		this.refCourseList = refCourseList;
	}


	/**
	 * @return the candidateName
	 */
	public String getCandidateName() {
		return candidateName;
	}


	/**
	 * @param candidateName the candidateName to set
	 */
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}


	/**
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}


	/**
	 * @param parentName the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}


	/**
	 * @return the totalCourseFees
	 */
	public Long getTotalCourseFees() {
		return totalCourseFees;
	}


	/**
	 * @param totalCourseFees the totalCourseFees to set
	 */
	public void setTotalCourseFees(Long totalCourseFees) {
		this.totalCourseFees = totalCourseFees;
	}


	/**
	 * @return the amountRecieved
	 */
	public Long getAmountRecieved() {
		return amountRecieved;
	}


	/**
	 * @param amountRecieved the amountRecieved to set
	 */
	public void setAmountRecieved(Long amountRecieved) {
		this.amountRecieved = amountRecieved;
	}


	/**
	 * @return the balanceAmount
	 */
	public Long getBalanceAmount() {
		return balanceAmount;
	}


	/**
	 * @param balanceAmount the balanceAmount to set
	 */
	public void setBalanceAmount(Long balanceAmount) {
		this.balanceAmount = balanceAmount;
	}


	/**
	 * @return the paymentMode
	 */
	public Character getPaymentMode() {
		return paymentMode;
	}


	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(Character paymentMode) {
		this.paymentMode = paymentMode;
	}


	/**
	 * @return the bankDetails
	 */
	public String getBankDetails() {
		return bankDetails;
	}


	/**
	 * @param bankDetails the bankDetails to set
	 */
	public void setBankDetails(String bankDetails) {
		this.bankDetails = bankDetails;
	}


	/**
	 * @return the chequeDDNumber
	 */
	public Long getChequeDDNumber() {
		return chequeDDNumber;
	}


	/**
	 * @param chequeDDNumber the chequeDDNumber to set
	 */
	public void setChequeDDNumber(Long chequeDDNumber) {
		this.chequeDDNumber = chequeDDNumber;
	}


	/**
	 * @return the chequeDated
	 */
	public Date getChequeDated() {
		return chequeDated;
	}


	/**
	 * @param chequeDated the chequeDated to set
	 */
	public void setChequeDated(Date chequeDated) {
		this.chequeDated = chequeDated;
	}


	/**
	 * @return the nameOfAuthSign
	 */
	public String getNameOfAuthSign() {
		return nameOfAuthSign;
	}


	/**
	 * @param nameOfAuthSign the nameOfAuthSign to set
	 */
	public void setNameOfAuthSign(String nameOfAuthSign) {
		this.nameOfAuthSign = nameOfAuthSign;
	}	


	/**
	 * @return the courseId
	 */
	public Integer getCourseId() {
		return courseId;
	}


	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}


	/**
	 * @return the centerId
	 */
	public RefCenter getCenterId() {
		return centerId;
	}


	/**
	 * @param centerId the centerId to set
	 */
	public void setCenterId(RefCenter centerId) {
		this.centerId = centerId;
	}


	/**
	 * @return the studentPaymentDetailsManager
	 */
	public StudentPaymentDetailsManager getStudentPaymentDetailsManager() {
		return studentPaymentDetailsManager;
	}


	/**
	 * @param studentPaymentDetailsManager the studentPaymentDetailsManager to set
	 */
	public void setStudentPaymentDetailsManager(
			StudentPaymentDetailsManager studentPaymentDetailsManager) {
		this.studentPaymentDetailsManager = studentPaymentDetailsManager;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	

}
