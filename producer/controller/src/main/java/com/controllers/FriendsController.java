package com.controllers;

import com.dto.FriendDTO;
import com.service.implementation.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan("com.service")
public class FriendsController {

    @Autowired
    private RabbitMQSender rabbitMQSender;
    private final String message = "Message has been sent Successfully..";

    @GetMapping("/friends/{friendsId}")
    @PreAuthorize("hasAuthority('access:read')")
    public FriendDTO showFriend(@PathVariable("friendsId") Integer friendsId) {
        return rabbitMQSender.getFriendsById(friendsId);
    }

    @GetMapping("/friends")
    @PreAuthorize("hasAuthority('access:read')")
    public List<FriendDTO> showFriends() {
        return rabbitMQSender.getFriends(message);
    }

    @PostMapping("/friends/add")
    @PreAuthorize("hasAuthority('access:write')")
    public String addPairFriends(@RequestBody FriendDTO friendDTO) {
        rabbitMQSender.addFriends(friendDTO);
        return message;
    }

    @DeleteMapping("/friends/delete")
    @PreAuthorize("hasAuthority('access:write')")
    public String deleteFriends(@RequestBody FriendDTO friendDTO) {
        rabbitMQSender.deleteFriends(friendDTO);
        return message;
    }

    @DeleteMapping("/friends/delete/{friendsId}")
    @PreAuthorize("hasAuthority('access:write')")
    public String deleteFriendsById(@PathVariable("friendsId") Integer friendsId) {
        rabbitMQSender.getFriendsById(friendsId);
        return message;
    }

    @DeleteMapping("/friends/delete/friends")
    @PreAuthorize("hasAuthority('access:write')")
    public String deleteAllPairFriends() {
        rabbitMQSender.getFriends(message);
        return message;
    }
}
