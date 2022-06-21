package com.artyom.services;

import com.artyom.dto.*;
import com.artyom.entities.Answer;
import com.artyom.entities.Game;
import com.artyom.entities.Question;
import com.artyom.repositories.AnswerRepository;
import com.artyom.repositories.GameRepository;
import com.artyom.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final QuestionRepository questionRepository;
    private final QuestionService questionService;
    private final AnswerRepository answerRepository;
    @Autowired
    public GameService(GameRepository gameRepository, QuestionRepository questionRepository, QuestionService questionService, AnswerRepository answerRepository) {
        this.gameRepository = gameRepository;
        this.questionRepository = questionRepository;
        this.questionService = questionService;
        this.answerRepository = answerRepository;
    }
    public ResponseDTO<GameResponseDTO> createNewGame(GameCreationDTO gameCreationDTO){
        Game game = gameRepository.save(new Game(
                gameCreationDTO.getQuestionCount(),
                gameCreationDTO.getMinValue(),
                gameCreationDTO.getMaxValue()));
        List<Question> questions = questionRepository.findQuestionsByDifficultyBetween(game.getMinValue(),game.getMaxValue());
        if (questions.size() < game.getQuestionCount()){
            return new ResponseDTO<>("Error", "There are not enough questions with this difficulty parameters", null);
        }
        for (int i = 0; i < game.getQuestionCount(); i++){
            game.getQuestions().add(questions.get(i));
        }
        game = gameRepository.save(game);
        return new ResponseDTO<>(
                "OK", "OK",
                new GameResponseDTO(game.getId(), game.getQuestionCount())
        );
    }
    public ResponseDTO<QuestionDTO> getQuestionFromGame(Long gameId, int questionNumber) {
        questionNumber--;
        Game game = gameRepository.findById(gameId).orElse(null);
        if (game != null && game.getIsActive()){
            if (game.getQuestionCount() <= questionNumber){
                return new ResponseDTO<>("Error", "There is not question number " + (questionNumber + 1) + " in game " + gameId, null);
            }
            Question question = game.getQuestions().get(questionNumber);
            return new ResponseDTO<>("OK", "OK",
                    new QuestionDTO(
                            question.getId(),
                            question.getQuestionText(),
                            new CategoryDTO(question.getCategory().getId(), question.getCategory().getName()),
                            question.getDifficulty()
                    ));
        }
        return new ResponseDTO<>("Error", " Cannot find active game with id " + gameId, null);
    }
    public ResponseDTO<AnswerDTO> checkAnswerFromGame(Long gameId, int questionNumber, AnswerGameDTO answer) {
        questionNumber--;
        Game game = gameRepository.findById(gameId).orElse(null);
        if (game != null && game.getIsActive()){
            if (answerRepository.findAnswerByGameIdAndQuestionId(gameId, game.getQuestions().get(questionNumber).getId()) != null){
                return new ResponseDTO<>("Error", "You already have answered on this question", null);
            }
            ResponseDTO<AnswerDTO> result = questionService.checkAnswer(
                    new AnswerCreationDTO(
                            game.getQuestions().get(questionNumber).getId(),
                            answer.getAnswer()
                    )
            );
            answerRepository.save(new Answer(gameId, questionNumber, result.getResult().getQuestionId(), result.getResult().getIsCorrect()));
            return result;
        }
        return new ResponseDTO<>("Error", " Cannot find active game with id " + gameId, null);
    }
    public ResponseDTO<FinishGameDTO> finishGame(Long id){
        Game game = gameRepository.findById(id).orElse(null);
        if (game == null){
            return new ResponseDTO<>("Error", "Game with id " + id + " not found", null);
        }
        if (!game.getIsActive()){
            return new ResponseDTO<>("Error", "Game with id " + id + " already has finished", null);
        }
        game.setIsActive(false);
        gameRepository.save(game);
        HashMap<String, String> results = new LinkedHashMap<>();
        for (Question question: game.getQuestions()){
            Answer answer = answerRepository.findAnswerByGameIdAndQuestionId(game.getId(), question.getId());
            boolean ans = (answer != null) ? answer.getIsCorrect() : false;
            String res = (ans) ? "Correct" : "Incorrect";
            results.put(
                    question.getQuestionText(),
                    res
            );
        }
        return new ResponseDTO<>("OK", "OK", new FinishGameDTO(game.getId(), results));
    }
}