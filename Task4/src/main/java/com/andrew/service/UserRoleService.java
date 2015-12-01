package com.andrew.service;

import java.util.List;

import com.andrew.model.UserRole;

/**
 * 
 * Database service interface for UserRole entity
 *
 */
public interface UserRoleService {

	UserRole findByRoleName(String rolename);
	
	UserRole findById(Integer id);
	
	List<UserRole> findAll();	

	UserRole saveOrUpdate(UserRole userRole);
	
	void delete(int id);
	
}