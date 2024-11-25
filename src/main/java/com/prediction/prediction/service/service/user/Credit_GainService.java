package com.prediction.prediction.service.service.user;

import com.prediction.prediction.domain.user.Credit_Gain;
import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.request.user.Credit_GainDTO;
import com.prediction.prediction.dto.response.MessageDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

public interface Credit_GainService {

    void presentCredit(User user, int type, int credit);

    List<Credit_GainDTO> info(Long id);

}
