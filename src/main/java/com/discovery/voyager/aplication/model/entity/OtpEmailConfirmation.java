package com.discovery.voyager.aplication.model.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jose on 8/4/2019.
 */
@Data
@Entity
@Table(name="otp_email_confirmation_Tab")
public class OtpEmailConfirmation {

    @Id
    private long id;
    private String otpSending;
    private boolean otpValidated;

    public OtpEmailConfirmation(long id){
        this.id = id;
    }

    public OtpEmailConfirmation(){}



}
