package com.discovery.voyager.aplication.service.interfaces;

import freemarker.template.TemplateException;
import java.io.IOException;

public interface EmailService {

    void sentOtpValidationEmail(String otp, String destinationEmail) throws IOException, TemplateException;

}
