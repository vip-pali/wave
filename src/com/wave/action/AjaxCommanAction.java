package com.wave.action;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletResponse;

import org.json.simple.JSONValue;

import com.wave.daoInterfaces.RefCenterManager;
import com.wave.daoInterfaces.RefDistrictManager;
import com.wave.daoInterfaces.RefStateManager;
import com.wave.daoInterfaces.UserMasterManager;
import com.wave.pojo.RefCenter;
import com.wave.pojo.RefDistrict;
import com.wave.pojo.RefState;



public class AjaxCommanAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2238394698859428724L;
	private String stateCode;
	private String districtCode;	
	private List<RefDistrict> refDistrictList;	
	private List<RefState> stateList;	
	private RefDistrictManager refDistrictManager;	
	private List<RefState> RefStateList;	
	private UserMasterManager userMasterManager;
	private RefStateManager refStateManager;
	private RefCenterManager refCenterManager;
	private List<RefCenter> refCenterList;
	
	
	public String getDistrictListForState() {

		try {

			ServletResponse servletRespone = getServletResponse();
			servletRespone.setContentType("application/json; charset=UTF-8");

			PrintWriter out = servletRespone.getWriter();

			Pattern p = Pattern.compile("^[0-9a-zA-Z-,+() ./]*$");
			Matcher m;

			m = p.matcher(stateCode);
			if (!m.matches()) {

				return null;
			}
			else {

				//refDistrictList=getRefDistrictManager().findByStateId(stateCode);
				refDistrictList = refDistrictManager.findByStateId(stateCode);
				Map districtMap = new LinkedHashMap();

				for(int i = 0; i < refDistrictList.size(); i++)
				{
					districtMap.put(refDistrictList.get(i).getDistrictCode(), refDistrictList.get(i).getName());
				}

				out.write(JSONValue.toJSONString(districtMap).toString());
			}
		}
		catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}
	
	//this method to get district list based on state which are present ref_center table 
	public String getDistrictList() {

		try {

			ServletResponse servletRespone = getServletResponse();
			servletRespone.setContentType("application/json; charset=UTF-8");

			PrintWriter out = servletRespone.getWriter();

			Pattern p = Pattern.compile("^[0-9a-zA-Z-,+() ./]*$");
			Matcher m;

			m = p.matcher(stateCode);
			if (!m.matches()) {

				return null;
			}
			else {

				
				refDistrictList = refDistrictManager.findDistricts(stateCode);
				Map districtMap = new LinkedHashMap();

				for(int i = 0; i < refDistrictList.size(); i++)
				{
					districtMap.put(refDistrictList.get(i).getDistrictCode(), refDistrictList.get(i).getName());
				}

				out.write(JSONValue.toJSONString(districtMap).toString());
			}
		}
		catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}
	
	public String getCenterList() {

		try {

			ServletResponse servletRespone = getServletResponse();
			servletRespone.setContentType("application/json; charset=UTF-8");

			PrintWriter out = servletRespone.getWriter();

			Pattern p = Pattern.compile("^[0-9a-zA-Z-,+() ./]*$");
			Matcher m;

			m = p.matcher(stateCode);
			if (!m.matches()) {

				return null;
			}
			else {
				
				refCenterList = refCenterManager.findByStateIdAndDistrictId(stateCode, districtCode);
				Map districtMap = new LinkedHashMap();

				for(int i = 0; i < refCenterList.size(); i++)
				{
					districtMap.put(refCenterList.get(i).getId(), refCenterList.get(i).getName());
				}

				out.write(JSONValue.toJSONString(districtMap).toString());
			}
		}
		catch (Exception e) {

			e.printStackTrace();
		}

		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	/**
	 * @return the districtCode
	 */
	public String getDistrictCode() {
		return districtCode;
	}

	/**
	 * @param districtCode the districtCode to set
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	/**
	 * @return the refDistrictList
	 */
	public List<RefDistrict> getRefDistrictList() {
		return refDistrictList;
	}

	/**
	 * @param refDistrictList the refDistrictList to set
	 */
	public void setRefDistrictList(List<RefDistrict> refDistrictList) {
		this.refDistrictList = refDistrictList;
	}

	/**
	 * @return the stateList
	 */
	public List<RefState> getStateList() {
		return stateList;
	}

	/**
	 * @param stateList the stateList to set
	 */
	public void setStateList(List<RefState> stateList) {
		this.stateList = stateList;
	}

	/**
	 * @return the refDistrictManager
	 */
	public RefDistrictManager getRefDistrictManager() {
		return refDistrictManager;
	}

	/**
	 * @param refDistrictManager the refDistrictManager to set
	 */
	public void setRefDistrictManager(RefDistrictManager refDistrictManager) {
		this.refDistrictManager = refDistrictManager;
	}

	/**
	 * @return the refStateList
	 */
	public List<RefState> getRefStateList() {
		return RefStateList;
	}

	/**
	 * @param refStateList the refStateList to set
	 */
	public void setRefStateList(List<RefState> refStateList) {
		RefStateList = refStateList;
	}	

	/**
	 * @return the userMasterManager
	 */
	public UserMasterManager getUserMasterManager() {
		return userMasterManager;
	}

	/**
	 * @param userMasterManager the userMasterManager to set
	 */
	public void setUserMasterManager(UserMasterManager userMasterManager) {
		this.userMasterManager = userMasterManager;
	}

	/**
	 * @return the refStateManager
	 */
	public RefStateManager getRefStateManager() {
		return refStateManager;
	}

	/**
	 * @param refStateManager the refStateManager to set
	 */
	public void setRefStateManager(RefStateManager refStateManager) {
		this.refStateManager = refStateManager;
	}

	

	/**
	 * @return the refCenterManager
	 */
	public RefCenterManager getRefCenterManager() {
		return refCenterManager;
	}

	/**
	 * @param refCenterManager the refCenterManager to set
	 */
	public void setRefCenterManager(RefCenterManager refCenterManager) {
		this.refCenterManager = refCenterManager;
	}

	/**
	 * @return the refCenterList
	 */
	public List<RefCenter> getRefCenterList() {
		return refCenterList;
	}

	/**
	 * @param refCenterList the refCenterList to set
	 */
	public void setRefCenterList(List<RefCenter> refCenterList) {
		this.refCenterList = refCenterList;
	}
	
	
	

	
	
	
	

	

}
