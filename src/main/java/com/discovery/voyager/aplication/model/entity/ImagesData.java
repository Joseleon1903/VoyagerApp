package com.discovery.voyager.aplication.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Images_Tab")
public class ImagesData implements Serializable{

    private static final long serialVersionUID = 8967144121761988622L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String fileDownloadUri;
    private String fileViewUri;
    private String fileType;
    private long size;
    private Date creationDate;
    private Date updateDate;

    public ImagesData(String name, String fileDownloadUri, String fileViewUri, String fileType, long size) {
        this.name = name;
        this.fileDownloadUri = fileDownloadUri;
        this.fileViewUri = fileViewUri;
        this.fileType = fileType;
        this.size = size;
    }

    public ImagesData(String name){
        this.name = name;
    }

    public ImagesData(){}

}