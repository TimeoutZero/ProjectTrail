package io.redspark.ireadme.dto;

import io.redspark.ireadme.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {

	private Long id;
	private String email;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(User user) {
		super();
		this.id    = user.getId();
		this.email = user.getEmail();
	}

}
