package com.andrew.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * Controller for user login page
 *
 */
@Controller
public class MainController {
	
	private final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	/**
	 * 
	 * Show home page 
	 * 
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/login");
		logger.debug("User entered home page");
		return model;
	}
	
	/**
	 * 
	 * Show login page
	 * 
	 * @param error Not required Error request parameter
	 * @param logout Not required Logout request parameter
	 * @param request HttpServletRequest object of current request
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
			logger.error(getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
			logger.debug("User has logout from the system");
		}
		
		String page = "redirect:/category";
		if(SecurityContextHolder.getContext().getAuthentication() == null ||
				SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
			page = "login";			
		}		
				
		model.setViewName(page);
		logger.debug("Show main page");

		return model;
	}
	
	/**
	 * 
	 * Customize the error message
	 * 	 
	 * @param request HttpServletRequest object of current request
	 * @param key Attribute name of the saved Exception object in session
	 * 
	 */ 
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	/**
	 * 
	 * Display 403 access denied page 	
	 * 
	 */ 
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails)auth.getPrincipal();			
			model.addObject("username", userDetail.getUsername());
		}

		model.setViewName("403");
		logger.debug("User entered access denied page");
		return model;
	}

}