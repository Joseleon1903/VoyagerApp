package com.discovery.voyager.aplication.repository;

import java.util.List;
import com.discovery.voyager.aplication.model.entity.ErrorException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ErrorExceptionRepository extends CrudRepository<ErrorException, Long>{

    @Query("select e from ErrorException e where e.status = true")
    List<ErrorException> findAllActive();

    @Query("select e from ErrorException e where e.code like ?1")
    ErrorException findByCode(long code);

}