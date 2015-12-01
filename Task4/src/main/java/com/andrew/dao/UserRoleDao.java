package com.andrew.dao;

import java.util.List;

import com.andrew.model.UserRole;

/**
 * 
 * DAO interface for UserRole entity
 *
 */
public interface UserRoleDao {

	UserRole findByRoleName(String rolename);
	
	UserRole findById(Integer id);

	List<UserRole> findAll();	

	UserRole save(UserRole userRole);

	void update(UserRole userRole);

	void delete(Integer id);

}