package com.discovery.voyager.aplication.model.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -3571211166033652642L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    @OneToOne(cascade = {CascadeType.ALL})
    private Profile profile;
    private String status;
    private Date creationDate;
    private Date lastLoginDate;

    @OneToOne(cascade = {CascadeType.ALL})
    private OtpEmailConfirmation otpEmailConfirmation;

    @ManyToMany(mappedBy = "userManagements")
    private Set<Project> projectsMg;

    @ManyToMany(mappedBy = "members")
    private Set<Project> projectsMembers;

    @OneToOne(cascade = {CascadeType.ALL})
    private Role role;


    public User(long id) {
        this.id = id;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {}
    

}
