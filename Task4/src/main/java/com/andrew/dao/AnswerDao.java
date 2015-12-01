package com.andrew.dao;

import java.util.List;

import com.andrew.model.Answer;

/**
 * 
 * DAO interface for Answer entity
 *
 */
public interface AnswerDao {

	Answer findById(Integer id);

	List<Answer> findAll();
	
	List<Answer> findByQuestionId(Integer id);

	Answer save(Answer answer);

	void update(Answer answer);

	void delete(Integer id);	

}