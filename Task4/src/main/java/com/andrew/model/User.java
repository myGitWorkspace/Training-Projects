package com.andrew.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

/**
 * 
 * Model class for User entity
 *
 */
@Entity
@Table(name = "users", catalog = "testing_system")
public class User{

	private int userId;
	private String username;
	private String password;
	private boolean enabled;
	private List<UserRole> userRole = new ArrayList<UserRole>(0);
	private List<Statistics> statistics = new ArrayList<Statistics>(0);
	private String password2;

	public User() {
	}

	public User(int userId, String username, String password, boolean enabled) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public User(int userId, String username, String password, boolean enabled, List<UserRole> userRole, List<Statistics> statistics) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
		this.statistics = statistics;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, 
		nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}	
	
	@Column(name = "username", 
			nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", 
			nullable = false, length = 60)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "enabled", nullable = false)
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public List<UserRole> getUserRole() {
		return this.userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public List<Statistics> getStatistics() {
		return this.statistics;
	}

	public void setStatistics(List<Statistics> statistics) {
		this.statistics = statistics;
	}
	
	@Transient
	public String getPassword2() {
		return this.password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

}
