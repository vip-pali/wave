package com.wave.daoInterfacesImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wave.daoInterfaces.RefStateManager;
import com.wave.pojo.RefState;
import com.wave.utils.BaseException;

public class RefStateManagerImpl extends HibernateDaoSupport implements RefStateManager {

	public List<RefState> findById(String stateId) throws BaseException {

		String query="from RefState where id=?";
		List<RefState> refStateList = getHibernateTemplate().find(query, stateId);

		return refStateList;
	}

	public List<RefState> findStateList() throws BaseException
	{
		List<RefState> refStateList = getHibernateTemplate().find("from RefState order by stateName");
		return refStateList;
	}

	public RefState findByStateId(String stateId) throws BaseException {
		
		String query="from RefState where id=?";
		List<RefState> refStateList = getHibernateTemplate().find(query,stateId);
		
		if(refStateList !=null && refStateList.size() ==1) {
			
			return refStateList.get(0);
		}
		return null;
		

	}
	
	public List<RefState> findDistinctStateBasedOnCenter() throws BaseException
	
	{
		String query = "from RefState where stateId in(select distinct stateId from RefCenter)";
		
		List<RefState> refStateList = getHibernateTemplate().find(query);
		return refStateList;
	}
}
