package com.andrew.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.andrew.model.User;
import com.andrew.service.UserService;

/**
 * 
 * Validator class for Register Form input values
 *
 */
@Component
public class RegisterFormValidator implements Validator {

	@Autowired
	UserService userService;
	
	/**
	 * 
	 * Checks if current Class object equals to User.class
	 * 
	 * @param clazz Current Class object
	 * @return true, if equals, false - otherwise
	 *
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
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

		User user = (User)target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.userForm.username");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password2", "NotEmpty.userForm.password2");
		
		if (!user.getPassword().equals(user.getPassword2())) {
			errors.rejectValue("password", "Diff.userForm.password");
		}

		if(userService.findByUserName(user.getUsername()) != null) {
			errors.rejectValue("username", "Diff.userForm.username");
		}		

	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public UserService getUserService() {
		return this.userService;
	}

}