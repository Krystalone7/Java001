package com.artyom.services;

import com.artyom.controllers.JSONPlaceHolderClient;
import com.artyom.dto.AnswerCreationDTO;
import com.artyom.dto.AnswerDTO;
import com.artyom.dto.CategoryDTO;
import com.artyom.dto.QuestionCreationDTO;
import com.artyom.dto.QuestionDTO;
import com.artyom.dto.ResponseDTO;
import com.artyom.entities.Category;
import com.artyom.entities.Question;
import com.artyom.repositories.CategoryRepository;
import com.artyom.repositories.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private JSONPlaceHolderClient client;
    @InjectMocks
    private QuestionService questionService;

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
        Question testQuestion = questionService.getQuestionById(1L).orElse(null);
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

    @ParameterizedTest
    @MethodSource("answers")
    public void checkTrueAnswerTest(String answer, boolean isCorrect){
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
                        answer
                )
        );
        Assertions.assertEquals(isCorrect, responseDTO.getResult().getIsCorrect());
    }

    private static Stream<Arguments> answers(){
        return Stream.of(
                Arguments.of("Answer", true),
                Arguments.of("Fake Answer", false)
        );
    }
}