package com.andrew.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.andrew.dao.UserDao;
import com.andrew.model.UserRole;

/**
 * 
 * Service class for Spring security Authentication
 *
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	public com.andrew.model.User findByUserName(String username) {
		return userDao.findByUserName(username);
	}
	
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		
		com.andrew.model.User user = userDao.findByUserName(username);
		List<GrantedAuthority> authorities = 
                                      buildUserAuthority(user.getUserRole());

		return buildUserForAuthentication(user, authorities);
	
	
	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.andrew.model.User user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}