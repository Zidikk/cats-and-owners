package com.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "user_password", nullable = false)
    private String userPassword;
    @Column(name = "user_name", nullable = false)
    private String name;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private Role role;
    @Column(name = "owner_id", nullable = false)
    private Integer ownerId;
}