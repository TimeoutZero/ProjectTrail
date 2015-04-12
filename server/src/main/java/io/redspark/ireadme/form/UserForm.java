package io.redspark.ireadme.form;

import io.redspark.ireadme.entity.User;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject
public class UserForm {

    @ApiObjectField
	private String email;

    @ApiObjectField
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public User toEntity() {
		return new User(this.email, this.password);
	}
}
