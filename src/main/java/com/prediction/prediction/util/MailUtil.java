package com.prediction.prediction.util;

import com.prediction.prediction.exception.BadRequestAlertException;
import com.prediction.prediction.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailUtil {

    private final JavaMailSender javaMailSender;

    public void sendMailReject(String email, String code){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("이메일 인증 - Prediction");
            simpleMailMessage.setText("인증번호 : " + code);
            javaMailSender.send(simpleMailMessage);
        }catch (BadRequestAlertException e){
            throw new BadRequestAlertException(e.getMessage());
        }catch (Exception e){
            System.out.println("Exception - MailUtil : sendMailReject");
            throw new CustomException(e);
        }

    }
}
