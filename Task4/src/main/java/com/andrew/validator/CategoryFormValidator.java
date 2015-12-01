package com.andrew.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.andrew.model.Category;

/**
 * 
 * Validator class for Category Form input values
 *
 */
@Component
public class CategoryFormValidator implements Validator {
	
	/**
	 * 
	 * Checks if current Class object equals to Category.class
	 * 
	 * @param clazz Current Class object
	 * @return true, if equals, false - otherwise
	 *
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Category.class.equals(clazz);
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
				
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.categoryForm.name");		
		
	}

}