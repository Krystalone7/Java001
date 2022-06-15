package com.artyom.dto;

import com.artyom.interfaces.Answerable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionDTO implements Answerable {
    private final Long id;
    private final String question;
    private final CategoryDTO category;
    private final Integer difficulty;
}
