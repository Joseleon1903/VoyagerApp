package com.discovery.voyager.aplication.service.implementation;

import com.discovery.voyager.aplication.model.entity.User;
import com.discovery.voyager.aplication.repository.RoleRepository;
import com.discovery.voyager.aplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    public Iterable<User> findAllUsers() {
        return  userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public User createUser(User user) {
        return saveOnSystem(user);
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

    public User saveOnSystem(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       // user.setRoles(new HashSet<>(roleRepository.findAll()));
        return userRepository.save(user);
    }

}
