package com.artyom.repositories;

import com.artyom.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * FROM quiz.questions q ORDER BY random() LIMIT 1", nativeQuery = true)
    Question getRandom();

    Question findQuestionById(Long id);
}
