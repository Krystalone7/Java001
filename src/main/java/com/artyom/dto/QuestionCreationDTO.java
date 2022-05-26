package com.artyom.dto;

import com.artyom.interfaces.Answerable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionCreationDTO implements Answerable, Serializable {
    private Long id;
    private String question;
    private Integer value;
    private String answer;
    private CategoryDTO category;
}
