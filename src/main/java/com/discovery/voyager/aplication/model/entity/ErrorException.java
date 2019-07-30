package com.discovery.voyager.aplication.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ErrorException{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long code;
    private String description;
    private boolean status;

    public ErrorException(){}


}