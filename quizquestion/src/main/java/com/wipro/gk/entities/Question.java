package com.wipro.gk.entities;

import com.wipro.gk.enums.Category;
import com.wipro.gk.enums.DifficultyLevel;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long QuestionID;
	private String questionTitle;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String correctAnswer;
	
	@Enumerated(EnumType.STRING)
	private Category category;
	
	@Enumerated(EnumType.STRING)
	private DifficultyLevel difficultyLevel;
}
