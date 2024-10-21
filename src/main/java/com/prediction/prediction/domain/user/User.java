package com.prediction.prediction.domain.user;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "team_id")
    private int team_id;

    private String email;
    private String nickname;
    private String password;
    private int role;
    private int credit;
    private Date date;

    @Column(name = "message_op")
    private int message_op;

    @Column(name = "result_op")
    private int result_op;
}
