package com.artyom.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class AnswerGameDTO {
    private final String answer;

    @JsonCreator
    public AnswerGameDTO(
            @JsonProperty("answer") String answer
    )
    {
        this.answer = answer;
    }
}
