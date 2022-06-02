package com.artyom.dto;

import com.artyom.interfaces.Answerable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.HashMap;

@Getter
@AllArgsConstructor
public class FinishGameDTO implements Answerable {
    private final Long gameId;
    private final HashMap<String, String> results;
}
