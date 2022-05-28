package com.artyom.controllers;

import com.artyom.dto.GameCreationDTO;
import com.artyom.dto.GameResponseDTO;
import com.artyom.repositories.GameRepository;
import com.artyom.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/game")
    public GameResponseDTO createGame(@RequestBody GameCreationDTO gameCreationDTO){
        return gameService.createNewGame(gameCreationDTO);
    }
}
