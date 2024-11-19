package com.prediction.prediction.service.serviceImpl.game;

import com.prediction.prediction.domain.game.Game;
import com.prediction.prediction.dto.request.game.GameDTO;
import com.prediction.prediction.exception.BadRequestAlertException;
import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.exception.NotFoundException;
import com.prediction.prediction.exception.ResourceNotFoundException;
import com.prediction.prediction.repository.game.GameRepository;
import com.prediction.prediction.repository.player.TeamRepository;
import com.prediction.prediction.service.service.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    @Autowired
    private final GameRepository gameRepository;

    @Autowired
    private final TeamRepository teamRepository;

    @Override
    public GameDTO createGame(GameDTO gameDto) {
        try{
            if(!teamRepository.existsById(gameDto.getHome_id())){
                throw new NotFoundException("잘못된 Home_id 입력");
            }
            if(!teamRepository.existsById(gameDto.getAway_id())){
                throw new NotFoundException("잘못된 Away_id 입력");
            }
            if(gameDto.getHome_id().equals(gameDto.getAway_id())){
                throw new BadRequestAlertException("잘못된 팀 선택.");
            }
            gameDto.setStatus(0);
            gameDto.setResult(0);


        }catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }catch (BadRequestAlertException e){
            throw new BadRequestAlertException(e.getMessage());
        }catch (Exception e){
            throw new CustomException(e);
        }
        return null;
    }

}
