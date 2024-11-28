package com.prediction.prediction.repository.game;

import com.prediction.prediction.domain.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Modifying
    @Query(value = "UPDATE Game SET result = :result , status = :status WHERE id = :game", nativeQuery = true)
    int resultGame(int result,int status, Long game);



}
