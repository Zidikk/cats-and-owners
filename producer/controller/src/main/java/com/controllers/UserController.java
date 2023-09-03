package com.controllers;

import com.dto.UserDTO;
import com.mappers.IUserMapper;
import com.models.User;
import com.service.implementation.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan(basePackages = {"com.service", "com.mappers"})
public class UserController {

    @Autowired
    private UsersService userService;
    @Autowired
    private IUserMapper userMapper;
    private final String message = "Successfully";

    @GetMapping("/users/{usersId}")
    @PreAuthorize("hasAuthority('access:write')")
    public UserDTO showUser(@PathVariable("usersId") Integer usersId) {
        User user = userService.findByIdUser(usersId);
        return userMapper.toDTO(user);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('access:write')")
    public List<UserDTO> showUsers() {
        List<User> users = userService.getAllUsers();
        return userMapper.toDTOs(users);
    }
}