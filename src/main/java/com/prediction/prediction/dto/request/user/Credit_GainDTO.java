package com.prediction.prediction.dto.request.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prediction.prediction.domain.user.Credit_Gain;
import com.prediction.prediction.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credit_GainDTO {
    private Long id;
    private int credit;
    private int type;

    @JsonIgnore
    private User user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date date;

    public Credit_GainDTO(Credit_Gain creditGain){
        this.id = creditGain.getId();
        this.credit = creditGain.getCredit();
        this.type = creditGain.getType();
        this.date = creditGain.getDate();
        this.user = creditGain.getUser();
    }

}
