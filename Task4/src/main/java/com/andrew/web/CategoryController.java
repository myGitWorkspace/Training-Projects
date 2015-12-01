package com.andrew.web;

import javax.servlet.http.HttpServletRequest;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andrew.model.Category;
import com.andrew.model.Statistics;
import com.andrew.model.Test;
import com.andrew.model.Question;
import com.andrew.model.Answer;
import com.andrew.service.AnswerService;
import com.andrew.service.CategoryService;
import com.andrew.service.QuestionService;
import com.andrew.service.StatisticsService;
import com.andrew.service.TestService;
import com.andrew.service.UserService;
import com.andrew.validator.CategoryFormValidator;


/**
 * 
 * Controller for category management
 *
 */
@Controller
public class CategoryController {

	@Autowired
	CategoryFormValidator categoryFormValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {	
		binder.setValidator(categoryFormValidator);
	}	
	
	@Autowired
	private AnswerService answerService;
	@Autowired
	private QuestionService questionService;	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private StatisticsService statisticsService;
	@Autowired
	private TestService testService;
	
	private final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	/**
	 * 
	 * Show the list of all categories and handle category actions
	 * 
	 * @param action Current action to be done with category
	 * @param categoryId Id number of current category
	 * 
	 */	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public ModelAndView showCategoryList(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "categoryId", required = false) Integer categoryId) {
		
		ModelAndView model = new ModelAndView();

		if(action != null && action.equals("add")) {
			Category category = new Category();			
			model.addObject("categoryForm", category);			
			model.setViewName("addcategory");
			logger.debug("Show form to add new category");
			return model;
		} else if(action != null && action.equals("delete")) {
			List<Test> tests = testService.findAllByCategoryId(categoryId);
			for(Test test : tests) {
				List<Question> questions = questionService.findByTestId(test.getTestId());
				for(Question question : questions) {
					List<Answer> answers = answerService.findByQuestionId(question.getQuestionId());
					for(Answer answer : answers) {
						answerService.delete(answer.getAnswerId());
					}
					questionService.delete(question.getQuestionId());
				}
				List<Statistics> stats = statisticsService.findByTestId(test.getTestId());
				for(Statistics statRecord : stats) {
					statisticsService.delete(statRecord.getStatisticsId());
				}
				testService.delete(test.getTestId());
			}
			categoryService.delete(categoryId);
			logger.debug("Category # id={} with its contents was successfully deleted from the system", categoryId);
		}

		List<Category> categories = categoryService.findAll();
		for(Category category : categories)
			category.setTest(testService.findAllByCategoryId(category.getCategoryId()));
		model.addObject("categories", categories);		
		model.setViewName("listcategory");
		logger.debug("Show list of all categories");
		
		return model;
	}
	
	/**
	 * 
	 * Save new category
	 * 
	 * @param category New Category object to be saved
	 * @param result Validation results of new Category object to be saved
	 * @param redirectAttributes Redirected attributes for a view page
	 * @param request HttpServletRequest object of current request
	 * 
	 */
	@RequestMapping(value = "/category", method = RequestMethod.POST)	
	public ModelAndView saveNewCategory(@ModelAttribute("categoryForm") @Validated Category category,
			BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView();
		
		if (result.hasErrors()) {
			model.setViewName("addcategory");			
			return model;
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
		}			
				
		categoryService.saveOrUpdate(category);	
		
		logger.debug("New category was successfully saved with name {}", category.getName());
		
		model.setViewName("redirect:/category");
		return model;		
	}
	
	/**
	 * 
	 * Show the content of category by id
	 * 
	 * @param id Category id
	 * @param request HttpServletRequest object of current request
	 * 
	 */
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public ModelAndView showCategory(@PathVariable("id") int id, HttpServletRequest request) {		

		ModelAndView model = new ModelAndView();
		Category category = categoryService.findById(id);
		request.getSession().setAttribute("currentCategory", category);
		
		List<Test> tests = testService.findAllByCategoryId(id);		
		for(Test test : tests) {
			List<Statistics> statistics = null;
			Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN")) )
				statistics = statisticsService.findByTestId(test.getTestId());
			else {
				int userId = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()).getUserId();
				statistics = statisticsService.findByTestIdAndUserId(test.getTestId(), userId);
			}
			int aveMark = -1;
			if(statistics != null) {
				for(Statistics stat : statistics)
					aveMark += stat.getProgress();
				aveMark /= statistics.size();	
			}
			test.setAveMark(aveMark);
		}
		if (category == null) {
			model.addObject("css", "danger");
			model.addObject("msg", "Category not found");
			logger.error("Category not found (id # {})", id);
		}
		model.addObject("category", category);
		model.addObject("tests", tests);
		model.setViewName("category");
		logger.debug("Show the content of category named {}", category.getName());

		return model;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	public QuestionService getQuestionService() {
		return this.questionService;
	}
	
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}
	
	public AnswerService getAnswerService() {
		return this.answerService;
	}
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public CategoryService getCategoryService() {
		return this.categoryService;
	}
	
	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}
	
	public StatisticsService getStatisticsService() {
		return this.statisticsService;
	}
	
	public void setTestService(TestService testService) {
		this.testService = testService;
	}
	
	public TestService getTestService() {
		return this.testService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public UserService getUserService() {
		return this.userService;
	}
	
}
