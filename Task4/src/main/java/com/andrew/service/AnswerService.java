package com.andrew.service;

import java.util.List;

import com.andrew.model.Answer;

/**
 * 
 * Database service interface for Answer entity
 *
 */
public interface AnswerService {

	Answer findById(Integer id);
	
	List<Answer> findAll();
	
	List<Answer> findByQuestionId(Integer id);

	Answer saveOrUpdate(Answer answer);
	
	void delete(int id);
	
}