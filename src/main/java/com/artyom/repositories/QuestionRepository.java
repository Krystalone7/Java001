package com.artyom.repositories;

import com.artyom.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * FROM quiz.questions q ORDER BY random() LIMIT 1", nativeQuery = true)
    Question getRandom();
    Optional<Question> findQuestionById(Long id);
    List<Question> findQuestionsByDifficultyBetween(Integer difficulty, Integer difficulty2);
    @Transactional
    void deleteById(Long id);
}