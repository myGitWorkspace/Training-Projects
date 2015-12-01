package com.andrew.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * Model class for Category entity
 *
 */
@Entity
@Table(name = "category", catalog = "testing_system")
public class Category{

	private int categoryId;
	private String name;
	private List<Test> test = new ArrayList<Test>(0);

	public Category() {
	}

	public Category(int categoryId, String name, List<Test> test) {
		this.categoryId = categoryId;
		this.name = name;
		this.test = test;		
	}

	public Category(int categoryId, String name) {
		this.categoryId = categoryId;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "category_id", unique = true, 
		nullable = false)
	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}	
	
	@Column(name = "name", 
			nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public List<Test> getTest() {
		return this.test;
	}

	public void setTest(List<Test> test) {
		this.test = test;
	}

}
