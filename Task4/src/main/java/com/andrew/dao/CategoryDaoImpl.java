package com.andrew.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.andrew.model.Category;

/**
 * 
 * DAO interface implementation for Category entity
 *
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * Get Category object by id from data Source 
	 * 
	 * @param id Id number of the category object to be found
	 * @return Category object
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Category findById(Integer id) {

		List<Category> categories = new ArrayList<Category>();

		categories = getSessionFactory().getCurrentSession().createQuery("from Category where category_id=?")
				.setParameter(0, id).list();

		if (categories.size() > 0) {
			return categories.get(0);
		} else {
			return null;
		}

	}

	/**
	 * 
	 * Get all Category objects from data Source 
	 * 
	 * @return List of Category objects
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findAll() {
		
		List<Category> categories = new ArrayList<Category>();

		categories = getSessionFactory().getCurrentSession().createQuery("from Category").list();

		return categories;
	}

	/**
	 * 
	 * Save category object to data source 
	 *
	 * @param category Category object to be saved
	 * @return New Category object already saved in data source with updated id number
	 * 
	 */
	@Override
	public Category save(Category category) {

		return (Category)getSessionFactory().getCurrentSession().merge(category);		
	}

	/**
	 * 
	 * Update category object in data source 
	 *
	 * @param category Category object to be updated
	 * 
	 */
	@Override
	public void update(Category category) {
		
		if(category != null)
			getSessionFactory().getCurrentSession().update(category);
	}
	
	/**
	 * 
	 * Delete category object from data source 
	 *
	 * @param id Id number of Category object to be deleted
	 * 
	 */
	@Override
	public void delete(Integer id) {

		Category category = findById(id);
		if(category != null)
			getSessionFactory().getCurrentSession().delete(category);		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
