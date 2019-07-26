package com.discovery.voyager.aplication.repository;

import com.discovery.voyager.aplication.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jleon on 6/11/2018.
 */
@org.springframework.stereotype.Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
