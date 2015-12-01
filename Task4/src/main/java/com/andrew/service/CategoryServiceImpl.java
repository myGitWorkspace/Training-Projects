package com.andrew.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.andrew.dao.CategoryDao;
import com.andrew.model.Category;

/**
 * 
 * Database service interface implementation for Category entity
 *
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
		
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	public CategoryDao getCategoryDao() {
		return this.categoryDao;
	}
	
	/**
	 * 
	 * Get Category object by id from data Source 
	 * 
	 * @param id Id number of the category object to be found
	 * @return Category object
	 * 
	 */
	@Override
	public Category findById(Integer id) {
		return categoryDao.findById(id);
	}

	/**
	 * 
	 * Get all Category objects from data Source 
	 * 
	 * @return List of Category objects
	 * 
	 */
	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	/**
	 * 
	 * Save or update category object in data source 
	 *
	 * @param category Category object to be saved
	 * @return New Category object already saved in data source with updated id number, null if update was done
	 * 
	 */
	@Override
	public Category saveOrUpdate(Category category) {

		if (findById(category.getCategoryId())==null) {
			return categoryDao.save(category);
		} else {
			categoryDao.update(category);
		}
		return null;
	}

	/**
	 * 
	 * Delete category object from data source 
	 *
	 * @param id Id number of Category object to be deleted
	 * 
	 */
	@Override
	public void delete(int id) {
		categoryDao.delete(id);
	}

}