package com.wipro.gk.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.gk.entities.QuestionWrapper;
import com.wipro.gk.entities.Quiz;
import com.wipro.gk.entities.Response;
import com.wipro.gk.service.QuizService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/quiz")
public class QuizController {

	private final QuizService quizService;
	


    @CircuitBreaker(name="createQuizCircuitBreaker",fallbackMethod="createQuizFallBackMethod")
    @PostMapping("/create")
    public Quiz createQuiz(
            @RequestParam String category,
            @RequestParam String level,               
            @RequestParam String title) {
        return quizService.createQuiz(category, level, title);
    }
    
    public Quiz createQuizFallBackMethod(
    		@RequestParam String category,
            @RequestParam String level,               
            @RequestParam String title, Throwable throwable) {
    
    	Quiz fallBackQuiz = new Quiz();
    	fallBackQuiz.setTitle(" Our Question Service is down!, please try after some time");
    	return fallBackQuiz; 
    	
    }
    
    @GetMapping("/getQuizByid/{id}")
    public List<QuestionWrapper>    getQuizByID(@PathVariable Integer id){
    	return quizService.getQuizQuestions(id);
    }
    
    @PostMapping("/submitQuiz/{id}")
    public Integer submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses)
    {
		return quizService.calculateResult(id,responses);
    	
    }
    
/*   
    
    @GetMapping("/getQuizByID/{id}")                  
    public List<QuestionWrapper> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }
    
    
    
    @PostMapping("/submitQuiz/{id}")
    public Integer submitQuiz(@PathVariable int id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }
    */
}
