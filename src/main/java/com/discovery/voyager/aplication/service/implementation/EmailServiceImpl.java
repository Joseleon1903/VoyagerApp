package com.discovery.voyager.aplication.service.implementation;

import com.discovery.voyager.aplication.config.ApplicationProperty;
import com.discovery.voyager.aplication.controller.profile.RegistrationController;
import com.discovery.voyager.aplication.service.interfaces.EmailService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    private static Logger logger = LogManager.getLogger(RegistrationController.class);

    @Autowired
    private ApplicationProperty applicationProperty;

    @Autowired
   private JavaMailSender emailSender;

    @Autowired
    private Configuration freemarkerConfiguration;

    @Async
    public void sendEmail(final String destinationEmail, final String content, final String subject, final boolean isHtml) {
        logger.info("sending email ");
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
                helper.setFrom(applicationProperty.getFromEmail());
                helper.setSubject(subject);
                helper.setTo(destinationEmail);
                // use the true flag to indicate you need a multipart message
                helper.setText(content, isHtml);
            }
        };
        emailSender.send(preparator);
        logger.info("email sent");
    }

    @Override
    public void sentOtpValidationEmail(String otp, String destinationEmail) throws IOException, TemplateException{
        Map model = new HashMap();
        model.put("emailCode", otp);
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(
                freemarkerConfiguration.getTemplate("email/fm_email_verification_code.ftl"), model);
        sendEmail(destinationEmail,content, "validation Otp", true);
    }

}