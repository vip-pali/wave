package com.wave.daoInterfaces;

import java.util.List;

import com.wave.pojo.RefState;
import com.wave.utils.BaseException;

public interface RefStateManager {

	public List<RefState> findById(String stateId) throws BaseException;
	
	public List<RefState> findStateList() throws BaseException;
	
	public RefState findByStateId(String stateId) throws BaseException;
	
	public List<RefState> findDistinctStateBasedOnCenter() throws BaseException;

}
