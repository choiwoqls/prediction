package com.prediction.prediction.service.serviceImpl.user;

import com.prediction.prediction.domain.user.Message;
import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.repository.user.MessageRepository;
import com.prediction.prediction.service.service.user.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    @Autowired
    private final MessageRepository messageRepository;

    @Transactional
    @Override
    public boolean sendMessage(List<User> user_list, int type, String body) {
        try {
            for(User user : user_list){
                Message message = new Message();
                message.setUser(user);
                message.setType(type);
                message.setBody(body);
                messageRepository.save(message);
            }
            return true;
        }catch (Exception e){
            throw new CustomException(e);
        }
    }
}
