package com.andrew.dao;

import java.util.List;

import com.andrew.model.Category;

/**
 * 
 * DAO interface for Category entity
 *
 */
public interface CategoryDao {

	Category findById(Integer id);

	List<Category> findAll();

	Category save(Category category);

	void update(Category category);

	void delete(Integer id);

}