package io.redspark.ireadme.dto;

import io.redspark.ireadme.entity.Action;
import io.redspark.ireadme.entity.Team;

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
	
	public ToolDTO getTool() {
		return tool;
	}
	
	public void setTool(ToolDTO tool) {
		this.tool = tool;
	}
	
	public Team toEntity() {

		Team team = new Team(this.name);
		team.setImage(this.image);
		team.setDescription(this.description);
		
		return team;
	}
}
