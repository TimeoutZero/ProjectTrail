package io.redspark.ireadme.repository;

import io.redspark.ireadme.entity.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String email);
}
