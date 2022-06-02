package com.artyom.controllers;

import com.artyom.dto.*;
import com.artyom.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/game")
    public ResponseDTO<GameResponseDTO> createGame(@RequestBody GameCreationDTO gameCreationDTO){
         return gameService.createNewGame(gameCreationDTO);
    }

    @GetMapping("/game/{gameId}/{questionNumber}")
    public ResponseDTO<QuestionDTO> getQuestionFromGame(@PathVariable Long gameId, @PathVariable int questionNumber){
        return gameService.getQuestionFromGame(gameId, questionNumber);
    }

    @PostMapping("/game/{gameId}/{questionNumber}/check")
    public ResponseDTO<AnswerDTO> checkAnswerFromGame(@RequestBody AnswerGameDTO answer, @PathVariable Long gameId, @PathVariable int questionNumber){
        return gameService.checkAnswerFromGame(gameId, questionNumber, answer);
    }

    @PostMapping("/game/{gameId}/finish")
    public ResponseDTO<FinishGameDTO> finishGame(@PathVariable Long gameId){
        return gameService.finishGame(gameId);
    }
}
