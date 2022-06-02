package com.artyom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GameCreationDTO {
    private Integer questionCount;
    private Integer minValue;
    private Integer maxValue;
}
