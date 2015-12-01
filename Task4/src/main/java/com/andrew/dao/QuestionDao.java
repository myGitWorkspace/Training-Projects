package com.andrew.dao;

import java.util.List;

import com.andrew.model.Question;

/**
 * 
 * DAO interface for Question entity
 *
 */
public interface QuestionDao {

	Question findById(Integer id);

	List<Question> findAll();
	
	List<Question> findByTestId(Integer id);

	Question save(Question question);

	void update(Question question);

	void delete(Integer id);

}
