package com.service.implementation;

import com.models.Owner;
import com.models.Role;
import com.models.User;
import com.repositories.IOwnersRepository;
import com.repositories.IUsersRepository;
import com.service.IOwnersService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan("com.repositories")
public class OwnersService implements IOwnersService {
    @Autowired
    private IOwnersRepository ownerRepository;
    @Autowired
    private IUsersRepository usersRepository;
    @Override
    public Owner findByIdOwner(Integer id) {
        return ownerRepository.getById(id);
    }

    @Override
    public void saveOwner(Owner owner) {
        String generatedPassword = RandomString.make(10);
        ownerRepository.save(owner);
        usersRepository.save(User.builder()
                .name(owner.getOwnerName())
                .ownerId(owner.getOwnerId())
                .role(Role.valueOf("USER"))
                .userPassword("{noop}" + generatedPassword)
                .build());
    }

    @Override
    public void deleteOwner(Owner owner) {
        ownerRepository.delete(owner);
    }

    @Override
    public void deleteOwners() {
        ownerRepository.deleteAll();
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public void deleteOwnerById(Integer id) {
        ownerRepository.deleteById(id);
    }
}