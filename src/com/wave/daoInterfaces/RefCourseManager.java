package com.wave.daoInterfaces;

import java.util.List;
import com.wave.pojo.RefCourse;
import com.wave.utils.BaseException;

public interface RefCourseManager {

	public List<RefCourse> findAll() throws BaseException;
	
	public List<RefCourse> findByCourseId(Integer courseId) throws BaseException;
	
	public List<RefCourse> findByStudentRegisteredCourses() throws BaseException;
}
