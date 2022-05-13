package com.artyom.dto;

import com.artyom.interfaces.Answerable;
import lombok.Getter;

@Getter
public class QuestionDTO implements Answerable {
    private final Long id;
    private final String questionText;
    private final CategoryDTO category;
    private final Integer difficulty;

    public QuestionDTO(Long id, String questionText, CategoryDTO category, Integer difficulty) {
        this.id = id;
        this.questionText = questionText;
        this.category = category;
        this.difficulty = difficulty;
    }
}
