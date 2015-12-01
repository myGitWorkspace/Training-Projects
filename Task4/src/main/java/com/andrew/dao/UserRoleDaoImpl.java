package com.andrew.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.andrew.model.UserRole;

/**
 * 
 * DAO interface implementation for UserRole entity
 *
 */
@Repository
public class UserRoleDaoImpl implements UserRoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * Get UserRole object by rolename from data Source 
	 * 
	 * @param rolename Rolename field of the userRole object to be found
	 * @return UserRole object
	 * 
	 */
	@SuppressWarnings("unchecked")
	public UserRole findByRoleName(String rolename) {

		List<UserRole> userRoles = new ArrayList<UserRole>();

		userRoles = sessionFactory.getCurrentSession()
				.createQuery("from UserRole where role=?")
				.setParameter(0, rolename)
				.list();

		if (userRoles.size() > 0) {
			return userRoles.get(0);
		} else {
			return null;
		}

	}
	
	/**
	 * 
	 * Get UserRole object by id from data Source 
	 * 
	 * @param id Id number of the UserRole object to be found
	 * @return UserRole object
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UserRole findById(Integer id) {

		List<UserRole> userRoles = new ArrayList<UserRole>();

		userRoles = getSessionFactory().getCurrentSession().createQuery("from UserRole where user_role_id=?")
				.setParameter(0, id).list();

		if (userRoles.size() > 0) {
			return userRoles.get(0);
		} else {
			return null;
		}

	}

	/**
	 * 
	 * Get all UserRole objects from data Source 
	 * 
	 * @return List of UserRole objects
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserRole> findAll() {
		
		List<UserRole> userRoles = new ArrayList<UserRole>();

		userRoles = getSessionFactory().getCurrentSession().createQuery("from UserRole").list();

		return userRoles;
	}

	/**
	 * 
	 * Save userRole object to data source 
	 *
	 * @param userRole UserRole object to be saved
	 * @return New UserRole object already saved in data source with updated id number
	 * 
	 */
	@Override
	public UserRole save(UserRole userRole) {

		return (UserRole)getSessionFactory().getCurrentSession().merge(userRole);		
	}

	/**
	 * 
	 * Update userRole object in data source 
	 *
	 * @param userRole UserRole object to be updated
	 * 
	 */
	@Override
	public void update(UserRole userRole) {
		
		if(userRole != null)
			getSessionFactory().getCurrentSession().update(userRole);		
	}
	
	/**
	 * 
	 * Delete userRole object from data source 
	 *
	 * @param id Id number of UserRole object to be deleted
	 * 
	 */
	@Override
	public void delete(Integer id) {

		UserRole userRole = findById(id);
		if(userRole != null)
			getSessionFactory().getCurrentSession().delete(userRole);		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}