package io.redspark.ireadme.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import io.redspark.ireadme.dto.UserDTO;
import io.redspark.ireadme.entity.User;
import io.redspark.ireadme.exceptions.IReadmeException;
import io.redspark.ireadme.exceptions.IReadmeExceptionMessage;
import io.redspark.ireadme.form.UserForm;
import io.redspark.ireadme.service.IReadmeRepository;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
 
	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IReadmeRepository ireadme;
	
	@RequestMapping(method = POST)
	@ResponseStatus(HttpStatus.CREATED) 
	public UserDTO save(@Valid UserForm form) {
		
		String email = form.getEmail();
		log.info("Trying to find user with email: {}", email);

		User user = ireadme.getUserRepository().findByEmail(email);
		
		if(user != null) {
			throw new IReadmeException(HttpStatus.INTERNAL_SERVER_ERROR, IReadmeExceptionMessage.alreadyExist("user"));
		}
		
		user = form.toEntity();
		user = ireadme.getUserRepository().save(user);
		
		return new UserDTO(user);
	}

}
