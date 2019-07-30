package com.discovery.voyager.aplication.repository;

import com.discovery.voyager.aplication.model.entity.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jleon on 6/11/2018.
 */
@org.springframework.stereotype.Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

}
