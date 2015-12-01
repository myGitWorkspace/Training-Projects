package com.andrew.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andrew.dao.UserRoleDao;
import com.andrew.model.UserRole;

/**
 * 
 * Database service interface implementation for UserRole entity
 *
 */
@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService {
	
	@Autowired
	private UserRoleDao userRoleDao;
		
	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}
	
	public UserRoleDao getUserRoleDao() {
		return this.userRoleDao;
	}
	
	/**
	 * 
	 * Get UserRole object by rolename from data Source 
	 * 
	 * @param rolename Rolename field of the userRole object to be found
	 * @return UserRole object
	 * 
	 */
	@Override
	public UserRole findByRoleName(String rolename) {
		return userRoleDao.findByRoleName(rolename);
	}
	
	/**
	 * 
	 * Get UserRole object by id from data Source 
	 * 
	 * @param id Id number of the UserRole object to be found
	 * @return UserRole object
	 * 
	 */
	@Override
	public UserRole findById(Integer id) {
		return userRoleDao.findById(id);
	}

	/**
	 * 
	 * Get all UserRole objects from data Source 
	 * 
	 * @return List of UserRole objects
	 * 
	 */
	@Override
	public List<UserRole> findAll() {
		return userRoleDao.findAll();
	}

	/**
	 * 
	 * Save or update userRole object in data source 
	 *
	 * @param userRole UserRole object to be saved
	 * @return New UserRole object already saved in data source with updated id number, null if update was done
	 * 
	 */
	@Override
	public UserRole saveOrUpdate(UserRole userRole) {

		if (findById(userRole.getUserRoleId())==null) {
			return userRoleDao.save(userRole);
		} else {
			userRoleDao.update(userRole);
		}
		return null;
	}

	/**
	 * 
	 * Delete userRole object from data source 
	 *
	 * @param id Id number of UserRole object to be deleted
	 * 
	 */
	@Override
	public void delete(int id) {
		userRoleDao.delete(id);
	}
	
}