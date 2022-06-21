package com.artyom.dto;

import com.artyom.entities.Answer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
public class AnswerCreationDTO {
    private final Long questionId;
    private final String answer;

    @JsonCreator
    public AnswerCreationDTO(
            @JsonProperty("questionId") Long questionId,
            @JsonProperty("answer") String answer
    ){
        this.questionId = questionId;
        this.answer = answer;
    }
}
