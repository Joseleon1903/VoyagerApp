package com.discovery.voyager.aplication.service.implementation;

import java.util.HashSet;
import com.discovery.voyager.aplication.model.entity.User;
import com.discovery.voyager.aplication.repository.RoleRepository;
import com.discovery.voyager.aplication.repository.UserRepository;
import com.discovery.voyager.aplication.service.interfaces.UserInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserInterfaces {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Iterable<User> findAllUsers() {
        return  userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User createUser(User user) {
        return saveOnSystem(user);
    }

    @Override
    public User updateUser(User user) {
       if (userRepository.existsById(user.getId())){
          return userRepository.save(user);
       }
       return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveOnSystem(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        return userRepository.save(user);
    }

}
