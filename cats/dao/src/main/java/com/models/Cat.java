package com.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Cats")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id", nullable = false)
    private Integer catId;
    @Column(name = "owner_id", nullable = false)
    private Integer ownerId;
    @Column(name = "cat_dob", nullable = false)
    private LocalDate catDoB;
    @Column(name = "cat_colour", nullable = false)
    private String catColour;
    @Column(name = "cat_name", nullable = false)
    private String catName;
}