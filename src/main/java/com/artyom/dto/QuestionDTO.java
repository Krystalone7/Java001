package com.artyom.dto;

import com.artyom.interfaces.Answerable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuestionDTO implements Answerable {
    private Long id;
    private String question;
    private CategoryDTO category;
    private Integer difficulty;
}
