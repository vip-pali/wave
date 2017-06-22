package com.wave.pojo;

import java.io.Serializable;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class RefState implements Serializable, JSONAware {

	private String stateId;
	private String stateName;

	public RefState() {
	}

	public RefState(String stateId) {
		this.stateId = stateId;
	}

	public RefState(String stateId, String stateName) {
		this.stateId = stateId;
		this.stateName = stateName;
	}

	public String getStateId() {
		return this.stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String toJSONString() {

		JSONObject obj = new JSONObject();
		obj.put("key", stateId);
		obj.put("value", stateName);

		return obj.toString();
	}
}