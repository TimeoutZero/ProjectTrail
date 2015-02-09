package io.redspark.ireadme.security;

import io.redspark.ireadme.entity.User;
import io.redspark.ireadme.repository.UserRepository;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Configuration
public class IReadmeSecurityContext implements ApplicationContextAware {
	
	private static ApplicationContext context;

	public static User getLoggedUser(){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 
		UserRepository repository = context.getBean(UserRepository.class);
		User user = repository.findByEmail(authentication.getName());
		
		return user;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
}