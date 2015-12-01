package com.andrew.service;

import java.util.List;

import com.andrew.model.Test;

/**
 * 
 * Database service interface for Test entity
 *
 */
public interface TestService {

	Test findById(Integer id);
	
	List<Test> findAll();
	
	List<Test> findAllByCategoryId(Integer categoryId);

	Test saveOrUpdate(Test test);
	
	void delete(int id);
	
}