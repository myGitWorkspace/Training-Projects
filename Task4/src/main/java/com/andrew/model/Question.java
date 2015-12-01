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

/**
 * 
 * Model class for Question entity
 *
 */
@Entity
@Table(name = "question", catalog = "testing_system")
public class Question{

	private int questionId;
	private Test test;
	private String questionText;
	private List<Answer> answer = new ArrayList<Answer>(0);

	public Question() {
	}

	public Question(int questionId, Test test, String questionText, List<Answer> answer) {
		this.questionId = questionId;
		this.test = test;
		this.questionText = questionText;
		this.answer = answer;
	}

	public Question(int questionId, Test test, String questionText) {
		this.questionId = questionId;
		this.test = test;
		this.questionText = questionText;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "question_id", unique = true, 
		nullable = false)
	public int getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)	
	@JoinColumn(name = "test_id", nullable = false)
	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}	
	
	@Column(name = "question_text", 
			nullable = false, length = 255)
	public String getQuestionText() {
		return this.questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
	public List<Answer> getAnswer() {
		return this.answer;
	}

	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}

}
