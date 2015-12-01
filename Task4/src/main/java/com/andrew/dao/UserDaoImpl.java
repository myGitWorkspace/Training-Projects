package com.andrew.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.andrew.model.User;

/**
 * 
 * DAO interface implementation for User entity
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 
	 * Get User object by username from data Source 
	 * 
	 * @param username Username field of the User object to be found
	 * @return User object
	 * 
	 */
	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {

		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession()
				.createQuery("from User where username=?")
				.setParameter(0, username)
				.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}
	
	/**
	 * 
	 * Get User object by id from data Source 
	 * 
	 * @param id Id number of the User object to be found
	 * @return User object
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User findById(Integer id) {

		List<User> users = new ArrayList<User>();

		users = getSessionFactory().getCurrentSession().createQuery("from User where user_id=?")
				.setParameter(0, id).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

	/**
	 * 
	 * Get all User objects from data Source 
	 * 
	 * @return List of User objects
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		
		List<User> users = new ArrayList<User>();

		users = getSessionFactory().getCurrentSession().createQuery("from User").list();

		return users;
	}

	/**
	 * 
	 * Save user object to data source 
	 *
	 * @param user User object to be saved
	 * @return New User object already saved in data source with updated id number
	 * 
	 */
	@Override
	public User save(User user) {

		return (User)getSessionFactory().getCurrentSession().merge(user);		
	}

	/**
	 * 
	 * Update user object in data source 
	 *
	 * @param user User object to be updated
	 * 
	 */
	@Override
	public void update(User user) {
		
		if(user != null)
			getSessionFactory().getCurrentSession().update(user);		
	}
	
	/**
	 * 
	 * Delete user object from data source
	 *
	 * @param id Id number of User object to be deleted
	 * 
	 */
	@Override
	public void delete(Integer id) {

		User user = findById(id);
		if(user != null)
			getSessionFactory().getCurrentSession().delete(user);		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}