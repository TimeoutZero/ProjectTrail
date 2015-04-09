package io.redspark.ireadme.dto;

import io.redspark.ireadme.entity.Step;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StepDTO extends AbstractDTO {

	private Integer index;
	
	public StepDTO(Step step) {
		this.id    		 = step.getId();
		this.name 		 = step.getName();
		this.description = step.getDescription();
		this.index 		 = step.getIndex();
	}
	
}
