package com.artyom.repositories;

import com.artyom.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findQuestionById(Long id);
}
