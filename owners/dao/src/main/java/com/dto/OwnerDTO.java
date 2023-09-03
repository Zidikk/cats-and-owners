package com.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO implements Serializable {

    private Integer ownerId;
    private Integer userId;
    private LocalDate ownerDoB;
    private String ownerName;
}
