package com.wave.pojo;

import java.io.Serializable;
import java.util.Date;

public class StudentPaymentDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String candidateName;
	private String parentName;
	private String address;
	private Long totalCourseFees;
	private Long amountRecieved;
	private Long balanceAmount;
	private Character paymentMode;
	private String bankDetails;
	private Long chequeDDNumber;
	//private Date chequeDated;
	private String chequeDated;
	private String nameOfAuthSign;
	private RefCourse courseId;
	private RefCenter centerId;
	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	
	
//	
//	/**
//	 * @return the chequeDated
//	 */
//	public Date getChequeDated() {
//		return chequeDated;
//	}
//	/**
//	 * @param chequeDated the chequeDated to set
//	 */
//	
//	public void setChequeDated(Date chequeDated) {
//		this.chequeDated = chequeDated;
//	}	
	
	
	
	/**
	 * @return the nameOfAuthSign
	 */
	public String getNameOfAuthSign() {
		return nameOfAuthSign;
	}
	/**
	 * @return the chequeDated
	 */
	public String getChequeDated() {
		return chequeDated;
	}
	/**
	 * @param chequeDated the chequeDated to set
	 */
	public void setChequeDated(String chequeDated) {
		this.chequeDated = chequeDated;
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
	public RefCourse getCourseId() {
		return courseId;
	}
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(RefCourse courseId) {
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
	
	
		

}
