package com.prediction.prediction.domain.game;

import com.prediction.prediction.domain.player.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //(0:예측 예정, 1:진행 중, 2:예측 마감, 3:결과)
    private int status;
    private Date date;

    //(0:경기 전, 1:승, 2:무, 3:패, 4:취소)(홈 팀 기준)
    private int result;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Game_Play> plays;

    @ManyToOne
    @JoinColumn(name = "home_id")
    private Team home;

    @ManyToOne
    @JoinColumn(name = "away_id")
    private Team away;




}

