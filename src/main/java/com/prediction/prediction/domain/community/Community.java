package com.prediction.prediction.domain.community;

import com.prediction.prediction.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "lineup_check")
    private int lineup_check;

    private int type;
    private String title;
    private String body;
    private Date date;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Community_Comment> comments;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Community_Like> likes;

}
