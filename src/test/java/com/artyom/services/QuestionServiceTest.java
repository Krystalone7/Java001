package com.artyom.services;

import com.artyom.dto.*;
import com.artyom.entities.Question;
import com.artyom.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
class QuestionServiceTest {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Test
    public void getRandomQuestionTest(){
        Question question = questionService.getRandomQuestion();
        Assert.isInstanceOf(Question.class, question);
    }
    @Test
    public void getQuestionByIdTest(){
        Question question = questionService.getRandomQuestion();
        Assertions.assertEquals(question.getId(), questionService.getQuestionById(question.getId()).getId());
    }
    @Test
    public void saveQuestionTest(){
        Question question = questionService.saveQuestion(
                new QuestionCreationDTO(
                        (long)100,
                        "Test question text",
                        200,
                        "Test answer",
                        new CategoryDTO(
                                (long) 100,
                                "test"
                        )
                )
        );
        Assertions.assertEquals("Test question text", question.getQuestionText());
        Assertions.assertEquals("Test answer", question.getAnswer());
        //Clearing test data
        Long categoryId = question.getCategory().getId();
        questionService.deleteQuestionById(question.getId());
        categoryRepository.deleteCategoryById(categoryId);
    }
    @Test
    public void getRandomFromDBTest(){
        ResponseDTO<QuestionDTO> responseDTO = questionService.getRandomQuestionFromApi();
        Assertions.assertNotNull(responseDTO.getResult());
        Assertions.assertNotNull(responseDTO.getResult().getCategory());
        Assertions.assertNotNull(responseDTO.getResult().getQuestion());
    }
    @Test
    public void getRandomFromApiTest(){
        ResponseDTO<QuestionDTO> responseDTO = questionService.getRandomQuestionFromDB();
        Assertions.assertNotNull(responseDTO.getResult());
        Assertions.assertNotNull(responseDTO.getResult().getCategory());
        Assertions.assertNotNull(responseDTO.getResult().getQuestion());
    }
    @Test
    public void checkTrueAnswerTest(){
        Question question = questionService.getRandomQuestion();
        ResponseDTO<AnswerDTO> responseDTO = questionService.checkAnswer(
                new AnswerCreationDTO(
                        question.getId(),
                        question.getAnswer()
                )
        );
        Assertions.assertEquals(true, responseDTO.getResult().getIsCorrect());
    }
    @Test
    public void checkFalseAnswerTest(){
        Question question = questionService.getRandomQuestion();
        ResponseDTO<AnswerDTO> responseDTO = questionService.checkAnswer(
                new AnswerCreationDTO(
                        question.getId(),
                        "Fake answer"
                )
        );
        Assertions.assertEquals(false, responseDTO.getResult().getIsCorrect());
    }
}