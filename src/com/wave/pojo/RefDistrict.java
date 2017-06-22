package com.wave.pojo;

import java.io.Serializable;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class RefDistrict implements Serializable, JSONAware {

	private String stateCode;
	private String districtCode;
	private String name;

	public RefDistrict() {
	}

	public RefDistrict(String stateCode, String districtCode, String name) {

		super();
		this.stateCode = stateCode;
		this.districtCode = districtCode;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toJSONString() {

		JSONObject obj = new JSONObject();
		obj.put("key", districtCode);
		obj.put("value", name);

		return obj.toString();
	}
}
