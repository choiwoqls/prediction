package com.prediction.prediction.mail;

import com.prediction.prediction.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EMailService {

    private final JavaMailSender javaMailSender;

    private final RedisUtil redisUtil;

    @Async public boolean sendMailReject(String Email) throws Exception{
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(Email);
        simpleMailMessage.setSubject("Prediction-Email AUTH");


        return true;
    }


}
