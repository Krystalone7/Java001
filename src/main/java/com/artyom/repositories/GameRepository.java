package com.artyom.repositories;

import com.artyom.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface GameRepository extends JpaRepository<Game, Long> {
    @Transactional
    void deleteById(Long id);
}
