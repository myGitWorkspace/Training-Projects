package com.andrew.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andrew.dao.QuestionDao;
import com.andrew.model.Question;

/**
 * 
 * Database service interface implementation for Question entity
 *
 */
@Service("questionService")
@Transactional
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionDao questionDao;
		
	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}
	
	public QuestionDao getQuestionDao() {
		return this.questionDao;
	}
	
	/**
	 * 
	 * Get Question object by id from data Source 
	 * 
	 * @param id Id number of the question object to be found
	 * @return Question object
	 * 
	 */
	@Override
	public Question findById(Integer id) {
		return questionDao.findById(id);
	}

	/**
	 * 
	 * Get all Question objects from data Source 
	 * 
	 * @return List of Question objects
	 * 
	 */
	@Override
	public List<Question> findAll() {
		return questionDao.findAll();
	}
	
	/**
	 * 
	 * Get all Question objects that belongs to the particular Test 
	 *
	 * @param id Id number of Test object
	 * @return List of Question objects
	 * 
	 */
	@Override
	public List<Question> findByTestId(Integer id) {
		return questionDao.findByTestId(id);
	}

	/**
	 * 
	 * Save or update question object in data source 
	 *
	 * @param question Question object to be saved
	 * @return New Question object already saved in data source with updated id number, null if update was done
	 * 
	 */
	@Override
	public Question saveOrUpdate(Question question) {

		if (findById(question.getQuestionId())==null) {
			return questionDao.save(question);
		} else {
			questionDao.update(question);
		}
		return null;
	}

	/**
	 * 
	 * Delete question object from data source 
	 *
	 * @param id Id number of Question object to be deleted
	 * 
	 */
	@Override
	public void delete(int id) {
		questionDao.delete(id);
	}

}