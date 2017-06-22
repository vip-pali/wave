package com.wave.daoInterfacesImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wave.daoInterfaces.RefCenterManager;
import com.wave.pojo.RefCenter;
import com.wave.pojo.RefState;
import com.wave.utils.BaseException;

public class RefCenterManagerImpl extends HibernateDaoSupport implements RefCenterManager {

	public List<RefCenter> findAll() throws BaseException {
		
		List<RefCenter> centerList = getHibernateTemplate().find("from RefCenter");
		return centerList;
	}
	
	public RefCenter findByCenterId(Integer centerId) throws BaseException {
		
		String query = "from RefCenter where id = ?";
		
		List<RefCenter> centerList = getHibernateTemplate().find(query, centerId);
		
		if(centerList.size()>0){
			
			return centerList.get(0);
		}
		
		return null;
		
	}
	
	public List<RefCenter> findByStateId(String stateId) throws BaseException {
		
		String query = "from RefCenter where stateId.stateId = ?";
		
		List<RefCenter> centerList = getHibernateTemplate().find(query, stateId);
		return centerList;
		
	}
	
	public List<RefCenter> findByStateIdAndDistrictId(String stateId, String districtId) throws BaseException {
		
		String query = "from RefCenter where stateId.stateId = ? and districtId.districtCode = ?";
		
		List<RefCenter> centerList = getHibernateTemplate().find(query, stateId, districtId);
		return centerList;
		
	}
	
	public boolean saveOrUpdate(RefCenter refCenter) throws BaseException {
		
		boolean res = false;
		getHibernateTemplate().saveOrUpdate(refCenter);
		
		res = true;
		return res;		
	}
	
	
	
	public List<RefCenter> findByCenterName(String centerName) throws BaseException {		
		
		String query = "from RefCenter where name = ?";
		
		List<RefCenter> centerList = getHibernateTemplate().find(query, centerName);
		return centerList;
		
	}
	
	public Integer findMaxId() throws BaseException {
		
		String query = "select max(id) from RefCenter";
		List<Integer> maxId = getHibernateTemplate().find(query);
		
		if(maxId.get(0) == null){
			
			return 0;
		
		}else{
		
			return maxId.get(0);
		}
		
		
	}
	
	
}
