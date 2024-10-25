package com.prediction.prediction.domain.player;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Player_Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "player_role")
    private int player_role;

    private float avg;

    @Column(name = "hr_win")
    private int hr_win;

    @Column(name = "obp_lose")
    private float obp_lose;

    @Column(name = "slg_hold")
    private float slg_hold;

    @Column(name = "ops_save")
    private float ops_save;

    private float war;

    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;
}
