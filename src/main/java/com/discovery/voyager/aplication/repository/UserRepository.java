package com.discovery.voyager.aplication.repository;

import com.discovery.voyager.aplication.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

   User findByUsernameAndStatus(String username, String status);

   User findByUsername(String username);

}
