package com.pccofvns.ts.service;

import com.pccofvns.ts.mapper.UserMapper;
import com.pccofvns.ts.model.User;
import com.pccofvns.ts.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserManagementFacade {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findById(Long id) {
        Optional<com.pccofvns.ts.domain.tables.pojos.User> user = userService.findById(id);
        if(user.isPresent()){
            User userDto = userMapper.sourceToDestination(user.get());
            return Optional.of(userDto);
        }
        return Optional.empty();
    }

    public User save(User user) {
        user.password(passwordEncoder.encode(user.getPassword()));
        com.pccofvns.ts.domain.tables.pojos.User userEntity = userMapper.destinationToSource(user);
        com.pccofvns.ts.domain.tables.pojos.User savedUser = userService.save(userEntity);
        return userMapper.sourceToDestination(savedUser);
    }
}
