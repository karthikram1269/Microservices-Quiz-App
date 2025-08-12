package com.wipro.gk.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.gk.entities.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}
