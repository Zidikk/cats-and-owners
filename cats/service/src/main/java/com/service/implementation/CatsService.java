package com.service.implementation;

import com.models.Cat;
import com.models.Friend;
import com.models.Owner;
import com.repositories.ICatsRepository;
import com.repositories.IFriendsRepository;
import com.repositories.IOwnersRepository;
import com.service.ICatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@ComponentScan("com.repositories")
public class CatsService implements ICatsService {

    @Autowired
    private IFriendsRepository friendsRepository;
    @Autowired
    private IOwnersRepository ownerRepository;
    @Autowired
    private ICatsRepository catRepository;

    @Override
    public void saveCat(Cat cat) {
        catRepository.save(cat);
    }

    @Override
    public void deleteCat(Cat cat) {
        catRepository.delete(cat);
    }

    @Override
    public void deleteCatById(Integer catId) {
        catRepository.deleteById(catId);
    }

    @Override
    public void deleteCats() {
        catRepository.deleteAll();
    }

    @Override
    public Cat findByIdCat(Integer id) {
        return catRepository.getById(id);
    }

    @Override
    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }

    @Override
    public List<Cat> getOwnerCats(Integer id) {
        Owner owners = findByIdOwner(id);
        List<Cat> cats = catRepository.findAll();
        List<Cat> ownerCats = new ArrayList<>(cats.size());
        for (Cat cat : cats) {
            if (Objects.equals(cat.getOwnerId(), owners.getOwnerId())) {
                ownerCats.add(cat);
            }
        }
        return ownerCats;
    }

    @Override
    public List<Cat> getFriendsCat(Integer id) {
        List<Friend> allPairs = friendsRepository.findAll();
        List<Cat> friends = new ArrayList<>(allPairs.size());
        for (Friend allPair : allPairs) {
            if (Objects.equals(allPair.getFriendFirst(), id)) {
                friends.add(findByIdCat(allPair.getFriendSecond()));
            }
            if (Objects.equals(allPair.getFriendSecond(), id)) {
                friends.add(findByIdCat(allPair.getFriendFirst()));
            }
        }
        return friends;
    }

    private Owner findByIdOwner(Integer id) {
        return ownerRepository.getById(id);
    }
}
