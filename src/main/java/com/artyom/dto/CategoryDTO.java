package com.artyom.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CategoryDTO {
    private final Long id;
    private final String title;

    @JsonCreator
    public CategoryDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("title") String title
    ){
        this.id = id;
        this.title = title;
    }
}
