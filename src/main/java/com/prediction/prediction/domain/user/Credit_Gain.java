package com.prediction.prediction.domain.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Credit_Gain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private int credit;

    //(0:회원가입, 1:예측 참여, 2:예측 참여)
    private int type;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
