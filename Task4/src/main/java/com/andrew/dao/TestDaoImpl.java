package com.andrew.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.andrew.model.Test;

/**
 * 
 * DAO interface implementation for Test entity
 *
 */
@Repository
public class TestDaoImpl implements TestDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * Get Test object by id from data Source 
	 * 
	 * @param id Id number of the test object to be found
	 * @return Test object
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Test findById(Integer id) {

		List<Test> tests = new ArrayList<Test>();

		tests = getSessionFactory().getCurrentSession().createQuery("from Test where test_id=?")
				.setParameter(0, id).list();

		if (tests.size() > 0) {
			return tests.get(0);
		} else {
			return null;
		}

	}

	/**
	 * 
	 * Get all Test objects from data Source 
	 * 
	 * @return List of Test objects
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Test> findAll() {
		
		List<Test> tests = new ArrayList<Test>();

		tests = getSessionFactory().getCurrentSession().createQuery("from Test").list();

		return tests;
	}
	
	/**
	 * 
	 * Get all Answer objects that belongs to the particular Category 
	 *
	 * @param categoryId Id number of Category object
	 * @return List of Test objects
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Test> findAllByCategoryId(Integer categoryId) {
		List<Test> tests = new ArrayList<Test>();

		tests = getSessionFactory().getCurrentSession().createQuery("from Test where category_id=?").setParameter(0, categoryId).list();

		return tests;
	}

	/**
	 * 
	 * Save test object to data source 
	 *
	 * @param test Test object to be saved
	 * @return New Test object already saved in data source with updated id number
	 * 
	 */
	@Override
	public Test save(Test test) {

		return (Test)getSessionFactory().getCurrentSession().merge(test);		
	}

	/**
	 * 
	 * Update test object in data source 
	 *
	 * @param test Test object to be updated
	 * 
	 */
	@Override
	public void update(Test test) {
		
		if(test != null)
			getSessionFactory().getCurrentSession().update(test);		
	}
	
	/**
	 * 
	 * Delete test object from data source 
	 *
	 * @param id Id number of Test object to be deleted
	 * 
	 */
	@Override
	public void delete(Integer id) {

		Test test = findById(id);
		if(test != null)
			getSessionFactory().getCurrentSession().delete(test);		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
