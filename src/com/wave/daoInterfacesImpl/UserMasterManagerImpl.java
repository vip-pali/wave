package com.wave.daoInterfacesImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wave.daoInterfaces.UserMasterManager;
import com.wave.pojo.UserMaster;
import com.wave.utils.BaseException;

public class UserMasterManagerImpl extends HibernateDaoSupport implements UserMasterManager {

	
	public List<UserMaster> findAll() throws BaseException {
		
		List<UserMaster> userMasterList = getHibernateTemplate().find("from UserMaster");
		return userMasterList;
	}
	
	public List<UserMaster> findByEmailId(String emailId) throws BaseException {
		
		String query = "from UserMaster where email = ?";
		
		List<UserMaster> userMasterList = getHibernateTemplate().find(query, emailId);
		return userMasterList;
		
	}

	public boolean saveOrUpdate(UserMaster userMaster) throws BaseException {
		
		boolean res = false;
		
		getHibernateTemplate().saveOrUpdate(userMaster);
		res = true;
		
		return res;
	}
	
	public boolean isCredentialAlreadyCreated(Integer centerId) throws BaseException {
		
		String query = "from UserMaster where centerId.id = ?";
		
		List<UserMaster> userMasterList = getHibernateTemplate().find(query, centerId);
		
		if(userMasterList.size()>0){
			
			return true;
		}
		return false;
		
	}
	
	public UserMaster findByCenterId(Integer centerId) throws BaseException {
		
		String query = "from UserMaster where centerId.id = ?";
		
		List<UserMaster> userMasterList = getHibernateTemplate().find(query, centerId);
		
		if(userMasterList.size() == 1){
			
			return userMasterList.get(0);
		}
		
		return null;
		
	}
}
