package com.artyom.dto;

import com.artyom.interfaces.Answerable;
import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class QuestionCreationDTO implements Answerable, Serializable {
    private final Long id;
    private final String question;
    private final Integer value;
    private final String answer;
    private final CategoryDTO category;

}
