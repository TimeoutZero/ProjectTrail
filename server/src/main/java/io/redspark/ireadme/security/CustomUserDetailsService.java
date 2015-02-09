package io.redspark.ireadme.security;

import static java.util.Arrays.asList;
import io.redspark.ireadme.entity.User;
import io.redspark.ireadme.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	private static final String USER_NOT_FOUND = "user.not.found";
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		logger.info("Trying to login with email " + email); 
		
		User user = repository.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException(USER_NOT_FOUND);
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), asList(new SimpleGrantedAuthority("ROLE_USER")));
	}
}
