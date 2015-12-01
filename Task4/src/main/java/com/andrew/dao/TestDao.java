package com.andrew.dao;

import java.util.List;

import com.andrew.model.Test;

/**
 * 
 * DAO interface for Test entity
 *
 */
public interface TestDao {

	Test findById(Integer id);

	List<Test> findAll();
	
	List<Test> findAllByCategoryId(Integer categoryId);

	Test save(Test test);

	void update(Test test);

	void delete(Integer id);

}
