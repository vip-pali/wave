package com.wave.daoInterfaces;

import java.util.List;

import com.wave.pojo.UserMaster;
import com.wave.utils.BaseException;

public interface UserMasterManager {

	public List<UserMaster> findAll() throws BaseException;
	
	public List<UserMaster> findByEmailId(String emailId) throws BaseException;
	
	public boolean saveOrUpdate(UserMaster userMaster) throws BaseException;
	
	public boolean isCredentialAlreadyCreated(Integer centerId) throws BaseException;
	
	public UserMaster findByCenterId(Integer centerId) throws BaseException;
	
}
