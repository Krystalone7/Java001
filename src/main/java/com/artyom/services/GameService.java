package com.artyom.services;

import com.artyom.dto.GameCreationDTO;
import com.artyom.dto.GameResponseDTO;
import com.artyom.entities.Game;
import com.artyom.repositories.GameRepository;
import com.artyom.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public GameService(GameRepository gameRepository, QuestionRepository questionRepository) {
        this.gameRepository = gameRepository;
        this.questionRepository = questionRepository;
    }

    public GameResponseDTO createNewGame(GameCreationDTO gameCreationDTO){
        Game game = gameRepository.save(new Game(
                gameCreationDTO.getQuestionCount(),
                gameCreationDTO.getMinValue(),
                gameCreationDTO.getMaxValue()));
        for (int i = 0; i < game.getQuestionCount(); i++){
            game.getQuestions().add(questionRepository.getQuestionByDifficultyIsBetween(game.getMinValue(),game.getMaxValue()));
        }
        return new GameResponseDTO(game.getId(), game.getQuestionCount());

    }
}

