package io.redspark.ireadme.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "team")
public class Team extends AbstractFormEntity{

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
}
