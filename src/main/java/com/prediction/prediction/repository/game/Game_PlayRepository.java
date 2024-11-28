package com.prediction.prediction.repository.game;

import com.prediction.prediction.domain.game.Game;
import com.prediction.prediction.domain.game.Game_Play;
import com.prediction.prediction.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Game_PlayRepository extends JpaRepository<Game_Play, Long> {

    @Modifying
    @Query(value = "UPDATE Game_Play SET result = 1 WHERE expect = :result AND game_id = :game_id", nativeQuery = true)
    int successExpect(int result, Long game_id);

    @Modifying
    @Query(value = "UPDATE Game_Play SET result = 2 WHERE expect != :result AND game_id = :game_id", nativeQuery = true)
    int failedExpect(int result, Long game_id);

    @Query(value = "SELECT g.user FROM Game_Play g WHERE g.result = 1 and g.game.id = :game_id")
    List<User> successList(Long game_id);

    @Query(value = "SELECT g.user FROM Game_Play g  WHERE g.user.result_op = 0 AND g.game.id = :game_id")
    List<User> userList(Long game_id);

    @Modifying
    @Query(value = "DELETE FROM Game_Play WHERE game.id = :game_id")
    void deleteByGameId(Long game_id);


}
