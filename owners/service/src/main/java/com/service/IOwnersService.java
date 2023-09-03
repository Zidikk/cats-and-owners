package com.service;

import com.models.Owner;

import java.util.List;


public interface IOwnersService {
    void saveOwner(Owner owner);
    void deleteOwner(Owner owner);
    void deleteOwnerById(Integer id);
    void deleteOwners();
    Owner findByIdOwner(Integer id);
    List<Owner> getAllOwners();
}