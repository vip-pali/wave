package com.wave.daoInterfacesImpl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.wave.utils.BaseException;
import com.wave.daoInterfaces.StudentPaymentDetailsManager;
import com.wave.pojo.RefCourse;
import com.wave.pojo.StudentPaymentDetails;

public class StudentPaymentDetailsManagerImpl extends HibernateDaoSupport implements StudentPaymentDetailsManager {
	
	public StudentPaymentDetails findById(Integer id) throws BaseException {
		
		String query = "from StudentPaymentDetails where id = ?";
		List<StudentPaymentDetails> candidatesList = getHibernateTemplate().find(query, id);
		
		if(candidatesList.size() == 1)
			
			return candidatesList.get(0);
		
		return null;
	}
	
	public List<StudentPaymentDetails> findAll() throws BaseException {
		
		List<StudentPaymentDetails> allCandidatesList = getHibernateTemplate().find("from StudentPaymentDetails");
		return allCandidatesList;
	}
	
	public boolean saveOrUpdate(StudentPaymentDetails studentPaymentDetails) throws BaseException {
		
		boolean res = false;
		getHibernateTemplate().saveOrUpdate(studentPaymentDetails);
		
		res = true;
		
		return res;
	}
	

	public List<StudentPaymentDetails> findByCourse(RefCourse refCourse) throws BaseException {
		
		String query = "from StudentPaymentDetails where courseId = ?";
		
		List<StudentPaymentDetails> allCandidatesList = getHibernateTemplate().find(query, refCourse);
		
		return allCandidatesList;
	}
	
	public List<StudentPaymentDetails> findByCourse(Integer courseId) throws BaseException {
		
		String query = "from StudentPaymentDetails where courseId.id = ?";
		
		List<StudentPaymentDetails> allCandidatesList = getHibernateTemplate().find(query, courseId);
		
		return allCandidatesList;
	}
	
	

}
