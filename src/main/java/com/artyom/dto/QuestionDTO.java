package com.artyom.dto;

import com.artyom.interfaces.Answerable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO implements Answerable {
    private Long id;
    private String question;
    private CategoryDTO category;
    private Integer difficulty;

    public QuestionDTO(Long id, String question, CategoryDTO category, Integer difficulty) {
        this.id = id;
        this.question = question;
        this.category = category;
        this.difficulty = difficulty;
    }
}
