package com.artyom.controllers;

import com.artyom.dto.*;
import com.artyom.entities.Question;
import com.artyom.services.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
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
        Question question = questionService.getQuestionById(creationDTO.getQuestionId());
        if (question.getAnswer().equals(creationDTO.getAnswer())) {
            return new ResponseDto<>("OK", "OK"
                    , new AnswerDTO(creationDTO.getQuestionId()
                    , true
                    , creationDTO.getAnswer()));
        } else {
            return new ResponseDto<>("OK", "OK"
                    , new AnswerDTO(creationDTO.getQuestionId()
                    , false
                    , question.getAnswer()));
        }

        //return new ResponseDto<>("ERROR", "Question is not found", null);
    }
}

