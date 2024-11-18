package com.prediction.prediction.mail;

import com.prediction.prediction.dto.response.MessageDto;

public interface EmailService {

    MessageDto sendMailAuth(String Email, boolean mode);




}
