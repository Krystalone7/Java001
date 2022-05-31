package com.artyom.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerCreationDTO {
    private Long questionId;
    private String answer;
}
