package com.andrew.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andrew.model.User;
import com.andrew.model.UserRole;
import com.andrew.service.UserRoleService;
import com.andrew.service.UserService;
import com.andrew.validator.RegisterFormValidator;

/**
 * 
 * Controller class to register new user
 *
 */
@Controller
public class RegisterController {
	
	@Autowired
	RegisterFormValidator registerFormValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(registerFormValidator);
	}
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService;
	
	private final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	/**
	 * 
	 * Display registration form 
	 * 
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView registration() {

		ModelAndView model = new ModelAndView();
		model.addObject("userForm", new User());
		model.setViewName("registration");
		logger.debug("Show registration form");
		return model;
	}
	 
	/**
	 * 
	 * Save current registration
	 * 
	 * @param user Current User object to be saved 
	 * @param result Validation results of User object
	 * @param redirectAttributes Redirected attributes for a view page
	 * 
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView saveRegistration(@ModelAttribute("userForm") @Validated User user,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		
		ModelAndView model = new ModelAndView();
		
		if (result.hasErrors()) {
			model.setViewName("registration");
			logger.debug("User entered not correct data during redistration. Will get one more try");
			return model;
		}
		
		model.addObject("msg", "New user has been successfully registered!");
		
		user.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));

		List<UserRole> roles = new ArrayList<UserRole>();
		roles.add(userRoleService.findByRoleName("ROLE_USER"));
		user.setUserRole(roles);
		user = userService.saveOrUpdate(user);
		UserRole role = new UserRole();
		role.setRole("ROLE_USER");
		role.setUser(user);
		userRoleService.saveOrUpdate(role);
		
		model.setViewName("login");
		logger.debug("New user (name {}) was successfully registered in the system", user.getUsername());
		
		return model;
	}
	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public UserService getUserService() {
		return this.userService;
	}
	
	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}
	
	public UserRoleService getUserRoleService() {
		return this.userRoleService;
	}
	
	
}
