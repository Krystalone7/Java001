package com.artyom.controllers;

import com.artyom.dto.QuestionCreationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@FeignClient(value = "jplaceholder", url = "http://jservice.io/api")
public interface JSONPlaceHolderClient {

    @GetMapping("/random")
    List<QuestionCreationDTO> getQuestionFromApi();

}
