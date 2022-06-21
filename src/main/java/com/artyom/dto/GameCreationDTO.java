package com.artyom.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class GameCreationDTO {
    private final Integer questionCount;
    private final Integer minValue;
    private final Integer maxValue;

    @JsonCreator
    public GameCreationDTO(
            @JsonProperty("questionCount") Integer questionCount,
            @JsonProperty("minValue") Integer minValue,
            @JsonProperty("maxValue") Integer maxValue
    ){
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.questionCount = questionCount;
    }
}
