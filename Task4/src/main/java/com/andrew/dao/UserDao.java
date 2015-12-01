package com.andrew.dao;

import java.util.List;

import com.andrew.model.User;

/**
 * 
 * DAO interface for User entity
 *
 */
public interface UserDao {

	User findByUserName(String username);
	
	User findById(Integer id);

	List<User> findAll();	

	User save(User user);

	void update(User test);

	void delete(Integer id);

}