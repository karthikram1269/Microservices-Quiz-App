package com.wipro.gk.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.gk.entities.QuestionWrapper;
import com.wipro.gk.entities.Quiz;
import com.wipro.gk.entities.Response;
import com.wipro.gk.feign.QuizInterface;
import com.wipro.gk.repos.QuizRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {

	private final QuizRepository quizRepository;
	private final QuizInterface quizInterface;

	public Quiz createQuiz(String category, String level, String title) {

		List<Integer> questionIdsForQuiz = quizInterface.getQuestionsForQuiz(category, level);

		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionsIds(questionIdsForQuiz);
		return quizRepository.save(quiz);
	}

	public List<QuestionWrapper> getQuizQuestions(Integer id) {
		Quiz quiz =quizRepository.findById(id).get();
		List<Integer> questionIds = quiz.getQuestionsIds();
		
		return quizInterface.getQuestionforQuizbyQuizID(questionIds);
		
	}

	public Integer calculateResult(Integer id, List<Response> responses) {
		// TODO Auto-generated method stub
		return quizInterface.getScoreQuiz(responses);
		
	}

	

}
