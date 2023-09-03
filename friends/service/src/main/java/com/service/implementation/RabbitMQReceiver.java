package com.service.implementation;

import com.dto.FriendDTO;
import com.mappers.IFriendMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@ComponentScan("com.mappers")
public class RabbitMQReceiver implements RabbitListenerConfigurer {

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }

    @Autowired
    private FriendsService friendsService;
    @Autowired
    private IFriendMapper friendMapper;

    @RabbitListener(queues = "friend.queue.add")
    @Transactional
    public void saveFriends(FriendDTO friendDTO) {
        friendsService.addPairFriends(friendMapper.unDTO(friendDTO));
    }

    @RabbitListener(queues = "friend.queue.delete")
    @Transactional
    public void deleteFriend(FriendDTO friendDTO) {
        friendsService.deletePairFriends(friendMapper.unDTO(friendDTO));
    }

    @RabbitListener(queues = "friend.queue.deleteById")
    @Transactional
    public void deleteFriendsById(Integer id) {
        friendsService.deletePairFriendsById(id);
    }

    @RabbitListener(queues = "friend.queue.deleteAll")
    @Transactional
    public void deleteFriends() {
        friendsService.deleteAllPairFriends();
    }

    @RabbitListener(queues = "friend.queue.getById")
    @Transactional
    public FriendDTO getByIdFriend(Integer id) {
        return friendMapper.toDTO(friendsService.findByIdFriends(id));
    }

    @RabbitListener(queues = "friend.queue.getAll")
    @Transactional
    public List<FriendDTO> getFriends() {
        return friendMapper.toDTOs(friendsService.getAllFriends());
    }
}