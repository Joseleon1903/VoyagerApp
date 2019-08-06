package com.discovery.voyager.aplication.service.interfaces;

import com.discovery.voyager.aplication.model.entity.User;

public interface UserService {

    Iterable<User> findAllUsers();

    User updateUser(User user);

    User findByUsernameAndStatusActive(String username);

    User findByUsername(String username);

}
