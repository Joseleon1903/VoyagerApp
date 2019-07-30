package com.discovery.voyager.aplication.service.implementation;

import com.discovery.voyager.aplication.model.entity.User;
import com.discovery.voyager.aplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAllUsers() {
        return  userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
       if (userRepository.existsById(user.getId())){
          return userRepository.save(user);
       }
       return null;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
