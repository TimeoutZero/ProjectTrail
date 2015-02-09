package io.redspark.ireadme.dto;

import io.redspark.ireadme.entity.Tool;

public class ToolDTO extends AbstractDTO {

	private Long id;
	
	public ToolDTO() {
		super();
	}
	
	public ToolDTO(Tool tool) {
		
		this.id 		 = tool.getId();
		this.name 		 = tool.getName();
		this.image 		 = tool.getImage();
		this.description = tool.getDescription();
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
}
