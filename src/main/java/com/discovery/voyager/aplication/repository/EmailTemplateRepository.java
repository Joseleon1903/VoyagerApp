package com.discovery.voyager.aplication.repository;

import com.discovery.voyager.aplication.model.entity.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface EmailTemplateRepository extends JpaRepository< EmailTemplate, Long>, Repository<EmailTemplate, Long>{

    @Query("select e from EmailTemplate e where e.code like ?1")
    EmailTemplate findByCode(String code);

}