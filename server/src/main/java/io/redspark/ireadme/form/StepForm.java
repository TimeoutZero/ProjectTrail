package io.redspark.ireadme.form;

import javax.validation.constraints.NotNull;

import io.redspark.ireadme.entity.Step;

public class StepForm extends GenericForm {

	@NotNull
	private Integer index;
	
	public Integer getIndex() {
		return index;
	}
	
	public void setIndex(Integer index) {
		this.index = index;
	}
	
	public Step toEntity() {
		
		Step step = new Step();
		step.setName(getName());
		step.setDescription(getDescription());
		step.setIndex(getIndex());
		
		return step;
	}
}
