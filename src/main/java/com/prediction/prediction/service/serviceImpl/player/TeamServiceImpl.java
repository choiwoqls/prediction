package com.prediction.prediction.service.serviceImpl.player;

import com.prediction.prediction.domain.player.Team;
import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.exception.NotFoundException;
import com.prediction.prediction.repository.player.TeamRepository;
import com.prediction.prediction.service.player.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
