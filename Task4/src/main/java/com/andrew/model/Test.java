package com.andrew.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * Model class for Test entity
 *
 */
@Entity
@Table(name = "test", catalog = "testing_system")
public class Test{

	private int testId;
	private Category category;
	private String title;
	private List<Question> question = new ArrayList<Question>(0);
	private List<Statistics> statistics = new ArrayList<Statistics>(0);
	private int aveMark;

	public Test() {
	}

	public Test(int testId, Category category, String title, List<Question> question, List<Statistics> statistics) {
		this.testId = testId;
		this.category = category;
		this.title = title;
		this.question = question;
		this.statistics = statistics;
	}
	
	public Test(int testId, Category category, String title) {
		this.testId = testId;
		this.category = category;
		this.title = title;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "test_id", unique = true, 
		nullable = false)
	public int getTestId() {
		return this.testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Column(name = "title", 
			nullable = false, length = 45)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "test")
	public List<Question> getQuestion() {
		return this.question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "test")
	public List<Statistics> getStatistics() {
		return this.statistics;
	}

	public void setStatistics(List<Statistics> statistics) {
		this.statistics = statistics;
	}
	
	@Transient
	public int getAveMark() {
		return this.aveMark;
	}
	
	public void setAveMark(int aveMark) {
		this.aveMark = aveMark;
	}

}
