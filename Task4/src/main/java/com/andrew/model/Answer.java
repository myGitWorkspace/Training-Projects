package com.andrew.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * Model class for Answer entity
 *
 */
@Entity
@Table(name = "answer", catalog = "testing_system")
public class Answer{

	private int answerId;
	private Question question;
	private String answerText;	
	private boolean isCorrect;	

	public Answer() {
	}
	
	public Answer(int answerId, Question question, String answerText, boolean isCorrect) {
		this.answerId = answerId;
		this.question = question;
		this.answerText = answerText;
		this.isCorrect = isCorrect;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "answer_id", unique = true, 
		nullable = false)
	public int getAnswerId() {
		return this.answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id", nullable = false)
	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}	
	
	@Column(name = "answer_text", 
			nullable = false, length = 255)
	public String getAnswerText() {
		return this.answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	@Column(name = "is_correct", 
			nullable = false)
	public boolean isIsCorrect() {
		return this.isCorrect;
	}

	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

}

