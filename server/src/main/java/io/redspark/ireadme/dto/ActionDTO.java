package io.redspark.ireadme.dto;

import io.redspark.ireadme.entity.Action;
import io.redspark.ireadme.entity.Team;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ActionDTO extends AbstractDTO {

	private Long id;
	private ToolDTO tool;
	
	public ActionDTO() {
		super();
	}
	
	public ActionDTO(Action action) {
		
		this.id 		 = action.getId();
		this.name 		 = action.getName();
		this.image 		 = action.getImage();
		this.description = action.getDescription();
		
		this.tool = new ToolDTO(action.getTool());
	}
	
	public Team toEntity() {

		Team team = new Team(this.name);
		team.setImage(this.image);
		team.setDescription(this.description);
		
		return team;
	}
}
