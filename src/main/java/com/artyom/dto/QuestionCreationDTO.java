package com.artyom.dto;

import com.artyom.interfaces.Answerable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionCreationDTO implements Answerable, Serializable {
    private Long id;
    private String question;
    private Integer value;
    private String answer;
    private CategoryDTO category;

}
