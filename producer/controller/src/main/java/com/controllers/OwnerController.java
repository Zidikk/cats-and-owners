package com.controllers;

import com.dto.OwnerDTO;
import com.service.implementation.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@ComponentScan("com.service")
@ComponentScan("com.models")
public class OwnerController {

    @Autowired
    private RabbitMQSender rabbitMQSender;
    private final String message = "Message has been sent Successfully..";

    @GetMapping("/owners/{ownerId}")
    @PreAuthorize("hasAuthority('access:read')")
    public OwnerDTO showOwner(@PathVariable("ownerId") Integer ownerId) {
        return rabbitMQSender.getOwnerById(ownerId);
    }

    @GetMapping("/owners")
    @PreAuthorize("hasAuthority('access:read')")
    public List<OwnerDTO> showOwners() {
        return rabbitMQSender.getOwners(message);
    }

    @PostMapping("/owners/add")
    @PreAuthorize("hasAuthority('access:write')")
    public String addOwner(@RequestBody OwnerDTO ownerDTO) {
        rabbitMQSender.addOwner(ownerDTO);
        return message;
    }

    @DeleteMapping("/owners/delete")
    @PreAuthorize("hasAuthority('access:write')")
    public String deleteOwner(@RequestBody OwnerDTO ownerDTO) {
        rabbitMQSender.deleteOwner(ownerDTO);
        return message;
    }

    @DeleteMapping("/owners/delete/{ownerId}")
    @PreAuthorize("hasAuthority('access:write')")
    public String deleteOwnerById(@PathVariable("ownerId") Integer ownerId) {
        rabbitMQSender.deleteOwnerById(ownerId);
        return message;
    }

    @DeleteMapping("/owners/delete/owners")
    @PreAuthorize("hasAuthority('access:write')")
    public String deleteOwners() {
        rabbitMQSender.deleteOwners(message);
        return message;
    }
}