package com.discovery.voyager.aplication.mapping;

import com.discovery.voyager.aplication.model.dto.form.ProfileFormData;
import com.discovery.voyager.aplication.model.entity.User;

public class ProfileMapping{

    private ProfileMapping(){}

    public static ProfileFormData convertFromEntity(User user){
        ProfileFormData entityout = new ProfileFormData();
        entityout.setUsername(user.getUsername());
        entityout.setFirstName(user.getProfile().getFirstName());
        entityout.setLastName(user.getProfile().getLastName());
        if(user.getProfile().getImage() != null){
            entityout.setProfileImageUrl(user.getProfile().getImage().getFileViewUri());
        }
        entityout.setEmail(user.getProfile().getEmail());
        return entityout;
    }

}