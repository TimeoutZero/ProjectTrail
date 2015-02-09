package io.redspark.ireadme.test.builders;

import io.redspark.ireadme.entity.Team;
import io.redspark.ireadme.entity.User;
import aleph.AbstractBuilder;

public class TeamBuilder extends AbstractBuilder<Team>{

	public static TeamBuilder team(String name, String description){
		return new TeamBuilder().name(name).description(description);
	}
	
	public TeamBuilder name(String name) {
		return set("name", name);
	}

	public TeamBuilder description(String description) {
		return set("description", description);
	}
	
	public TeamBuilder addUser(User user){
		
		add(team -> {
			team.getUsers().add(user);
		});
		
		return this;
	}
}
