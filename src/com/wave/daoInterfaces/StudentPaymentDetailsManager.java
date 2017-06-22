package com.wave.daoInterfaces;

import java.util.List;

import com.wave.utils.BaseException;
import com.wave.pojo.RefCourse;
import com.wave.pojo.StudentPaymentDetails;

public interface StudentPaymentDetailsManager {
	
	public StudentPaymentDetails findById(Integer id) throws BaseException;

	public List<StudentPaymentDetails> findAll() throws BaseException;
	
	public boolean saveOrUpdate(StudentPaymentDetails studentPaymentDetails) throws BaseException;
	
	public List<StudentPaymentDetails> findByCourse(RefCourse refCourse) throws BaseException;
	
	public List<StudentPaymentDetails> findByCourse(Integer refCourse) throws BaseException;

}
