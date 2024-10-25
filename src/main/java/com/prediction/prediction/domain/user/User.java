package com.prediction.prediction.domain.user;


import com.prediction.prediction.domain.community.Community;
import com.prediction.prediction.domain.community.Community_Comment;
import com.prediction.prediction.domain.community.Community_Like;
import com.prediction.prediction.domain.game.Game_Play;
import com.prediction.prediction.domain.player.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Community> communities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Game_Play> game_plays;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Community_Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Community_Like> likes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Credit_Gain> gains;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lineup> lineups;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
