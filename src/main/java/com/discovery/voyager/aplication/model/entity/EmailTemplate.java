package com.discovery.voyager.aplication.model.entity;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;

@Data
@Entity
@Table(name="Email_Template")
public class EmailTemplate implements Serializable{

    private static final long serialVersionUID = 2936373077564813096L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code;
    private String header;
    private String content;

    public EmailTemplate(){}


}
