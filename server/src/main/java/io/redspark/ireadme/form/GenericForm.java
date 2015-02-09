package io.redspark.ireadme.form;

import io.redspark.ireadme.entity.Action;
import io.redspark.ireadme.entity.Team;
import io.redspark.ireadme.entity.Tool;

import org.hibernate.validator.constraints.NotBlank;

public class GenericForm {

	private static final String TEAM_INVALID_BLANK_NAME = "team.invalid.blank.name";
	
	@NotBlank(message = TEAM_INVALID_BLANK_NAME)
	private String name;
	
	private String image;
	private String description;
	
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
	
	public Team toTeamEntity() {
		
		Team team = new Team();
		team.setName(this.name);
		team.setImage(this.image);
		team.setDescription(this.description);
		
		return team;
	}
	
	public Tool toToolEntity() {
		
		Tool tool = new Tool();
		tool.setName(this.name);
		tool.setImage(this.image);
		tool.setDescription(this.description);
		
		return tool;
	}
	
	public Action toActionEntity() {
		
		Action action = new Action();
		action.setName(this.name);
		action.setImage(this.image);
		action.setDescription(this.description);
		
		return action;
	}
}
