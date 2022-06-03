package com.artyom.services;

import com.artyom.dto.GameCreationDTO;
import com.artyom.dto.GameResponseDTO;
import com.artyom.dto.ResponseDTO;
import com.artyom.entities.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GameServiceTest {
    @Autowired
    private GameService gameService;
    @Test
    public void createGameTest(){
        ResponseDTO<GameResponseDTO> responseDTO = gameService.createNewGame(
                new GameCreationDTO(
                        3, 0, 400
                )
        );
        Assertions.assertNotNull(responseDTO.getResult());
        Assertions.assertNotNull(responseDTO.getResult().getGameId());
        gameService.deleteGameById(responseDTO.getResult().getGameId());
    }
    @Test
    public void getQuestionFromGameTest(){
        ResponseDTO<GameResponseDTO> responseDTO = gameService.createNewGame(
                new GameCreationDTO(
                        3, 0, 400
                )
        );
        Assertions.assertNotNull(gameService.getQuestionFromGame(responseDTO.getResult().getGameId(), 1));
        gameService.deleteGameById(responseDTO.getResult().getGameId());
    }
    @Test
    public void finishGameTest(){
        ResponseDTO<GameResponseDTO> responseDTO = gameService.createNewGame(
                new GameCreationDTO(
                        3, 0, 400
                )
        );
        Long gameId = responseDTO.getResult().getGameId();
        gameService.finishGame(gameId);
        Assertions.assertEquals("Error", gameService.getQuestionFromGame(responseDTO.getResult().getGameId(), 1).getCode());
        gameService.deleteGameById(gameId);
    }
    @Test
    public void deleteGameTest(){
        ResponseDTO<GameResponseDTO> responseDTO = gameService.createNewGame(
                new GameCreationDTO(
                        3, 0, 400
                )
        );
        Long gameId = responseDTO.getResult().getGameId();
        gameService.deleteGameById(gameId);
        Assertions.assertEquals("Error", gameService.getQuestionFromGame(gameId, 1).getCode());
    }
}
