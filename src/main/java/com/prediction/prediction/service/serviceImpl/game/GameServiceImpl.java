package com.prediction.prediction.service.serviceImpl.game;

import com.prediction.prediction.domain.game.Game;
import com.prediction.prediction.domain.player.Team;
import com.prediction.prediction.dto.request.game.GameDTO;
import com.prediction.prediction.exception.BadRequestAlertException;
import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.exception.NotFoundException;
import com.prediction.prediction.exception.ResourceNotFoundException;
import com.prediction.prediction.repository.game.GameRepository;
import com.prediction.prediction.repository.player.TeamRepository;
import com.prediction.prediction.service.service.game.GameService;
import com.prediction.prediction.service.service.player.TeamService;
import com.prediction.prediction.service.serviceImpl.player.TeamServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    @Autowired
    private final GameRepository gameRepository;

    @Autowired
    private final TeamService teamService;

    @Override
    public GameDTO createGame(GameDTO gameDto) {
        try{
            Integer home_id = gameDto.getHome_id();
            Integer away_id = gameDto.getAway_id();
            if(!teamService.checkTeam(home_id)){
                throw new NotFoundException("잘못된 Home_id 입력");
            }
            if(!teamService.checkTeam(away_id)){
                throw new NotFoundException("잘못된 Away_id 입력");
            }
            if(home_id.equals(away_id)) {
                throw new BadRequestAlertException("잘못된 팀 선택.");
            }
            Game game = new Game();

            game.setHome(teamService.getTeamById(home_id));
            game.setAway(teamService.getTeamById(away_id));

            //todo domain 에서 default 0 설정.
            game.setResult(0);
            game.setStatus(0);
            game.setDate(gameDto.getDate());

            gameDto = new GameDTO(gameRepository.save(game));
            return gameDto;
        }catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }catch (BadRequestAlertException e){
            throw new BadRequestAlertException(e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(e);
        }
    }

}
