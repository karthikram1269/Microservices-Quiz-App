package com.wipro.gk.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.gk.entities.Question;
import com.wipro.gk.entities.QuestionWrapper;
import com.wipro.gk.entities.Response;
import com.wipro.gk.enums.Category;
import com.wipro.gk.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/question")
@RequiredArgsConstructor
public class QuestionController {

	
	private final QuestionService questionService;
	
    @GetMapping("/category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable Category category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public Question addQuestion(@RequestBody Question question){
        return  questionService.addQuestion(question);
    }
	

    @GetMapping("/generateQuestionsForQuiz")
    public List<Integer> getQuestionsForQuiz(@RequestParam String category,@RequestParam String  difficultyLevel){
    
    	return  questionService.getQuestionsForQuiz(category,difficultyLevel);
    }
    
    
    @PostMapping("/getQuestions")
    public List<QuestionWrapper> getQuestionforQuizbyQuizID(@RequestBody List<Integer> questionIds)
    {
    	return  questionService.getQuestionforQuizbyQuizIDService(questionIds);
    }
    
    @PostMapping("/getScore")
    public Integer getScoreQuiz(@RequestBody List<Response> responses) {
    	return questionService.calculateUserScore(responses);
    }

} 


