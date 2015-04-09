package io.redspark.ireadme.dto;

import io.redspark.ireadme.entity.Team;
import io.redspark.ireadme.entity.User;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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
	
	public Team toEntity() {

		Team team = new Team(this.name);
		team.setImage(this.image);
		team.setDescription(this.description);
		
		return team;
	}
}
