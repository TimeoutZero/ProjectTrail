package io.redspark.ireadme.dto;

import io.redspark.ireadme.entity.Step;

public class StepDTO extends AbstractDTO {

	private Integer index;
	
	public StepDTO(Step step) {
		this.index = step.getIndex();
	}
	
	public Integer getIndex() {
		return index;
	}
}
