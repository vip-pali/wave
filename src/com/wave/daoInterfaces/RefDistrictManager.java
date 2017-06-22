package com.wave.daoInterfaces;

import java.util.List;

import com.wave.pojo.RefDistrict;
import com.wave.utils.BaseException;


public interface RefDistrictManager
{

	public List<RefDistrict> findByStateId(String stateId) throws BaseException;

	public List<RefDistrict> findAllDistirctList() throws BaseException;

	public List<RefDistrict>findByStateIdAndDistrictId(String stateId, String districtId)throws BaseException;

	public List<RefDistrict> findAllDistirctId(String districtId) throws BaseException;
	
	public RefDistrict findByDistirctId(String districtId) throws BaseException;

	//this method to get district list based on state which are present ref_center table
	public List<RefDistrict>findDistricts(String stateId) throws BaseException;

	
	

}
