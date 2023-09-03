package com.controllers;

import com.dto.CatDTO;
import com.service.implementation.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan("com.service")
public class CatController {

    @Autowired
    private RabbitMQSender rabbitMQSender;
    private final String message = "Message has been sent Successfully..";

    @GetMapping("/cats/{catId}")
    @PreAuthorize("hasAuthority('access:read')")
    public CatDTO showCat(@PathVariable("catId") Integer catId) {
        return rabbitMQSender.getCatById(catId);
    }

    @GetMapping("/cats")
    @PreAuthorize("hasAuthority('access:read')")
    public List<CatDTO> showCats() {
        return rabbitMQSender.getCats(message);
    }

    @GetMapping("/cats/ownercats/{ownerId}")
    @PreAuthorize("hasAuthority('access:read')")
    public List<CatDTO> showOwnerCats(@PathVariable("ownerId") Integer ownerId) {
        return rabbitMQSender.getOwnerCats(ownerId);
    }

    @GetMapping("/cats/catfriends/{catId}")
    @PreAuthorize("hasAuthority('access:read')")
    public List<CatDTO> showFriendsByCat(@PathVariable("catId") Integer catId) {
        return rabbitMQSender.getFriendsCat(catId);
    }

    @PostMapping("/cats/add")
    @PreAuthorize("hasAuthority('access:write')")
    public String addCat(@RequestBody CatDTO catDTO) {
        rabbitMQSender.addCat(catDTO);
        return message;
    }

    @DeleteMapping("/cats/delete")
    @PreAuthorize("hasAuthority('access:write')")
    public String deleteCat(@RequestBody CatDTO catDTO) {
        rabbitMQSender.deleteCat(catDTO);
        return message;
    }

    @DeleteMapping("/cats/delete/{catId}")
    @PreAuthorize("hasAuthority('access:write')")
    public String deleteCatById(@PathVariable("catId") Integer catId) {
        rabbitMQSender.deleteCatById(catId);
        return message;
    }

    @DeleteMapping("/cats/delete/cats")
    @PreAuthorize("hasAuthority('access:write')")
    public String deleteCats() {
        rabbitMQSender.deleteCats(message);
        return message;
    }
}
