package com.artyom.dto;


import liquibase.pro.packaged.G;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameResponseDTO {
    private Long gameId;
    private int questionCount;
}
