package com.service;

import com.models.Friend;

import java.util.List;

public interface IFriendsService {

    Friend findByIdFriends(Integer id);

    List<Friend> getAllFriends();

    void addPairFriends(Friend friends);

    void deletePairFriends(Friend friends);

    void deletePairFriendsById(Integer friendId);

    void deleteAllPairFriends();
}