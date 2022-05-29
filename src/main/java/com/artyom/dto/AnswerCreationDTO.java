package com.artyom.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class AnswerCreationDTO {
    private final Long questionId;
    private final String answer;
}
