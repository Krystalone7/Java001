package com.artyom.repositories;

import com.artyom.entities.Game;
import com.artyom.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    @Override
    Optional<Game> findById(Long id);
}
