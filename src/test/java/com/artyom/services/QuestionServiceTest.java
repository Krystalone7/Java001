package com.artyom.services;

import com.artyom.controllers.JSONPlaceHolderClient;
import com.artyom.dto.*;
import com.artyom.entities.Category;
import com.artyom.entities.Question;
import com.artyom.repositories.CategoryRepository;
import com.artyom.repositories.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class QuestionServiceTest {

    @InjectMocks
    private QuestionService questionService;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private JSONPlaceHolderClient client;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        questionService = new QuestionService(questionRepository, categoryRepository, client);
    }

    @Test
    public void getRandomQuestionTest(){
        Question question = new Question(
                1L,
                "Test text",
                new Category(
                        1L,
                        "Test category"
                ),
                100,
                "Answer",
                null
        );
        when(questionRepository.getRandom()).thenReturn(question);
        Question testQuestion = questionService.getRandomQuestion();
        Assertions.assertInstanceOf(Question.class, testQuestion);
    }

    @Test
    public void getQuestionByIdTest(){
        Question question = new Question(
                1L,
                "Test text",
                new Category(
                        1L,
                        "Test category"
                ),
                100,
                "Answer",
                null
        );
        when(questionRepository.findQuestionById(anyLong())).thenReturn(java.util.Optional.of(question));
        Question testQuestion = questionService.getQuestionById(1L);
        Assertions.assertInstanceOf(Question.class, testQuestion);
    }

    @Test
    public void getRandomFromApiTest(){
        QuestionCreationDTO questionCreationDTO = new QuestionCreationDTO(
                1L,
                "Test text",
                100,
                "Answer",
                new CategoryDTO(
                        1L,
                        "testCategory"
                )
        );
        Category category = new Category(
                1L,
                "Test category"
        );
        Question question = new Question(
                1L,
                "Test text",
                category,
                100,
                "Answer",
                null
        );
        List<QuestionCreationDTO> questionList = List.of(questionCreationDTO);
        when(client.getQuestionFromApi()).thenReturn(questionList);
        when(categoryRepository.saveAndFlush(any(Category.class))).thenReturn(category);
        when(questionRepository.saveAndFlush(any(Question.class))).thenReturn(question);
        ResponseDTO<QuestionDTO> responseDTO = questionService.getRandomQuestionFromApi();
        Assertions.assertInstanceOf(QuestionDTO.class, responseDTO.getResult());
    }

    @Test
    public void checkTrueAnswerTest(){
        Question question = new Question(
                1L,
                "Test text",
                new Category(
                        1L,
                        "Test category"
                ),
                100,
                "Answer",
                null
        );
        when(questionRepository.findQuestionById(anyLong())).thenReturn(java.util.Optional.of(question));
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
        Question question = new Question(
                1L,
                "Test text",
                new Category(
                        1L,
                        "Test category"
                ),
                100,
                "Answer",
                null
        );
        when(questionRepository.findQuestionById(anyLong())).thenReturn(java.util.Optional.of(question));
        ResponseDTO<AnswerDTO> responseDTO = questionService.checkAnswer(
                new AnswerCreationDTO(
                        question.getId(),
                        "Fake answer"
                )
        );
        Assertions.assertEquals(false, responseDTO.getResult().getIsCorrect());
    }

}