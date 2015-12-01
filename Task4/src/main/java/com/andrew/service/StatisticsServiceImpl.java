package com.andrew.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andrew.dao.StatisticsDao;
import com.andrew.model.Statistics;

/**
 * 
 * Database service interface implementation for Statistics entity
 *
 */
@Service("statisticsService")
@Transactional
public class StatisticsServiceImpl implements StatisticsService {
	
	@Autowired
	private StatisticsDao statisticsDao;
		
	public void setStatisticsDao(StatisticsDao statisticsDao) {
		this.statisticsDao = statisticsDao;
	}
	
	public StatisticsDao getStatisticsDao() {
		return this.statisticsDao;
	}
	
	/**
	 * 
	 * Get Statistics object by id from data Source 
	 * 
	 * @param id Id number of the statistics object to be found
	 * @return Statistics object
	 * 
	 */
	@Override
	public Statistics findById(Integer id) {
		return statisticsDao.findById(id);
	}
	
	/**
	 * 
	 * Get all Statistics objects that belongs to the particular Test 
	 *
	 * @param id Id number of Test object
	 * @return List of Statistics objects
	 * 
	 */
	@Override
	public List<Statistics> findByTestId(Integer testId) {
		return statisticsDao.findByTestId(testId);
	}
	
	/**
	 * 
	 * Get all Statistics objects that belongs to the particular Test and User at one time 
	 *
	 * @param testId Id number of Test object
	 * @param userId Id number of User object
	 * @return List of Statistics objects
	 * 
	 */
	@Override
	public List<Statistics> findByTestIdAndUserId(Integer testId, Integer userId) {
		return statisticsDao.findByTestIdAndUserId(testId, userId);
	}

	/**
	 * 
	 * Get all Statistics objects from data Source 
	 * 
	 * @return List of Statistics objects
	 * 
	 */
	@Override
	public List<Statistics> findAll() {
		return statisticsDao.findAll();
	}

	/**
	 * 
	 * Save or update statistics object in data source 
	 *
	 * @param statistics Statistics object to be saved
	 * @return New Statistics object already saved in data source with updated id number, null if update was done
	 * 
	 */
	@Override
	public Statistics saveOrUpdate(Statistics statistics) {

		if (findById(statistics.getStatisticsId())==null) {
			return statisticsDao.save(statistics);
		} else {
			statisticsDao.update(statistics);
		}
		return null;
	}

	/**
	 * 
	 * Delete statistics object from data source 
	 *
	 * @param id Id number of Statistics object to be deleted
	 * 
	 */
	@Override
	public void delete(int id) {
		statisticsDao.delete(id);
	}

}