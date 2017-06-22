package com.wave.daoInterfacesImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wave.daoInterfaces.RefDistrictManager;
import com.wave.pojo.RefDistrict;
import com.wave.utils.BaseException;

public class RefDistrictManagerImpl extends HibernateDaoSupport implements RefDistrictManager {

	public List<RefDistrict> findByStateId(String stateId) throws BaseException
	{
		String query="from RefDistrict where stateCode =?  order by name";
		List<RefDistrict> refDistrictList = getHibernateTemplate().find(query, stateId);
		return refDistrictList;
	}

	public List<RefDistrict> findAllDistirctList() throws BaseException
	{
		List<RefDistrict> refDistrictList = getHibernateTemplate().find("from RefDistrict order by name");

		return refDistrictList;
	}

	public List<RefDistrict>findByStateIdAndDistrictId(String stateId, String districtId)throws BaseException
	{
		String query="from RefDistrict where stateCode = ? and districtCode = ? order by name";
		List<RefDistrict> refDistrictList = getHibernateTemplate().find(query, stateId, districtId);
		return refDistrictList;
	}

	public List<RefDistrict> findAllDistirctId(String districtId) throws BaseException
	{
		String query="from RefDistrict where districtCode = ?";
		List<RefDistrict> refDistrictList = getHibernateTemplate().find(query, districtId);
		return refDistrictList;
	}

	public RefDistrict findByDistirctId(String districtId) throws BaseException
	{
		String query="from RefDistrict where districtCode = ?";
		List<RefDistrict> refDistrictList = getHibernateTemplate().find(query, districtId);
		
		if(refDistrictList !=null && refDistrictList.size() ==1) {
			
			return refDistrictList.get(0);

		}
		return null;
	}
	
	//this method to get district list based on state which are present ref_center table
	public List<RefDistrict>findDistricts(String stateId) throws BaseException
	{
		String query="from RefDistrict where districtCode in(select distinct districtId from RefCenter where stateId.stateId = ? )";
		
		List<RefDistrict> refDistrictList = getHibernateTemplate().find(query, stateId);
		return refDistrictList;
	}




}




