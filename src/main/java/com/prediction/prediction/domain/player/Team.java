package com.prediction.prediction.domain.player;

import com.prediction.prediction.domain.game.Game;
import com.prediction.prediction.domain.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    private String name;

    @Column(name = "win_count")
    private int win_count;

    @Column(name = "draw_count")
    private int draw_count;

    @Column(name = "lose_count")
    private int lose_count;

//    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Player> players;
//
//    @OneToMany(mappedBy = "home")
//    private List<Game> home;
//
//    @OneToMany(mappedBy = "away")
//    private List<Game> away;
//
//    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<User> users;
}
