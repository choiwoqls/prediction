package com.prediction.prediction.service.service.user;

import com.prediction.prediction.domain.user.User;
import com.prediction.prediction.dto.response.user.Credit_GainDTO;

import java.util.List;

public interface Credit_GainService {

    void presentCredit(User user, int type, int credit);

    List<Credit_GainDTO> info(Long id);

}
