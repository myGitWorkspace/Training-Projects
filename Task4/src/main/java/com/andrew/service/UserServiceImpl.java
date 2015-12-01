package com.andrew.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andrew.dao.UserDao;
import com.andrew.model.User;

/**
 * 
 * Database service interface implementation for User entity
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
		
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public UserDao getUserDao() {
		return this.userDao;
	}
	
	/**
	 * 
	 * Get User object by username from data Source 
	 * 
	 * @param username Username field of the User object to be found
	 * @return User object
	 * 
	 */
	@Override
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}
	
	/**
	 * 
	 * Get User object by id from data Source 
	 * 
	 * @param id Id number of the User object to be found
	 * @return User object
	 * 
	 */
	@Override
	public User findById(Integer id) {
		return userDao.findById(id);
	}

	/**
	 * 
	 * Get all User objects from data Source 
	 * 
	 * @return List of User objects
	 * 
	 */
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	/**
	 * 
	 * Save or update user object in data source 
	 *
	 * @param user User object to be saved
	 * @return New User object already saved in data source with updated id number, null if update was done
	 * 
	 */
	@Override
	public User saveOrUpdate(User user) {

		if (findById(user.getUserId())==null) {
			return userDao.save(user);
		} else {
			userDao.update(user);
		}
		return null;
	}

	/**
	 * 
	 * Delete user object from data source
	 *
	 * @param id Id number of User object to be deleted
	 * 
	 */
	@Override
	public void delete(int id) {
		userDao.delete(id);
	}
	
}

