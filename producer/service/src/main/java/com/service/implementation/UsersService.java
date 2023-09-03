package com.service.implementation;

import com.models.User;
import com.repositories.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan("com.repositories")
public class UsersService implements UserDetailsService {

    @Autowired
    private IUsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = usersRepository.findByName(userName).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist"));
        return UserSecurity.fromUser(user);
    }

    public User findByIdUser(Integer id) {
        return usersRepository.getById(id);
    }

    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }
}
