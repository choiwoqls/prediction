package com.prediction.prediction.repository.game;

import com.prediction.prediction.domain.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}
