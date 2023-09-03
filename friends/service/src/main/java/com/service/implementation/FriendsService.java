package com.service.implementation;

import com.models.Friend;
import com.repositories.IFriendsRepository;
import com.service.IFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan("com.repositories")
public class FriendsService implements IFriendsService {

    @Autowired
    private IFriendsRepository friendsRepository;

    @Override
    public Friend findByIdFriends(Integer id) {
        return friendsRepository.getById(id);
    }

    @Override
    public List<Friend> getAllFriends() {
        return friendsRepository.findAll();
    }

    @Override
    public void addPairFriends(Friend friends) {
        friendsRepository.save(friends);
    }

    @Override
    public void deletePairFriends(Friend friend) {
        friendsRepository.delete(friend);
    }

    @Override
    public void deletePairFriendsById(Integer friendId) {
        friendsRepository.deleteById(friendId);
    }

    @Override
    public void deleteAllPairFriends() {
        friendsRepository.deleteAll();
    }
}
