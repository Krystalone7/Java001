package com.artyom.services;

import com.artyom.controllers.JSONPlaceHolderClient;
import com.artyom.dto.*;
import com.artyom.entities.Category;
import com.artyom.entities.Question;
import com.artyom.repositories.CategoryRepository;
import com.artyom.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.*;

@Service
public class QuestionService {
    private final QuestionRepository repository;
    private final CategoryRepository categoryRepository;
    private final JSONPlaceHolderClient client;

    @Autowired
    public QuestionService(QuestionRepository repository, CategoryRepository categoryRepository, JSONPlaceHolderClient client, EntityManager entityManager) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.client = client;
    }

    public Question getRandomQuestion(){
        return repository.getRandom();
    }

    public Question getQuestionById(Long id){
        try {
            Question question = repository.findQuestionById(id);
        }catch (Exception e){
            return null;
        }
        return repository.findQuestionById(id);
    }

    public Question saveQuestion(QuestionCreationDTO questionCreationDTO){
        Category category =  categoryRepository.saveAndFlush(new Category(questionCreationDTO.getCategory().getId(), questionCreationDTO.getCategory().getTitle()));
        Question question = new Question(
                questionCreationDTO.getId(),
                questionCreationDTO.getQuestion(),
                category,
                questionCreationDTO.getValue(),
                questionCreationDTO.getAnswer()
        );
        return repository.saveAndFlush(question);
    }

    public ResponseDTO<QuestionDTO> getRandomQuestionFromDB(){
        Question question = getRandomQuestion();
        return new ResponseDTO<>("OK", "OK",
                new QuestionDTO(question.getId()
                        , question.getQuestionText()
                        , new CategoryDTO(question.getCategory().getId()
                        , question.getCategory().getName())
                        , question.getDifficulty()));
    }

    public ResponseDTO<QuestionDTO> getRandomQuestionFromApi(){
        try {
            List<QuestionCreationDTO> questions = client.getQuestionFromApi();
            QuestionCreationDTO questionCreationDTO = questions.get(0);
            Question question = saveQuestion(questionCreationDTO);
            return new ResponseDTO<>("OK", "OK",
                    new QuestionDTO(
                            question.getId(),
                            question.getQuestionText(),
                            new CategoryDTO(question.getCategory().getId(), question.getCategory().getName()),
                            question.getDifficulty()));
        } catch (Exception e){
            return new ResponseDTO<>("ERROR", "Error with Api", null);
        }
    }

    public ResponseDTO<AnswerDTO> checkAnswer(AnswerCreationDTO creationDTO){
        Question question;
        try {
            question = getQuestionById(creationDTO.getQuestionId());
            if (question.getAnswer().equals(creationDTO.getAnswer())) {
                return new ResponseDTO<>("OK", "OK"
                        , new AnswerDTO(creationDTO.getQuestionId()
                        , true
                        , question.getAnswer()));
            } else {
                return new ResponseDTO<>("OK", "OK"
                        , new AnswerDTO(creationDTO.getQuestionId()
                        , false
                        , question.getAnswer()));
            }
        } catch (NullPointerException e){
            return new ResponseDTO<>("ERROR", "Question with questionId: " + creationDTO.getQuestionId().toString() + " doesnt exist", null);
        } catch (Exception e){
            return new ResponseDTO<>("ERROR", "Error with request body", null);
        }
    }
}
