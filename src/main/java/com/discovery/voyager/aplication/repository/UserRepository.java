package com.discovery.voyager.aplication.repository;

import com.discovery.voyager.aplication.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

   User findByUsernameAndStatus(String username, String status);

   User findByUsername(String username);

   @Query(value ="from User u where u.username like ?1")
   Iterable<User> findListUsernameByParam(String username);

}
