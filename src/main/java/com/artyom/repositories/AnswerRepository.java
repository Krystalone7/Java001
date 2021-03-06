package com.artyom.repositories;

import com.artyom.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Answer findAnswerByGameIdAndQuestionId(Long gameId, Long questionId);
}
