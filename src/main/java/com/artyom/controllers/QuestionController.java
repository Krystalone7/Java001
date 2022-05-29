package com.artyom.controllers;

import com.artyom.dto.*;
import com.artyom.entities.Question;
import com.artyom.services.QuestionService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@RestController
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/question/random")
    public ResponseDTO<QuestionDTO> getRandomQuestion() {
        return questionService.getRandomQuestionFromDB();
    }

    @PostMapping("/question/check")
    public ResponseDTO<AnswerDTO> checkAnswer(@RequestBody AnswerCreationDTO creationDTO) {
        return questionService.checkAnswer(creationDTO);
    }

    @GetMapping("/api/random")
    public ResponseDTO<QuestionDTO> getRandomQuestionFromApi() {
        return questionService.getRandomQuestionFromApi();
    }
}

