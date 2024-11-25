package com.prediction.prediction.service.service.game;

import com.prediction.prediction.domain.user.User;

import java.util.List;

public interface Game_PlayService {
    void updateExpect(int result, Long game_id);
    List<User> successList(Long game_id);
    List<User> userList(Long game_id);
    void deleteExpect(Long game_id);
}
