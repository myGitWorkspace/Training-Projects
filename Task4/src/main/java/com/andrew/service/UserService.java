package com.andrew.service;

import java.util.List;

import com.andrew.model.User;

/**
 * 
 * Database service interface for User entity
 *
 */
public interface UserService {

	User findByUserName(String username);
	
	User findById(Integer id);
	
	List<User> findAll();	

	User saveOrUpdate(User answer);
	
	void delete(int id);
	
}