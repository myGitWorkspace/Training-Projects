package com.andrew.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.andrew.model.Question;
import com.andrew.model.Answer;

/**
 * 
 * Validator class for Question Form input values
 *
 */
@Component
public class QuestionFormValidator implements Validator {
	
	/**
	 * 
	 * Checks if current Class object equals to Question.class
	 * 
	 * @param clazz Current Class object
	 * @return true, if equals, false - otherwise
	 *
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Question.class.equals(clazz);
	}

	/**
	 * 
	 * Validates current object
	 * 
	 * @param target Current object to be validated
	 * @param errors Holds the results of object validation
	 *
	 */
	@Override
	public void validate(Object target, Errors errors) {

		Question question = (Question)target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "questionText", "NotEmpty.questionForm.questionText");
		
		boolean hasCorrectAnswer = false;
		for(Answer answer : question.getAnswer()) {
			if(answer.getAnswerText().equals("")){
				errors.rejectValue("questionText", "NotEmpty.questionForm.answerText");
			}
			if(answer.isIsCorrect())
				hasCorrectAnswer = true;			
		}
		
		if( !hasCorrectAnswer)
			errors.rejectValue("questionText", "NotEmpty.questionForm.correctAnswer");

	}

}