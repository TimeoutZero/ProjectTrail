package io.redspark.ireadme.service;

import io.redspark.ireadme.repository.StepRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StepService {

	@Autowired
	private IReadmeRepository repository;
	
	public StepRepository getRepository() {
		return repository.getStepRepository();
	}
}
