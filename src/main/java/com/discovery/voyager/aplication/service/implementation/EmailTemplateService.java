package com.discovery.voyager.aplication.service.implementation;

import com.discovery.voyager.aplication.model.entity.EmailTemplate;
import com.discovery.voyager.aplication.repository.EmailTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by jleon on 6/22/2018.
 */
@Service
public class EmailTemplateService {

    @Autowired
    private EmailTemplateRepository emailTemplateRepository;

	public EmailTemplate findByCode(String code) {
		return emailTemplateRepository.findByCode(code);
	}

	public List<EmailTemplate> findAll() {
		return emailTemplateRepository.findAll();
	}

	public EmailTemplate save(EmailTemplate emailTemplate) {
		return emailTemplateRepository.saveAndFlush(emailTemplate);
    }

}
