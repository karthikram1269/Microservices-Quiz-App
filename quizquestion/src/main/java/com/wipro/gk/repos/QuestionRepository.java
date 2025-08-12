package com.wipro.gk.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wipro.gk.entities.Question;
import com.wipro.gk.enums.Category;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    


	@Query(value = "SELECT * FROM question q WHERE q.category = :category AND q.difficulty_level = :difficulty ORDER BY RAND() LIMIT 3", nativeQuery = true)

	List<Integer> findRandomQuestionsByCategoryAndLevel(String category, String difficulty);

	List<Question> findByCategory(Category category);

}