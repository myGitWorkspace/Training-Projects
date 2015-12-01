package com.andrew.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andrew.dao.TestDao;
import com.andrew.model.Test;

/**
 * 
 * Database service interface implementation for Test entity
 *
 */
@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestDao testDao;
		
	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}
	
	public TestDao getTestDao() {
		return this.testDao;
	}
	
	/**
	 * 
	 * Get Test object by id from data Source 
	 * 
	 * @param id Id number of the test object to be found
	 * @return Test object
	 * 
	 */
	@Override
	public Test findById(Integer id) {
		return testDao.findById(id);
	}

	/**
	 * 
	 * Get all Test objects from data Source 
	 * 
	 * @return List of Test objects
	 * 
	 */
	@Override
	public List<Test> findAll() {
		return testDao.findAll();
	}
	
	/**
	 * 
	 * Get all Answer objects that belongs to the particular Category 
	 *
	 * @param categoryId Id number of Category object
	 * @return List of Test objects
	 * 
	 */
	@Override
	public List<Test> findAllByCategoryId(Integer categoryId) {
		return testDao.findAllByCategoryId(categoryId);
	}

	/**
	 * 
	 * Save or update test object in data source 
	 *
	 * @param test Test object to be saved
	 * @return New Test object already saved in data source with updated id number, null if update was done
	 * 
	 */
	@Override
	public Test saveOrUpdate(Test test) {

		if (findById(test.getTestId())==null) {
			return testDao.save(test);
		} else {
			testDao.update(test);
		}
		return null;
	}

	/**
	 * 
	 * Delete test object from data source 
	 *
	 * @param id Id number of Test object to be deleted
	 * 
	 */
	@Override
	public void delete(int id) {
		testDao.delete(id);
	}

}