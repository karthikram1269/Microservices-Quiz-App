package com.wipro.gk.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.gk.entities.Question;
import com.wipro.gk.entities.QuestionWrapper;
import com.wipro.gk.entities.Response;
import com.wipro.gk.enums.Category;
import com.wipro.gk.repos.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
	
	private final QuestionRepository questionRepository;

	public List<Question> getQuestionsByCategory(Category category) {
		
           return questionRepository.findByCategory(category);

    }

    public Question addQuestion(Question question) {
        
        return questionRepository.save(question);
    }

	public List<Integer> getQuestionsForQuiz(String category, String difficultyLevel) {
		 List<Integer> questions= questionRepository.findRandomQuestionsByCategoryAndLevel(category, difficultyLevel);
		return questions;
	}
	
	public List<QuestionWrapper> getQuestionsFromId(List<Integer> questionIds) {
		 List<QuestionWrapper> wrappers = new ArrayList<>();
	        List<Question> questions = new ArrayList<>();

	        for(Integer id : questionIds){///repos layer chage this to Integer or
	            //questions.add(questionRepository.findById(id).get());
	        	questions.add(questionRepository.findById(id.longValue()).orElseThrow());
	        }
	
	        for(Question question : questions){
	            QuestionWrapper wrapper = new QuestionWrapper();
	            wrapper.setId(question.getQuestionID());
	            wrapper.setQuestionTitle(question.getQuestionTitle());
	            wrapper.setOption1(question.getOption1());
	            wrapper.setOption2(question.getOption2());
	            wrapper.setOption3(question.getOption3());
	            wrapper.setOption4(question.getOption4());
	            wrappers.add(wrapper);
	        }
		return wrappers;
	}

	public Integer getScoreforQuiz(List<Response> responses) {

		
	        int rightAnswerScore = 0;

	        for(Response response : responses){
	        	Question question = questionRepository
	        	        .findById(Long.valueOf(response.getId()))
	        	        .orElseThrow();
	            if(response.getResponse().equals(question.getCorrectAnswer()))
	            	rightAnswerScore++;
	        }
	        return rightAnswerScore;
	   }

	public List<QuestionWrapper> getQuestionforQuizbyQuizIDService(List<Integer> questionIds) {
		
		List<QuestionWrapper> wrappers=new ArrayList<>();
		List<Question> questions=new ArrayList<>();
	
              //[4,7,9]
				for(Integer id:questionIds)
				{
					
					questions.add(questionRepository.findById(id.longValue()).orElseThrow());
				
				}
				
				for(Question question:questions)
				{
					QuestionWrapper wrapper=new QuestionWrapper();
					
					wrapper.setId(question.getQuestionID());
					wrapper.setQuestionTitle(question.getQuestionTitle());
					wrapper.setOption1(question.getOption1());
					wrapper.setOption2(question.getOption2());
					wrapper.setOption3(question.getOption3());
					wrapper.setOption4(question.getOption4());
					wrappers.add(wrapper);
				}
				return wrappers;
	}

	public Integer calculateUserScore(List<Response> responses) {
		
		int rightAnswers=0;
		
		
		for(Response response:responses)
		{
			Question questions =questionRepository.findById(Long.valueOf(response.getId())).orElseThrow();
			
			if(response.getResponse().equals(questions.getCorrectAnswer()))
			{
				rightAnswers++;
			}
			
		}
		return rightAnswers;
		
	}
	

	
}