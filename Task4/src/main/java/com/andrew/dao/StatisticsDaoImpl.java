package com.andrew.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.andrew.model.Statistics;

/**
 * 
 * DAO interface implementation for Statistics entity
 *
 */
@Repository
public class StatisticsDaoImpl implements StatisticsDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * Get Statistics object by id from data Source 
	 * 
	 * @param id Id number of the statistics object to be found
	 * @return Statistics object
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Statistics findById(Integer id) {

		List<Statistics> statistics = new ArrayList<Statistics>();

		statistics = getSessionFactory().getCurrentSession().createQuery("from Statistics where statistics_id=?")
				.setParameter(0, id).list();

		if (statistics.size() > 0) {
			return statistics.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 * Get all Statistics objects from data Source 
	 * 
	 * @return List of Statistics objects
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Statistics> findAll() {
		
		List<Statistics> statistics = new ArrayList<Statistics>();

		statistics = getSessionFactory().getCurrentSession().createQuery("from Statistics").list();

		return statistics;
	}
	
	/**
	 * 
	 * Get all Statistics objects that belongs to the particular Test 
	 *
	 * @param id Id number of Test object
	 * @return List of Statistics objects
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Statistics> findByTestId(Integer testId) {
		
		List<Statistics> statistics = new ArrayList<Statistics>();

		statistics = getSessionFactory().getCurrentSession().createQuery("from Statistics where test_id=?")
				.setParameter(0, testId).list();

		if (statistics.size() > 0) {
			return statistics;
		} else {
			return null;
		}
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
	@SuppressWarnings("unchecked")
	@Override
	public List<Statistics> findByTestIdAndUserId(Integer testId, Integer userId) {
		
		List<Statistics> statistics = new ArrayList<Statistics>();

		statistics = getSessionFactory().getCurrentSession().createQuery("from Statistics where test_id=? and user_id=?")
				.setParameter(0, testId).setParameter(1, userId).list();

		if (statistics.size() > 0) {
			return statistics;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * Save statistics object to data source 
	 *
	 * @param statistics Statistics object to be saved
	 * @return New Statistics object already saved in data source with updated id number
	 * 
	 */
	@Override
	public Statistics save(Statistics statistics) {

		return (Statistics)getSessionFactory().getCurrentSession().merge(statistics);		
	}

	/**
	 * 
	 * Update statistics object in data source 
	 *
	 * @param statistics Statistics object to be updated
	 * 
	 */
	@Override
	public void update(Statistics statistics) {
		
		if(statistics != null)
			getSessionFactory().getCurrentSession().update(statistics);
	}
	
	/**
	 * 
	 * Delete statistics object from data source 
	 *
	 * @param id Id number of Statistics object to be deleted
	 * 
	 */
	@Override
	public void delete(Integer id) {

		Statistics statistics = findById(id);
		if(statistics != null)
			getSessionFactory().getCurrentSession().delete(statistics);		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
