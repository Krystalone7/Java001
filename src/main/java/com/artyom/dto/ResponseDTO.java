package com.artyom.dto;

import com.artyom.interfaces.Answerable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDTO<T extends Answerable>{
    private final String code;
    private final String description;
    private final T result;
}
