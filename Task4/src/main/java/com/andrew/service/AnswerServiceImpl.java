package com.andrew.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andrew.dao.AnswerDao;
import com.andrew.model.Answer;

/**
 * 
 * Database service interface implementation for Answer entity
 *
 */
@Service("answerService")
@Transactional
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	private AnswerDao answerDao;
		
	public void setAnswerDao(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}
	
	public AnswerDao getAnswerDao() {
		return this.answerDao;
	}
	
	/**
	 * 
	 * Get Answer object by id from data Source 
	 * 
	 * @param id Id number of the answer object to be found
	 * @return Answer object
	 * 
	 */
	@Override
	public Answer findById(Integer id) {
		return answerDao.findById(id);
	}

	/**
	 * 
	 * Get all Answer objects from data Source 
	 * 
	 * @return List of Answer objects
	 * 
	 */
	@Override
	public List<Answer> findAll() {
		return answerDao.findAll();
	}
	
	/**
	 * 
	 * Get all Answer objects that belongs to the particular Question 
	 *
	 * @param id Id number of Question object
	 * @return List of Answer objects
	 * 
	 */
	@Override
	public List<Answer> findByQuestionId(Integer id) {
		return answerDao.findByQuestionId(id);
	}

	/**
	 * 
	 * Save or update answer object in data source 
	 *
	 * @param answer Answer object to be saved
	 * @return New Answer object already saved in data source with updated id number, null if update was done
	 * 
	 */
	@Override
	public Answer saveOrUpdate(Answer answer) {

		if (findById(answer.getAnswerId())==null) {
			return answerDao.save(answer);
		} else {
			answerDao.update(answer);
		}
		return null;
	}

	/**
	 * 
	 * Delete answer object from data source 
	 *
	 * @param id Id number of Answer object to be deleted
	 * 
	 */
	@Override
	public void delete(int id) {
		answerDao.delete(id);
	}

}