package com.prediction.prediction.service.service.user;

import com.prediction.prediction.domain.user.User;

import java.util.List;

public interface MessageService {
    boolean sendMessage(List<User> user_list, int type, String title);
}
