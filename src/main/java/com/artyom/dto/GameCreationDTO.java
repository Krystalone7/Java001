package com.artyom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameCreationDTO {
    private Integer questionCount;
    private Integer minValue;
    private Integer maxValue;
}
