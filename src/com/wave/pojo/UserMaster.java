package com.wave.pojo;

import java.io.Serializable;
import java.util.Date;

public class UserMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3154128780105465879L;
	
	
	private String email;
	private String password;
	private Integer roleId;
	private Integer activeStatus;
	private Date dateTime;
	private RefCenter centerId;
	private String centerCode;
	
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return the activeStatus
	 */
	public Integer getActiveStatus() {
		return activeStatus;
	}
	/**
	 * @param activeStatus the activeStatus to set
	 */
	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}
	/**
	 * @return the dateTime
	 */
	public Date getDateTime() {
		return dateTime;
	}
	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
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
	 * @return the centerCode
	 */
	public String getCenterCode() {
		return centerCode;
	}
	/**
	 * @param centerCode the centerCode to set
	 */
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}
	
	
	
	
	
	

}
