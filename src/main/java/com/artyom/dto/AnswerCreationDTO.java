package com.artyom.dto;

import lombok.Getter;

@Getter
public class AnswerCreationDTO {
    private final Long questionId;
    private final String answer;

    public AnswerCreationDTO(Long questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }
}
