package com.prediction.prediction.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
@Getter
@NoArgsConstructor
@Entity
public class Credit_Gain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private int credit;
    private int type;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_count")
    private User user;
}
