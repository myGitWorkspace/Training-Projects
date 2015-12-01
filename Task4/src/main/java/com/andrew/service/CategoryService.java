package com.andrew.service;

import java.util.List;

import com.andrew.model.Category;

/**
 * 
 * Database service interface for Category entity
 *
 */
public interface CategoryService {

	Category findById(Integer id);
	
	List<Category> findAll();

	Category saveOrUpdate(Category category);
	
	void delete(int id);
	
}