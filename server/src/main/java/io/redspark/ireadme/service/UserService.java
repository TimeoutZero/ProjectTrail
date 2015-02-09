package io.redspark.ireadme.service;

import io.redspark.ireadme.entity.User;
import io.redspark.ireadme.exceptions.IReadmeException;
import io.redspark.ireadme.exceptions.IReadmeExceptionMessage;
import io.redspark.ireadme.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private IReadmeRepository repository;
	
	public User findUserById(Long id){
		
		User user = repository.getUserRepository().findOne(id);
		
		if(user == null) {
			throw new IReadmeException(HttpStatus.NOT_FOUND, IReadmeExceptionMessage.notFound("user"));
		}
		
		return user;
	}
	
	public UserRepository getRepository() {
		return repository.getUserRepository();
	}
}
