package com.andrew.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andrew.model.Answer;
import com.andrew.model.Question;
import com.andrew.model.Statistics;
import com.andrew.model.Test;
import com.andrew.service.AnswerService;
import com.andrew.service.CategoryService;
import com.andrew.service.QuestionService;
import com.andrew.service.StatisticsService;
import com.andrew.service.TestService;
import com.andrew.validator.QuestionFormValidator;

/**
 * 
 * Controller class to create new test
 *
 */
@Controller
public class TestController {

	@Autowired
	QuestionFormValidator questionFormValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(questionFormValidator);
	}	
	
	@Autowired
	private TestService testService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private StatisticsService statisticsService;
	@Autowired
	private CategoryService categoryService;
	
	private final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	/**
	 * 
	 * Delete test
	 * 
	 * @param action Request parameter that holds a delete action for test 
	 * @param testId Id number of current test
	 * @param categoryId Id number of current category
	 * 
	 */	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView deleteTest(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "testId", required = false) Integer testId,
			@RequestParam(value = "categoryId", required = false) Integer categoryId) {
		
		ModelAndView model = new ModelAndView();

		if(action != null && action.equals("delete")) {
			Test test = testService.findById(testId);			
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
		model.setViewName("redirect:/category/"+categoryId);
		logger.debug("Test with id # {} was successfully deleted from the system", testId);
		
		return model;
	}
	
	List<Question> questionsToSave = new ArrayList<>();
	Test newTest = new Test();	
 
	/**
	 * 
	 * Start creating new test
	 * 
	 * @param title The title of a new test 
	 * @param categoryId Id number of current category
	 * 
	 */	
	@RequestMapping(value = "/tests/add", method = RequestMethod.POST)
	public ModelAndView startCreatingNewTest(@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "categoryId", required = true) Integer categoryId) {

		questionsToSave = new ArrayList<>();
		newTest = new Test();
		
		newTest.setTitle(title);
		newTest.setCategory(categoryService.findById(categoryId));
	
		ModelAndView model = new ModelAndView();
		
		saveCurrentQuestionToList(model);
		
		model.setViewName("addquestion");
		logger.debug("User is going to create new Test with title # {}", title);

		return model;

	}
	
	/**
	 * 
	 * Display form for adding new question
	 * 
	 * @param question Question object to be displayed in question form 
	 * @param result Validation results of Question object
	 * @param redirectAttributes Redirected attributes for a view page
	 * @param questionNumb Current Question number to be displayed 
	 * @param request HttpServletRequest object of current request
	 * 
	 */	
	@RequestMapping(value = "/question/add", method = RequestMethod.POST)
	public ModelAndView showAddQuestionForm(@ModelAttribute("questionForm") @Validated Question question,			
			BindingResult result, final RedirectAttributes redirectAttributes,
			@RequestParam(value = "questionNumb", required = true) Integer questionNumb,
			HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView();
		
		if (result.hasErrors()) {
			model.setViewName("addquestion");
			model.addObject("currentQuestion", questionsToSave.size()+1);
			logger.debug("User entered not correct data while creating test with title {}", newTest.getTitle());
			return model;
		}
				
		if(questionNumb-questionsToSave.size() == 1)
			questionsToSave.add(question);		
			
		saveCurrentQuestionToList(model);
		
		model.setViewName("addquestion");
		logger.debug("User has added new question for test with title {}", newTest.getTitle());

		return model;
	}
 
	/**
	 * 
	 * Save new test
	 * 
	 * @param question New Question object to be saved 
	 * @param result Validation results of new Question object
	 * @param redirectAttributes Redirected attributes for a view page
	 * @param questionNumb Current Question number to be displayed 
	 * @param request HttpServletRequest object of current request
	 * 
	 */	
	@RequestMapping(value = "/tests/save", method = RequestMethod.POST)
	public ModelAndView saveNewTest(@ModelAttribute("questionForm") @Validated Question question,			
			BindingResult result, final RedirectAttributes redirectAttributes,
			@RequestParam(value = "questionNumb", required = true) Integer questionNumb,
			HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView();
		
		if (result.hasErrors()) {
			model.addObject("currentQuestion", questionsToSave.size()+1);
			model.setViewName("addquestion");
			logger.debug("User entered not correct data while creating test with title {}", newTest.getTitle());
			return model;
		}
		
		showAddQuestionForm(question, result, redirectAttributes, questionNumb, request);
		
		Test newTestWithId = testService.saveOrUpdate(newTest);
		
		for(Question questionSave : questionsToSave) {
			questionSave.setTest(newTestWithId);
			Question newQuestionSave = questionService.saveOrUpdate(questionSave);
			for(Answer answerSave : questionSave.getAnswer()) {
				answerSave.setQuestion(newQuestionSave);
				answerService.saveOrUpdate(answerSave);
			}
		}
		questionsToSave.clear();
		newTest = new Test();
				
		model.setViewName("redirect:/category/"+newTestWithId.getCategory().getCategoryId());
		logger.debug("New test with title {}( id={} ) was successfully saved in DB", newTestWithId.getTitle(), newTestWithId.getTestId());
		
		return model;
	}

	/**
	 * 
	 * Prepare Question object to display at question form
	 * 
	 * @param model Current ModelAndView object for processing current request
	 * 
	 */	
	private void saveCurrentQuestionToList(ModelAndView model) {
		
		Question questionToAdd = new Question();		
		List<Answer> answerToAdd = new ArrayList<>();
		answerToAdd.add(new Answer());
		answerToAdd.add(new Answer());
		questionToAdd.setAnswer(answerToAdd);		
		model.addObject("currentQuestion", questionsToSave.size()+1);
		model.addObject("questionForm", questionToAdd);
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
	
	public void setTestService(TestService testService) {
		this.testService = testService;
	}
	
	public TestService getTestService() {
		return this.testService;
	}
	
	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}
	
	public StatisticsService getStatisticsService() {
		return this.statisticsService;
	}
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public CategoryService getCategoryService() {
		return this.categoryService;
	}
	
}
