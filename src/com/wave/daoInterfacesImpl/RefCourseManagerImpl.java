package com.wave.daoInterfacesImpl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.wave.daoInterfaces.RefCourseManager;
import com.wave.pojo.RefCourse;
import com.wave.utils.BaseException;

public class RefCourseManagerImpl extends HibernateDaoSupport implements RefCourseManager {

	public List<RefCourse> findAll() throws BaseException {
		
		List<RefCourse> courseList = getHibernateTemplate().find("from RefCourse");
		return courseList;
	}
	
	public List<RefCourse> findByCourseId(Integer courseId) throws BaseException {
		
		String query = "from RefCourse where id = ?";
		
		List<RefCourse> courseList = getHibernateTemplate().find(query, courseId);
		return courseList;
		
	}
	
	public List<RefCourse> findByStudentRegisteredCourses() throws BaseException {
		
		String query = "from RefCourse where id in(select distinct courseId.id from StudentPaymentDetails)";
		
		List<RefCourse> courseList = getHibernateTemplate().find(query);
		return courseList;
		
	}
}
