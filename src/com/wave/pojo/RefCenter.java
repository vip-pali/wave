package com.wave.pojo;

import java.io.Serializable;

public class RefCenter implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 525915656143146317L;
	
	private Integer id;
	private String name;
	private RefState stateId;
	private RefDistrict districtId;
	private String centerCode;
	
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the stateId
	 */
	public RefState getStateId() {
		return stateId;
	}
	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(RefState stateId) {
		this.stateId = stateId;
	}
	/**
	 * @return the districtId
	 */
	public RefDistrict getDistrictId() {
		return districtId;
	}
	/**
	 * @param districtId the districtId to set
	 */
	public void setDistrictId(RefDistrict districtId) {
		this.districtId = districtId;
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
