package com.prediction.prediction.service.service.user;

import com.prediction.prediction.domain.user.Credit_Gain;
import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.response.MessageDto;
import jakarta.servlet.http.HttpServletRequest;

public interface Credit_GainService {

    void presentCredit(User user, int type, int credit);

    Credit_Gain info(Long id);

}
