package io.redspark.ireadme.dto;

import java.util.ArrayList;
import java.util.Collection;

import io.redspark.ireadme.entity.Team;
import io.redspark.ireadme.entity.User;

public class TeamDTO extends AbstractDTO {

	private Long id;
	private Collection<UserDTO> users = new ArrayList<>();
	
	public TeamDTO() {
		super();
	}
	
	public TeamDTO(Team team) {
		
		this.id 		 = team.getId();
		this.name 		 = team.getName();
		this.image 		 = team.getImage();
		this.description = team.getDescription();
		
		for (User user : team.getUsers()) {
			this.users.add(new UserDTO(user));
		}
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Collection<UserDTO> getUsers() {
		return users;
	}
	
	public Team toEntity() {

		Team team = new Team(this.name);
		team.setImage(this.image);
		team.setDescription(this.description);
		
		return team;
	}
}
