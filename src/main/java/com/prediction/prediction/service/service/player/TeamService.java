package com.prediction.prediction.service.service.player;

import com.prediction.prediction.domain.game.Game;
import com.prediction.prediction.domain.player.Team;

public interface TeamService {

    Team getTeamById(int team_id);
    boolean checkTeam(int team_id);
    void updateResult(int result, Game game);

}
