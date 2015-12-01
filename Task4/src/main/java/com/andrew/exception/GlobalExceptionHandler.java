package com.andrew.exception;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * 
 * Global scope exception handler class
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	public static final String DEFAULT_ERROR_VIEW = "error";

	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	/**
	 * 
	 * Exception handler for all exceptions
	 * 
	 * @param req Request parameter 
	 * @param e Current exception object
	 * @return ModelAndView object to display a resulted page
	 * 
	 */
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		
		logger.error("[URL] : {}", req.getRequestURL(), e);		

		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
			throw e;

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}
	
	/**
	 * 
	 * Exception handler for NoHandlerFoundException
	 * 
	 * @param e Current exception object
	 * @param req Request parameter 
	 * @return ModelAndView object to display a resulted page
	 * 
	 */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNotFoundException(Exception e, HttpServletRequest req){        
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
    }
    
}