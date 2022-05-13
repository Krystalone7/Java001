package com.artyom.dto;

import com.artyom.interfaces.Answerable;
import lombok.Getter;

@Getter
public class AnswerDTO implements Answerable {
    private final Long questionId;
    private final Boolean isCorrect;
    private final String correctAnswer;

    public AnswerDTO(Long questionId, Boolean isCorrect, String correctAnswer) {
        this.questionId = questionId;
        this.isCorrect = isCorrect;
        this.correctAnswer = correctAnswer;
    }
}
