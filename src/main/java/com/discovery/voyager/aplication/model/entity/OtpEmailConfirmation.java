package com.discovery.voyager.aplication.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jose on 8/4/2019.
 */
@Data
@Entity
@Table(name="otp_email_confirmation_Tab")
public class OtpEmailConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean otpEmailSend;
    private String otpSending;
    private boolean otpValidated;
    private Date otpSendingDate;

    public OtpEmailConfirmation(long id){
        this.id = id;
    }

    public OtpEmailConfirmation(){}



}
