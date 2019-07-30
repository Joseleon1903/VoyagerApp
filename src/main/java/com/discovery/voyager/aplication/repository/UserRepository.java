package com.discovery.voyager.aplication.repository;

import com.discovery.voyager.aplication.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

   @Query("select u from User u where u.username like ?1")
   User findByUsername(String username);

}
