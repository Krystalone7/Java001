package com.artyom.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerCreationDTO {
    private Long questionId;
    private String answer;

    public AnswerCreationDTO() {
    }
}
