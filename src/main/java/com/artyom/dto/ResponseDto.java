package com.artyom.dto;

import com.artyom.interfaces.Answerable;
import lombok.Getter;

@Getter
public class ResponseDto<T extends Answerable>{
    private final String code;
    private final String description;
    private final T result;

    public ResponseDto(String code, String description, T result) {
        this.code = code;
        this.description = description;
        this.result = result;
    }
}
