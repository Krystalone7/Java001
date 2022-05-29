package com.artyom.dto;

import com.artyom.interfaces.Answerable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class AnswerDTO implements Answerable {
    private final Long questionId;
    private final Boolean isCorrect;
    private final String correctAnswer;
}
