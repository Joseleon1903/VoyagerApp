package com.discovery.voyager.aplication.model.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class EpicItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private StatusItem status;
    private String Description;
    private int priority;
    private Date creationDate;
    private Date closeDate;
//    @ManyToOne
//    private List<CorrelationLink> links;

//    @ManyToOne
//    private List<CustomTag> tags;




}
