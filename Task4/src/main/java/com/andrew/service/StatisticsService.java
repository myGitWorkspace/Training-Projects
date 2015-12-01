package com.andrew.service;

import java.util.List;

import com.andrew.model.Statistics;

/**
 * 
 * Database service interface for Statistics entity
 *
 */
public interface StatisticsService {

	Statistics findById(Integer id);
	
	List<Statistics> findByTestId(Integer testId);
	
	List<Statistics> findByTestIdAndUserId(Integer testId, Integer userId);
	
	List<Statistics> findAll();

	Statistics saveOrUpdate(Statistics statistics);
	
	void delete(int id);
	
}