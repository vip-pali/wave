package com.wave.daoInterfaces;

import java.util.List;

import com.wave.pojo.RefCenter;
import com.wave.pojo.RefState;
import com.wave.utils.BaseException;

public interface RefCenterManager {

	public List<RefCenter> findAll() throws BaseException;
	
	public RefCenter findByCenterId(Integer centerId) throws BaseException;
	
	public boolean saveOrUpdate(RefCenter refCenter) throws BaseException;
	
	public List<RefCenter> findByCenterName(String centerName) throws BaseException;
	
	public Integer findMaxId() throws BaseException;
	
	public List<RefCenter> findByStateId(String stateId) throws BaseException;
	
	public List<RefCenter> findByStateIdAndDistrictId(String stateId, String districtId) throws BaseException;
	
	
	
	
	
	
}
