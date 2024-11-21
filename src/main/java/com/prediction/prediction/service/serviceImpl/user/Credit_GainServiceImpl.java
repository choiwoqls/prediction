package com.prediction.prediction.service.serviceImpl.user;

import com.prediction.prediction.domain.user.Credit_Gain;
import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.response.MessageDto;
import com.prediction.prediction.exception.CustomException;
import com.prediction.prediction.exception.NotFoundException;
import com.prediction.prediction.exception.UnauthorizedException;
import com.prediction.prediction.repository.user.Credit_GainRepository;
import com.prediction.prediction.security.JwtTokenProvider;
import com.prediction.prediction.service.service.user.Credit_GainService;
import com.prediction.prediction.service.service.user.UserService;
import com.prediction.prediction.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class Credit_GainServiceImpl implements Credit_GainService {

    @Autowired
    private final Credit_GainRepository credit_gainRepository;

    @Override
    public void presentCredit(User user, int type, int credit) {
        try {
            Credit_Gain creditGain = new Credit_Gain();
            creditGain.setUser(user);
            creditGain.setType(type);
            creditGain.setCredit(credit);
            creditGain.setDate(new Date());
            credit_gainRepository.save(creditGain);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("유효하지않은 정보 : Credit_gain");
        }catch (Exception e){
            throw new CustomException(e);
        }
    }

    @Override
    public Credit_Gain info(Long id) {
        try{
            return credit_gainRepository.findAllUserAndTeam(id);
        }catch (UnauthorizedException e){
            throw new UnauthorizedException(e.getMessage());
        }catch (Exception e){
            throw new CustomException(e);
        }
    }
}
