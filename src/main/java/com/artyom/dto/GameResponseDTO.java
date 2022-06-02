package com.artyom.dto;


import com.artyom.interfaces.Answerable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class GameResponseDTO implements Answerable {
    private final Long gameId;
    private final int questionCount;
}
