package com.artyom.dto;

import com.artyom.interfaces.Answerable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
public class QuestionCreationDTO implements Answerable, Serializable {
    private final Long id;
    private final String question;
    private final Integer value;
    private final String answer;
    private final CategoryDTO category;

    @JsonCreator
    public QuestionCreationDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("question") String question,
            @JsonProperty("value") Integer value,
            @JsonProperty("answer") String answer,
            @JsonProperty("category") CategoryDTO category
    ){
        this.id = id;
        this.question = question;
        this.value = value;
        this.answer = answer;
        this.category = category;
    }
}
