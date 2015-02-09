package io.redspark.ireadme.service;

import io.redspark.ireadme.entity.Action;
import io.redspark.ireadme.exceptions.IReadmeException;
import io.redspark.ireadme.exceptions.IReadmeExceptionMessage;
import io.redspark.ireadme.repository.ActionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

	@Autowired
	private IReadmeRepository repository;
	
	public Action findActionById(Long id) {
		
		Action action = repository.getActionRepository().findOne(id);
		
		if(action == null) {
			throw new IReadmeException(HttpStatus.NOT_FOUND, IReadmeExceptionMessage.notFound("action"));
		}
		return action;
	}
	
	public ActionRepository getRepository() {
		return repository.getActionRepository();
	}
}
