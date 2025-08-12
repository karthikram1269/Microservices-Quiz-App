package com.wipro.gk.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.wipro.gk.entities.QuestionWrapper;
import com.wipro.gk.entities.Response;

@FeignClient(name="QUESTION-SERVICE",path="/api/v1/question")
public interface QuizInterface {

	
    @GetMapping("/generateQuestionsForQuiz")
    public List<Integer> getQuestionsForQuiz(@RequestParam String category,@RequestParam String  difficultyLevel);
    
    @PostMapping("/getQuestions")
    public List<QuestionWrapper> getQuestionforQuizbyQuizID(@RequestBody List<Integer> questionIds);
    
    
    @PostMapping("/getScore")
    public Integer getScoreQuiz(@RequestBody List<Response> responses) ;
    
}
