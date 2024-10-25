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

    private int status;
    private Date date;
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

