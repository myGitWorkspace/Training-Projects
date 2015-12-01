package com.andrew.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.andrew.model.Question;

/**
 * 
 * DAO interface implementation for Question entity
 *
 */
@Repository
public class QuestionDaoImpl implements QuestionDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * Get Question object by id from data Source 
	 * 
	 * @param id Id number of the question object to be found
	 * @return Question object
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Question findById(Integer id) {

		List<Question> questions = new ArrayList<Question>();

		questions = getSessionFactory().getCurrentSession().createQuery("from Question where question_id=?")
				.setParameter(0, id).list();

		if (questions.size() > 0) {
			return questions.get(0);
		} else {
			return null;
		}

	}

	/**
	 * 
	 * Get all Question objects from data Source 
	 * 
	 * @return List of Question objects
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> findAll() {
		
		List<Question> questions = new ArrayList<Question>();

		questions = getSessionFactory().getCurrentSession().createQuery("from Question").list();

		return questions;
	}
	
	/**
	 * 
	 * Get all Question objects that belongs to the particular Test 
	 *
	 * @param id Id number of Test object
	 * @return List of Question objects
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> findByTestId(Integer id) {
		
		List<Question> questions = new ArrayList<Question>();

		questions = getSessionFactory().getCurrentSession().createQuery("from Question where test_id=?").setParameter(0, id).list();

		return questions;
	}	

	/**
	 * 
	 * Save question object to data source 
	 *
	 * @param question Question object to be saved
	 * @return New Question object already saved in data source with updated id number
	 * 
	 */
	@Override
	public Question save(Question question) {

		return (Question)getSessionFactory().getCurrentSession().merge(question);		
	}

	/**
	 * 
	 * Update question object in data source 
	 *
	 * @param question Question object to be updated
	 * 
	 */
	@Override
	public void update(Question question) {
		
		if(question != null)
			getSessionFactory().getCurrentSession().update(question);
	}
	
	/**
	 * 
	 * Delete question object from data source 
	 *
	 * @param id Id number of Question object to be deleted
	 * 
	 */
	@Override
	public void delete(Integer id) {

		Question question = findById(id);
		if(question != null)
			getSessionFactory().getCurrentSession().delete(question);		
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}

