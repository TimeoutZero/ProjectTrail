package io.redspark.ireadme.form;

import io.redspark.ireadme.entity.Action;
import io.redspark.ireadme.entity.Team;
import io.redspark.ireadme.entity.Tool;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.validator.constraints.NotBlank;

//@ApiObject
@Getter @Setter
public class GenericForm {

	private static final String TEAM_INVALID_BLANK_NAME = "team.invalid.blank.name";
	
   // @ApiObjectField
	@NotBlank(message = TEAM_INVALID_BLANK_NAME)
	private String name;
	
    //@ApiObjectField
	private String image;
    
    //@ApiObjectField
	private String description;
	
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
