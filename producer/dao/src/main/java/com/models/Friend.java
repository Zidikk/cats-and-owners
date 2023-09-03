package com.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friends_id", nullable = false)
    private Integer friendsId;
    @Column(name = "friend_first", nullable = false)
    private Integer friendFirst;
    @Column(name = "friend_second", nullable = false)
    private Integer friendSecond;
}
