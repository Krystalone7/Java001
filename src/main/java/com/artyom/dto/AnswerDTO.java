package com.artyom.dto;

import com.artyom.interfaces.Answerable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO implements Answerable {
    private Long questionId;
    private Boolean isCorrect;
    private String correctAnswer;

    public AnswerDTO(Long questionId, Boolean isCorrect, String correctAnswer) {
        this.questionId = questionId;
        this.isCorrect = isCorrect;
        this.correctAnswer = correctAnswer;
    }
}
