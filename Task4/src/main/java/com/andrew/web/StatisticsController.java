package com.andrew.web;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.andrew.model.Category;
import com.andrew.model.Statistics;
import com.andrew.model.Test;
import com.andrew.service.CategoryService;
import com.andrew.service.StatisticsService;
import com.andrew.service.TestService;
import com.andrew.service.UserService;

/**
 * 
 * Controller class to manage statistics
 *
 */
@Controller
public class StatisticsController {

	@Autowired
	private StatisticsService statisticsService;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private TestService testService;
	
	private final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	/**
	 * 
	 * Display statistics page
	 * 
	 * @param testId Id number of current test
	 * 
	 */
	@RequestMapping(value = "/stats/{id}", method = RequestMethod.GET)
	public ModelAndView statistics(@PathVariable("id") int testId) {

		ModelAndView model = new ModelAndView();
		List<Statistics> statistics = null;
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")) )
			statistics = statisticsService.findByTestId(testId);
		else {
			int userId = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).getUserId();
			statistics = statisticsService.findByTestIdAndUserId(testId, userId);			
		}
		
		model.addObject("statisticsList", statistics);
		Test test = testService.findById(testId);
		Category category = categoryService.findById(test.getCategory().getCategoryId());
		test.setCategory(category);
		model.addObject("test", test);
		
		model.setViewName("statistics");
		logger.debug("Show statistics for category # id {}", category.getCategoryId());
		
		return model;
	}
	
	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}
	
	public StatisticsService getStatisticsService() {
		return this.statisticsService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public UserService getUserService() {
		return this.userService;
	}
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public CategoryService getCategoryService() {
		return this.categoryService;
	}
	
	public void setTestService(TestService testService) {
		this.testService = testService;
	}
	
	public TestService getTestService() {
		return this.testService;
	}
	
}
