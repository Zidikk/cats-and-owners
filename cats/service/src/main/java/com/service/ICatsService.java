package com.service;


import com.models.Cat;

import java.util.List;

public interface ICatsService {

    void saveCat(Cat cat);

    void deleteCat(Cat cat);

    void deleteCatById(Integer id);

    void deleteCats();

    Cat findByIdCat(Integer id);

    List<Cat> getAllCats();

    List<Cat> getOwnerCats(Integer id);

    List<Cat> getFriendsCat(Integer id);
}