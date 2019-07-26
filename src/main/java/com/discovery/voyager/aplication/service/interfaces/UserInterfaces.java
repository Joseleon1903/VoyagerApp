package com.discovery.voyager.aplication.service.interfaces;

import com.discovery.voyager.aplication.model.entity.User;

public interface UserInterfaces {

    Iterable<User> findAllUsers();

    User findById(Long id);

    User createUser(User user);

    User updateUser(User user);

    User findByUsername(String username);

    User saveOnSystem(User user);

}
