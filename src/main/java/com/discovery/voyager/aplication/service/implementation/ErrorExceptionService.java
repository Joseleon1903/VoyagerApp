package com.discovery.voyager.aplication.service.implementation;

import java.util.List;
import com.discovery.voyager.aplication.model.entity.ErrorException;
import com.discovery.voyager.aplication.repository.ErrorExceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrorExceptionService  {

    @Autowired
    private ErrorExceptionRepository errorExceptionRepository;

	public ErrorException findByCode(long code) {
		return errorExceptionRepository.findByCode(code);
	}

	public List<ErrorException> findAll() {
		return (List<ErrorException>) errorExceptionRepository.findAll();
	}

	public List<ErrorException> findAllActive() {
		return errorExceptionRepository.findAllActive();
	}

	public ErrorException save(ErrorException errorException) {
		return errorExceptionRepository.save(errorException);
	}

}