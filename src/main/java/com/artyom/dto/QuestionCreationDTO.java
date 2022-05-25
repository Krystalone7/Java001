package com.artyom.dto;

import com.artyom.interfaces.Answerable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class QuestionCreationDTO implements Answerable, Serializable {
    private Long id;
    private String question;
    private Integer value;

    public QuestionCreationDTO(Long id, String question, Integer value) {
        this.id = id;
        this.question = question;
        this.value = value;
    }
}
