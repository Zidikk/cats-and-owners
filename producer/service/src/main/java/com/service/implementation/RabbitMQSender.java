package com.service.implementation;

import com.dto.CatDTO;
import com.dto.FriendDTO;
import com.dto.OwnerDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = {"com.mappers", "com.core"})
public class RabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void addOwner(OwnerDTO ownerDTO) {
        rabbitTemplate.convertAndSend("owner.exchange", "owner.add", ownerDTO);
    }

    public void deleteOwner(OwnerDTO ownerDTO) {
        rabbitTemplate.convertAndSend("owner.exchange", "owner.delete", ownerDTO);
    }

    public void deleteOwnerById(Integer id) {
        rabbitTemplate.convertAndSend("owner.exchange", "owner.deleteById", id);
    }

    public void deleteOwners(String forDelete) {
        rabbitTemplate.convertAndSend("owner.exchange", "owner.deleteAll", forDelete);
    }

    public OwnerDTO getOwnerById(Integer id) {
        return (OwnerDTO) rabbitTemplate.convertSendAndReceive("owner.exchange", "owner.getById", id);
    }

    public List<OwnerDTO> getOwners(String forGet) {
        return (List<OwnerDTO>) rabbitTemplate.convertSendAndReceive("owner.exchange", "owner.getAll", forGet);
    }

    public void addCat(CatDTO catDTO) {
        rabbitTemplate.convertAndSend("cat.exchange", "cat.add", catDTO);
    }

    public void deleteCat(CatDTO catDTO) {
        rabbitTemplate.convertAndSend("cat.exchange", "cat.delete", catDTO);
    }

    public void deleteCatById(Integer id) {
        rabbitTemplate.convertAndSend("cat.exchange", "cat.deleteById", id);
    }

    public void deleteCats(String forDelete) {
        rabbitTemplate.convertAndSend("cat.exchange", "cat.deleteAll", forDelete);
    }

    public CatDTO getCatById(Integer id) {
        return (CatDTO) rabbitTemplate.convertSendAndReceive("cat.exchange", "cat.getById", id);
    }

    public List<CatDTO> getCats(String forGet) {
        return (List<CatDTO>) rabbitTemplate.convertSendAndReceive("cat.exchange", "cat.getAll", forGet);
    }

    public List<CatDTO> getFriendsCat(Integer id) {
        return (List<CatDTO>) rabbitTemplate.convertSendAndReceive("cat.exchange", "cat.getFriendsCat", id);
    }

    public List<CatDTO> getOwnerCats(Integer id) {
        return (List<CatDTO>) rabbitTemplate.convertSendAndReceive("cat.exchange", "cat.getOwnerCats", id);
    }

    public void addFriends(FriendDTO friendDTO) {
        rabbitTemplate.convertAndSend("friend.exchange", "friend.add", friendDTO);
    }

    public void deleteFriends(FriendDTO friendDTO) {
        rabbitTemplate.convertAndSend("friend.exchange", "friend.delete", friendDTO);
    }

    public void deleteFriendsById(Integer id) {
        rabbitTemplate.convertAndSend("friend.exchange", "friend.deleteById", id);
    }

    public void deleteAllFriends(String forDelete) {
        rabbitTemplate.convertAndSend("friend.exchange", "friend.deleteAll", forDelete);
    }

    public FriendDTO getFriendsById(Integer id) {
        return (FriendDTO) rabbitTemplate.convertSendAndReceive("friend.exchange", "friend.getById", id);
    }

    public List<FriendDTO> getFriends(String forGet) {
        return (List<FriendDTO>) rabbitTemplate.convertSendAndReceive("friend.exchange", "friend.getAll", forGet);
    }
}