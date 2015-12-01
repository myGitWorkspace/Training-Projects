package com.andrew.service;

import java.util.List;

import com.andrew.model.Question;

/**
 * 
 * Database service interface for Question entity
 *
 */
public interface QuestionService {

	Question findById(Integer id);
	
	List<Question> findAll();
	
	List<Question> findByTestId(Integer id);

	Question saveOrUpdate(Question question);
	
	void delete(int id);
	
}