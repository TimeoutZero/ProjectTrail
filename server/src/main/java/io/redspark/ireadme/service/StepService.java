package io.redspark.ireadme.service;

import io.redspark.ireadme.entity.Step;
import io.redspark.ireadme.exceptions.IReadmeException;
import io.redspark.ireadme.exceptions.IReadmeExceptionMessage;
import io.redspark.ireadme.repository.StepRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class StepService {

	@Autowired
	private IReadmeRepository repository;

	public StepRepository getRepository() {
		return repository.getStepRepository();
	}

	public Step findById(Long id) {

		Step step = getRepository().findOne(id);

		if (step == null) { 
			throw new IReadmeException(HttpStatus.NOT_FOUND, IReadmeExceptionMessage.notFound("step"));
		}

		return step;
	}
}
