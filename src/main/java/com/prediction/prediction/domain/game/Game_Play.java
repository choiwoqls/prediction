package com.prediction.prediction.domain.game;

import com.prediction.prediction.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@Getter
@NoArgsConstructor
public class Game_Play {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private int expect;
    private int result;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

}
