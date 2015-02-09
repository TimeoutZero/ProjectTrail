package io.redspark.ireadme.service;

import io.redspark.ireadme.entity.Tool;
import io.redspark.ireadme.exceptions.IReadmeException;
import io.redspark.ireadme.exceptions.IReadmeExceptionMessage;
import io.redspark.ireadme.repository.ToolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ToolService {

	@Autowired
	private IReadmeRepository repository;
	
	public Tool findToolById(Long id) {
		
		Tool tool = getRepository().findOne(id);
		
		if(tool == null) {
			throw new IReadmeException(HttpStatus.NOT_FOUND, IReadmeExceptionMessage.notFound("tool"));
		}
		
		return tool;
	}
	
	public void exist(Long toolId) {
		
		if(!getRepository().exists(toolId)){
			throw new IReadmeException(HttpStatus.NOT_FOUND, IReadmeExceptionMessage.notFound("tool"));
		}
	}

	public ToolRepository getRepository() {
		return repository.getToolRepository();
	}

}
