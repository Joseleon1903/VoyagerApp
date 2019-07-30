package com.discovery.voyager.aplication.model.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 6972588677114510106L;
    
	private long id;
    private String username;
    private String password;

    public UserDTO() {

    }

    public UserDTO(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


}
