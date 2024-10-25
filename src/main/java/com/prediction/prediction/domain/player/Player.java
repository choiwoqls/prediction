package com.prediction.prediction.domain.player;

import com.prediction.prediction.domain.user.Lineup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private int position;
    private String hand;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lineup> lineups;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
    private Player_Record record;
}
