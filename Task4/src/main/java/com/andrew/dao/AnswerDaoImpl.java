package com.andrew.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.andrew.model.Answer;

/**
 * 
 * DAO interface implementation for Answer entity
 *
 */
@Repository
public class AnswerDaoImpl implements AnswerDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * Get Answer object by id from data Source 
	 * 
	 * @param id Id number of the answer object to be found
	 * @return Answer object
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Answer findById(Integer id) {

		List<Answer> answers = new ArrayList<Answer>();

		answers = getSessionFactory().getCurrentSession().createQuery("from Answer where answer_id=?")
				.setParameter(0, id).list();

		if (answers.size() > 0) {
			return answers.get(0);
		} else {
			return null;
		}

	}

	/**
	 * 
	 * Get all Answer objects from data Source 
	 * 
	 * @return List of Answer objects
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> findAll() {
		
		List<Answer> answers = new ArrayList<Answer>();

		answers = getSessionFactory().getCurrentSession().createQuery("from Answer").list();

		return answers;
	}
	
	/**
	 * 
	 * Get all Answer objects that belongs to the particular Question 
	 *
	 * @param id Id number of Question object
	 * @return List of Answer objects
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Answer> findByQuestionId(Integer id) {
		
		List<Answer> answers = new ArrayList<Answer>();

		answers = getSessionFactory().getCurrentSession().createQuery("from Answer where question_id=?").setParameter(0, id).list();

		return answers;
	}

	/**
	 * 
	 * Save answer object to data source 
	 *
	 * @param answer Answer object to be saved
	 * @return New Answer object already saved in data source with updated id number
	 * 
	 */
	@Override
	public Answer save(Answer answer) {

		return (Answer)getSessionFactory().getCurrentSession().merge(answer);		
	}

	/**
	 * 
	 * Update answer object in data source 
	 *
	 * @param answer Answer object to be updated
	 * 
	 */
	@Override
	public void update(Answer answer) {
		
		if(answer != null)
			getSessionFactory().getCurrentSession().update(answer);
	}
	
	/**
	 * 
	 * Delete answer object from data source 
	 *
	 * @param id Id number of Answer object to be deleted
	 * 
	 */
	@Override
	public void delete(Integer id) {

		Answer answer = findById(id);
		if(answer != null)
			getSessionFactory().getCurrentSession().delete(answer);		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
