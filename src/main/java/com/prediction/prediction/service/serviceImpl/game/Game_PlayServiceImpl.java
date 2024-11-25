package com.prediction.prediction.service.serviceImpl.game;

import com.prediction.prediction.domain.game.Game;
import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.repository.game.Game_PlayRepository;
import com.prediction.prediction.service.service.game.Game_PlayService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class Game_PlayServiceImpl implements Game_PlayService {

    @Autowired
    private final Game_PlayRepository gamePlayRepository;

    @Transactional
    @Override
    public void updateExpect(int result, Long game_id) {
        try{
            int suc = gamePlayRepository.successExpect(result, game_id);
            int fal = gamePlayRepository.failedExpect(result, game_id);
            System.out.println("Success count : " + suc + "Failed count : " + fal);
        }catch (Exception e){
            System.out.println("Game_PlayServiceImpl : updateExpect");
            throw new CustomException(e);
        }
    }

    @Transactional
    @Override
    public List<User> successList(Long game_id) {
        try {
            Game game = new Game();
            game.setId(game_id);
            return gamePlayRepository.successList(game);
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Transactional
    @Override
    public List<User> userList(Long game_id) {
        try {
            Game game = new Game();
            game.setId(game_id);
            return gamePlayRepository.userList(game);
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Transactional
    @Override
    public void deleteExpect(Long game_id) {
        try {
            gamePlayRepository.deleteByGameId(game_id);
        }catch (Exception e){
            throw new CustomException(e);
        }
    }
}
