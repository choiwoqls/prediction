package com.prediction.prediction.mail;

import com.prediction.prediction.dto.response.MessageDto;
import com.prediction.prediction.exception.BadRequestAlertException;
import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.exception.NotFoundException;
import com.prediction.prediction.service.user.UserService;
import com.prediction.prediction.util.CodeUtil;
import com.prediction.prediction.util.MailUtil;
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

    private final MailUtil mailUtil;

    private final UserService userService;

    @Override
    @Async
    public MessageDto sendMailAuth(String email, boolean mode) {
        try {
            //email 정보가 없으면 true
            boolean check = userService.checkEmail(email);
            if(mode){
                if(check){
                    throw new BadRequestAlertException("이미 사용중인 이메일");
                }
            }else{
                if(!check){
                    throw new NotFoundException("가입되지 않은 이메일");
                }
            }
            MessageDto message = new MessageDto();
            String code = CodeUtil.generate();

            mailUtil.sendMailReject(email, code);

            redisUtil.saveToken(email, code, 3);

            message.setType("message");
            message.setData("이메일 발송 성공");
            return message;
        }catch (BadRequestAlertException e){
            throw new BadRequestAlertException(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(e);
        }
    }
}
