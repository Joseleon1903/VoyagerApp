package com.discovery.voyager.aplication.model.dto.form;

import lombok.Data;

@Data
public class UsernameChecked {

    private String username;
    private boolean isCheck;

    public UsernameChecked(){}

    public UsernameChecked(String username, boolean isCheck) {
        this.username = username;
        this.isCheck = isCheck;
    }
}
