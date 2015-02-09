package io.redspark.ireadme.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "team")
public class Team extends AbstractEntity{

	@ManyToMany
	private Collection<User> users = new ArrayList<>();
	
	@OneToMany(mappedBy =  "team")
	private Collection<Tool> tools = new  ArrayList<>();
	
	public Team() { 
		super();
	}

	public Team(String name) {
		this.name = name;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	
	public Collection<Tool> getTools() {
		return tools;
	}
}
