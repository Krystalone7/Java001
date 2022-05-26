package com.artyom.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerCreationDTO {
    private Long questionId;
    private String answer;
}
