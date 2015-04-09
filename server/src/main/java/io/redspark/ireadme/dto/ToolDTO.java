package io.redspark.ireadme.dto;

import io.redspark.ireadme.entity.Tool;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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
	
}
