package com.prediction.prediction.mail;

import com.prediction.prediction.dto.response.MessageDto;
import com.prediction.prediction.exception.BadRequestAlertException;
import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.service.user.UserService;
import com.prediction.prediction.util.CodeUtil;
import com.prediction.prediction.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;

    private final RedisUtil redisUtil;

    private final UserService userService;

    @Override
    @Async
    public String sendMailReject(String email) {
        try {
            if (!userService.checkEmail(email)) {
                throw new BadRequestAlertException("이미 사용중인 이메일");
            }
            String code = CodeUtil.generate();
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("이메일 인증 - Prediction");
            simpleMailMessage.setText("인증번호 : " + code + ".");

            javaMailSender.send(simpleMailMessage);

            redisUtil.saveToken(email, code, 3);
            System.out.println(code);
            return code;
        }catch (BadRequestAlertException e){
            throw new BadRequestAlertException(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(e);
        }
    }
}
