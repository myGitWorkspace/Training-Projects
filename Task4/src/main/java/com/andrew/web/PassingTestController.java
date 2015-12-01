package com.andrew.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.andrew.model.User;
import com.andrew.service.AnswerService;
import com.andrew.service.QuestionService;
import com.andrew.service.StatisticsService;
import com.andrew.service.TestService;
import com.andrew.service.UserService;
import com.andrew.validator.QuestionFormValidator;

/**
 * 
 * Controller class used to display questions of test and to save results
 *
 */
@Controller
public class PassingTestController {

	@Autowired
	QuestionFormValidator questionFormValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(questionFormValidator);
	}
	
	@Autowired
	private AnswerService answerService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private StatisticsService statisticsService;
	@Autowired
	private TestService testService;
	@Autowired
	private UserService userService;
	
	private final Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	List<Question> questionsForTest = new ArrayList<>();
	int currentQuestionIndex = 0;
	int numberCorrectQuestions = 0;
	int numberWrongQuestions = 0;
	int currentTestId = 0;
	
	/**
	 * 
	 * Handle a passing test process
	 * 
	 * @param testId Id number of current test
	 * @param questionNumb Number of question to be saved 
	 * @param questionWithResults Question object filled with data from question form
	 * @param result Validation results of new Question object to be saved
	 * @param redirectAttributes Redirected attributes for a view page
	 * @param request HttpServletRequest object of current request
	 * @param categoryId Id number of current category
	 * 
	 */
	@RequestMapping(value = "/tests/start", method = RequestMethod.POST)
	public ModelAndView passTest(@RequestParam(value = "testId", required = false) Integer testId,
			@RequestParam(value = "questionNumb", required = false) Integer questionNumb,
			@ModelAttribute("currentQuestionForm") @Validated Question questionWithResults,
			BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request,
			@RequestParam(value = "categoryId", required = false) Integer categoryId ) {
		
		ModelAndView model = new ModelAndView();		
		
		if(request.getHeader("Referer").indexOf("/tests/start") == -1) {
			questionsForTest.clear();
			currentQuestionIndex = 0;
			numberCorrectQuestions = 0;
			numberWrongQuestions = 0;
			currentTestId = -1;
		}
		
		boolean isReloadPage = false;
		if(currentQuestionIndex > questionNumb) {
			isReloadPage = true;
			currentQuestionIndex = questionNumb;
			if(currentQuestionIndex == 0)
				questionsForTest.clear();
		}
		
		if (currentQuestionIndex!=0 && result.hasErrors()) {
			model.setViewName("showquestion");
			model.addObject("numberCorrectAnswers", getOriginCorrectAnswersNumber(questionsForTest.get(currentQuestionIndex-1)));
			model.addObject("totalNumberQuestions", questionsForTest.size());
			model.addObject("currentQuestionId", currentQuestionIndex);
			logger.debug("User has not filled the Test form correctly. Will get a try again");
			return model;
		}
		
		if(currentQuestionIndex > 0 && currentQuestionIndex == questionsForTest.size()) {
			// save results of the test to DB
			checkAnswersCorrectOrWrong(questionWithResults);
			
			Statistics statistics = new Statistics();
			statistics.setCorrectAnswers(numberCorrectQuestions);
			statistics.setWrongAnswers(numberWrongQuestions);
			statistics.setDate(new Date());
			Test test = new Test();
			test.setTestId(currentTestId);
			User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
			statistics.setTest(test);
			statistics.setUser(user);
			statisticsService.saveOrUpdate(statistics);			
			
			model.addObject("testTitle", testService.findById(currentTestId).getTitle());
			model.addObject("numberCorrectAnswers", numberCorrectQuestions);
			model.addObject("numberWrongAnswers", numberWrongQuestions);
			model.addObject("totalNumberQuestions", questionsForTest.size());
			model.addObject("progressResult", numberCorrectQuestions*100/questionsForTest.size());
			model.addObject("categoryId", categoryId);
			
			questionsForTest.clear();
			currentQuestionIndex = 0;
			numberCorrectQuestions = 0;
			numberWrongQuestions = 0;
			currentTestId = -1;
			model.setViewName("redirect:/tests/results");
			logger.debug("The test # id {} was passed by user", currentTestId);
		} else {
			if(currentQuestionIndex == 0) {
				currentTestId = testId;
				questionsForTest.addAll(questionService.findByTestId(testId));
				for(Question questn : questionsForTest)
					questn.setAnswer(answerService.findByQuestionId(questn.getQuestionId()));
			} else {
				// save results of the question to local List
				if(!isReloadPage)
					checkAnswersCorrectOrWrong(questionWithResults);
			}
			
			model.addObject("totalNumberQuestions", questionsForTest.size());
			model.addObject("currentQuestionId", currentQuestionIndex+1);
			Question nextQuestion = questionsForTest.get(currentQuestionIndex);
			Question nextQuestionClean = new Question();
			nextQuestionClean.setQuestionText(nextQuestion.getQuestionText());			
			nextQuestionClean.setAnswer( cloneListAnswers(nextQuestion.getAnswer()) );			
			model.addObject("currentQuestionForm", nextQuestionClean);
			model.addObject("numberCorrectAnswers", getOriginCorrectAnswersNumber(nextQuestion));
			model.addObject("categoryId", categoryId);
			model.setViewName("showquestion");
			currentQuestionIndex++;
			logger.debug("Show question # {} for Test with id {}", currentQuestionIndex, currentTestId);
		}
		
		return model;
	}
	
	/**
	 * 
	 * Get number of correct answers in question
	 * 
	 * @param nextQuestion Target Question object
	 * @return Number of correct answers in question
	 * 
	 */
	private int getOriginCorrectAnswersNumber(Question nextQuestion) {
		int count = 0;
		for(Answer answer : nextQuestion.getAnswer())
			if(answer.isIsCorrect())
				count++;
		return count;
	}
	
	/**
	 * 
	 * Check answers correct or wrong for currently passed question
	 * 
	 * @param questionWithResults Question object with users answers
	 * 
	 */
	private void checkAnswersCorrectOrWrong(Question questionWithResults) {
		boolean isCorrectQuestion = true;
		for(Answer answer : questionWithResults.getAnswer()){					
			for(Answer answerCorrect : questionsForTest.get(currentQuestionIndex-1).getAnswer()){
				if(answerCorrect.getAnswerId() == answer.getAnswerId()){					
					if(answerCorrect.isIsCorrect() != answer.isIsCorrect()) {
						isCorrectQuestion = false;
						break;
					}
				}
			}					
		}
		if(isCorrectQuestion)
			numberCorrectQuestions++;
		else
			numberWrongQuestions++;
	}
	
	/**
	 * 
	 * Make a clone object for the list of answers
	 * 
	 * @param sourceList Source list to be cloned
	 * @return Clone list of answers
	 * 
	 */
	private List<Answer> cloneListAnswers(List<Answer> sourceList) {
		List<Answer> cloneList = new ArrayList<>();
		for(Answer answer : sourceList) {
			Answer cloneAnswer = new Answer();
			cloneAnswer.setAnswerId(answer.getAnswerId());
			cloneAnswer.setAnswerText(answer.getAnswerText());
			cloneAnswer.setIsCorrect(false);
			cloneList.add(cloneAnswer);
		}
		return cloneList;
	}
	
	/**
	 * 
	 * Show results of current test
	 * 
	 * @param testTitle The title of test
	 * @param numberCorrectAnswers Number of correct answers 
	 * @param numberWrongAnswers Number of wrong answers
	 * @param totalNumberQuestions Total number of questions
	 * @param progressResult Total progress for test
	 * @param categoryId Id number of current category
	 * @param request HttpServletRequest object of current request
	 * 
	 */
	@RequestMapping(value = "/tests/results", method = RequestMethod.GET)
	public ModelAndView showResultsOfTest(@RequestParam(value = "testTitle", required = false) String testTitle,
			@RequestParam(value = "numberCorrectAnswers", required = false) Integer numberCorrectAnswers,
			@RequestParam(value = "numberWrongAnswers", required = false) Integer numberWrongAnswers,
			@RequestParam(value = "totalNumberQuestions", required = false) Integer totalNumberQuestions,
			@RequestParam(value = "progressResult", required = false) Integer progressResult,
			@RequestParam(value = "categoryId", required = false) Integer categoryId,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		
		model.addObject("testTitle", testTitle);
		model.addObject("numberCorrectAnswers", numberCorrectAnswers);
		model.addObject("numberWrongAnswers", numberWrongAnswers);
		model.addObject("totalNumberQuestions", totalNumberQuestions);
		model.addObject("progressResult", progressResult);		
		model.addObject("categoryId", categoryId);
		
		model.setViewName("results");
		logger.debug("Show page with results for test titled {}", testTitle);
		
		return model;
	}
	
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	public QuestionService getQuestionService() {
		return this.questionService;
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
	
	public void setAnswerService(AnswerService answerService) {
		this.answerService = answerService;
	}
	
	public AnswerService getAnswerService() {
		return this.answerService;
	}
	
}
