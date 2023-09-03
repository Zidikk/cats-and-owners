package com.dto;

import com.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer userId;
    private Integer ownerId;
    private String userPassword;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Role role;
}