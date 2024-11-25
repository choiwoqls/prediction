package com.prediction.prediction.service.serviceImpl.player;

import com.prediction.prediction.domain.game.Game;
import com.prediction.prediction.domain.player.Team;
import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.exception.NotFoundException;
import com.prediction.prediction.repository.player.TeamRepository;
import com.prediction.prediction.service.service.player.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public Team getTeamById(int team_id) {
        try {
            return teamRepository.findById(team_id).orElseThrow(()->new NotFoundException("Not Found Team Info"));
        }catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Override
    public boolean checkTeam(int team_id) {
        try {
            return teamRepository.existsById(team_id);
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Transactional
    @Override
    public void updateResult(int result, Game game) {
        try {
            switch (result){
                case 1 :
                    teamRepository.winTeam(game.getHome().getId());
                    teamRepository.loseTeam(game.getAway().getId());
                    break;
                case 2 :
                    teamRepository.drawTeam(game.getHome().getId());
                    teamRepository.drawTeam(game.getAway().getId());
                    break;
                case 3 :
                    teamRepository.loseTeam(game.getHome().getId());
                    teamRepository.winTeam(game.getAway().getId());
                    break;
                default:
                    break;
            }
        }catch (Exception e){
            throw new CustomException(e);
        }
    }
}
