package com.prediction.prediction.domain.community;

import com.prediction.prediction.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Community_Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String body;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

}
