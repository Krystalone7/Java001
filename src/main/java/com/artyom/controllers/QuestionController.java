package com.artyom.controllers;

import com.artyom.dto.*;
import com.artyom.entities.Question;
import com.artyom.services.QuestionService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import liquibase.pro.packaged.Q;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@RestController
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/question/random")
    public ResponseDto<QuestionDTO> getRandomQuestion() {
        Question question = questionService.getRandomQuestion();
        return new ResponseDto<>("OK", "OK",
                new QuestionDTO(question.getId()
                        , question.getQuestionText()
                        , new CategoryDTO(question.getCategory().getId()
                        , question.getCategory().getName())
                        , question.getDifficulty()));
    }

    @PostMapping("/question/check")
    public ResponseDto<AnswerDTO> checkAnswer(@RequestBody AnswerCreationDTO creationDTO) {
        Question question = null;
        try {
            question = questionService.getQuestionById(creationDTO.getQuestionId());
            if (question.getAnswer().equals(creationDTO.getAnswer())) {
                return new ResponseDto<>("OK", "OK"
                        , new AnswerDTO(creationDTO.getQuestionId()
                        , true
                        , question.getAnswer()));
            } else {
                return new ResponseDto<>("OK", "OK"
                        , new AnswerDTO(creationDTO.getQuestionId()
                        , false
                        , question.getAnswer()));
            }
        } catch (NullPointerException e){
            return new ResponseDto<>("ERROR", "Question with questionId: " + creationDTO.getQuestionId().toString() + " doesnt exist", null);
        } catch (Exception e){
            return new ResponseDto<>("ERROR", "Error with request body", null);
        }
    }
    @GetMapping("/api/random")
    public ResponseDto<QuestionDTO> getRandomQuestionFromApi() throws IOException {
        try {
            URL url = new URL("http://jservice.io/api/random");
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayList arr =objectMapper.readValue(url, ArrayList.class);
            LinkedHashMap<String, Object> map =  (LinkedHashMap)(arr.get(0));

            //saving question to database
            Question question = questionService.saveQuestion(map);

            return new ResponseDto<>("OK", "OK",
                    new QuestionDTO(
                            question.getId(),
                            question.getQuestionText(),
                            new CategoryDTO(question.getCategory().getId(), question.getCategory().getName()),
                            question.getDifficulty()));
        }catch (Exception e){
            System.out.println("Exception");
            return new ResponseDto<>("ERROR", "Troubles with the resource", null);
        }

    }
}

