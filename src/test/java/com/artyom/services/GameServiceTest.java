package com.artyom.services;

import com.artyom.dto.*;
import com.artyom.entities.Answer;
import com.artyom.entities.Category;
import com.artyom.entities.Game;
import com.artyom.entities.Question;
import com.artyom.repositories.AnswerRepository;
import com.artyom.repositories.GameRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GameServiceTest {
    @InjectMocks
    private GameService gameService;
    @Mock
    private GameRepository gameRepository;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private AnswerRepository answerRepository;
    @Mock
    private QuestionService questionService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        gameService = new GameService(gameRepository, questionRepository, questionService, answerRepository);
    }

    @Test
    public void createGameTest(){

        Game game = new Game(1L, 2, 0, 400, true, new ArrayList<>());
        when(gameRepository.save(any(Game.class))).thenReturn(game);
        Category category = new Category(1L, "Test category");
        List<Question> questions = List.of(
                new Question(
                        1L,
                        "Text1",
                        category,
                        100,
                        "Answer1",
                        null
                ),
                new Question(
                        2L,
                        "Text2",
                        category,
                        200,
                        "Answer2",
                        null
                ),
                new Question(
                        3L,
                        "Text3",
                        category,
                        300,
                        "Answer3",
                        null
                )
        );
        when(questionRepository.findQuestionsByDifficultyBetween(any(Integer.class), any(Integer.class))).thenReturn(questions);
        ResponseDTO<GameResponseDTO> responseDTO = gameService.createNewGame(
                new GameCreationDTO(
                        3, 0, 400
                )
        );
        Assertions.assertEquals(2, responseDTO.getResult().getQuestionCount());
        System.out.println(responseDTO.getResult().getGameId());
    }

    @Test
    public void getQuestionFromGameTest(){
        Category category = new Category(1L, "Test category");
        Game game = new Game(1L, 2, 0, 400, true, List.of(
                new Question(
                        1L,
                        "Text1",
                        category,
                        100,
                        "Answer1",
                        null
                ),
                new Question(
                        2L,
                        "Text2",
                        category,
                        200,
                        "Answer2",
                        null
                ),
                new Question(
                        3L,
                        "Text3",
                        category,
                        300,
                        "Answer3",
                        null
                )
        ));
        when(gameRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(game));
        ResponseDTO<QuestionDTO> responseDTO = gameService.getQuestionFromGame(1L, 1);
        Assertions.assertEquals(1L, responseDTO.getResult().getId());
    }

    @Test
    public void checkAnswerFromGameTest(){
        Category category = new Category(1L, "Test category");
        Game game = new Game(1L, 2, 0, 400, true, List.of(
                new Question(
                        1L,
                        "Text1",
                        category,
                        100,
                        "Answer1",
                        null
                ),
                new Question(
                        2L,
                        "Text2",
                        category,
                        200,
                        "Answer2",
                        null
                ),
                new Question(
                        3L,
                        "Text3",
                        category,
                        300,
                        "Answer3",
                        null
                )
        ));
        when(gameRepository.findById(any(Long.class))).thenReturn(Optional.of(game));
        when(answerRepository.findAnswerByGameIdAndQuestionId(any(Long.class), any(Long.class))).thenReturn(null);
        ResponseDTO<AnswerDTO> answer = new ResponseDTO<>(
                "OK", "OK",
                new AnswerDTO(
                        1L, true, "Answer1"
                )
        );
        when(questionService.checkAnswer(any(AnswerCreationDTO.class))).thenReturn(answer);
        ResponseDTO<AnswerDTO> responseDTO = gameService.checkAnswerFromGame(
                1L, 1,
                new AnswerGameDTO("Answer1")
        );
        Assertions.assertEquals(true, responseDTO.getResult().getIsCorrect());
    }

    @Test
    public void finishGameTest(){
        Category category = new Category(1L, "Test category");
        Game game = new Game(1L, 2, 0, 400, true, List.of(
                new Question(
                        1L,
                        "Text1",
                        category,
                        100,
                        "Answer1",
                        null
                ),
                new Question(
                        2L,
                        "Text2",
                        category,
                        200,
                        "Answer2",
                        null
                ),
                new Question(
                        3L,
                        "Text3",
                        category,
                        300,
                        "Answer3",
                        null
                )
        ));
        Answer answer = new Answer(1L, 2, 1L, true);
        when(gameRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(game));
        when(answerRepository.findAnswerByGameIdAndQuestionId(any(Long.class), any(Long.class))).thenReturn(answer);
        ResponseDTO<FinishGameDTO> responseDTO = gameService.finishGame(1L);
        Assertions.assertEquals(false, game.getIsActive());
    }
}
