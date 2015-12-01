package com.andrew.dao;

import java.util.List;

import com.andrew.model.Statistics;

/**
 * 
 * DAO interface for Statistics entity
 *
 */
public interface StatisticsDao {

	Statistics findById(Integer id);
	
	List<Statistics> findByTestId(Integer testId);
	
	List<Statistics> findByTestIdAndUserId(Integer testId, Integer userId);

	List<Statistics> findAll();

	Statistics save(Statistics statistics);

	void update(Statistics statistics);

	void delete(Integer id);

}