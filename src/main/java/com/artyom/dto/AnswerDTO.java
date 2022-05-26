package com.artyom.dto;

import com.artyom.interfaces.Answerable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AnswerDTO implements Answerable {
    private Long questionId;
    private Boolean isCorrect;
    private String correctAnswer;
}
