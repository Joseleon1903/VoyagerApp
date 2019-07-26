package com.discovery.voyager.aplication.repository;

import com.discovery.voyager.aplication.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User, Long>, JpaRepository<User, Long> , CrudRepository<User, Long> {

   @Query("select u from User u where u.username like ?1")
   User findByUsername(String username);

}
