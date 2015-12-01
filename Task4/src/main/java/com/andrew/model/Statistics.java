package com.andrew.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * Model class for Statistics entity
 *
 */
@Entity
@Table(name = "statistics", catalog = "testing_system")
public class Statistics{

	private int statisticsId;
	private User user;
	private Test test;
	private int correctAnswers;
	private int wrongAnswers;
	private Date date;
	private int progress;

	public Statistics() {
	}

	public Statistics(int statisticsId, User user, Test test, int correctAnswers, int wrongAnswers, Date date) {
		this.statisticsId = statisticsId;
		this.user = user;
		this.test = test;
		this.correctAnswers = correctAnswers;
		this.wrongAnswers = wrongAnswers;
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "statistics_id", unique = true, 
		nullable = false)
	public int getStatisticsId() {
		return this.statisticsId;
	}

	public void setStatisticsId(int statisticsId) {
		this.statisticsId = statisticsId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "test_id", nullable = false)
	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}
	
	@Column(name = "correct_answers", 
			nullable = false)
	public int getCorrectAnswers() {
		return this.correctAnswers;
	}

	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
	
	@Column(name = "wrong_answers", 
			nullable = false)
	public int getWrongAnswers() {
		return this.wrongAnswers;
	}

	public void setWrongAnswers(int wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}
	
	@Column(name = "date", 
			nullable = false)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Transient
	public int getProgress() {
		return this.correctAnswers*100/(this.correctAnswers + this.wrongAnswers);
	}
	
	public void setProgress(int progress) {
		this.progress = progress;
	}

}
