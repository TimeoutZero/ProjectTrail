package io.redspark.ireadme.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Audited
@Table(name = "user")
public class User extends AbstractEntity {
	
	public static final String ROLE_USER  = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;

	@Column(name = "nickname")
	private String nickname;
	
	@CollectionTable(name = "roles")
	@ElementCollection(fetch = FetchType.EAGER)
	private Collection<String> roles = new ArrayList<>();
	
	@ManyToMany(mappedBy = "users")
	private Collection<Team> teams = new ArrayList<>();
	
	public User() {
		super();
	}
	
	public User(String email, String password) {
		super();
		this.email 	  = email;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.roles.add(ROLE_USER);
	}
	
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
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public Collection<String> getRoles() {
		return roles;
	}
	
	public void setRoles(Collection<String> roles) {
		this.roles = roles;
	}
	
	public Collection<Team> getTeams() {
		return teams;
	}
	
}
