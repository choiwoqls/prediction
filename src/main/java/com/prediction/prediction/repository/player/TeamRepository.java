package com.prediction.prediction.repository.player;

import com.prediction.prediction.domain.player.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    @Modifying
    @Query(value = "UPDATE Team SET win_count = win_count+1 WHERE id = :team_id", nativeQuery = true)
    int winTeam (int team_id);

    @Modifying
    @Query(value = "UPDATE Team SET draw_count = draw_count+1 WHERE id = :team_id", nativeQuery = true)
    int drawTeam (int team_id);

    @Modifying
    @Query(value = "UPDATE Team SET lose_count = lose_count+1 WHERE id = :team_id", nativeQuery = true)
    int loseTeam (int team_id);



}
