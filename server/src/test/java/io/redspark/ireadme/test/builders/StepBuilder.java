package io.redspark.ireadme.test.builders;

import io.redspark.ireadme.entity.Step;
import aleph.AbstractBuilder;

public class StepBuilder extends AbstractBuilder<Step>{

	public static StepBuilder step(String name, String description, Integer index, ActionBuilder action) {
		return new StepBuilder().name(name).description(description).index(index).action(action);
	}
	
	public StepBuilder name(String name) {
		return set("name", name);
	}

	public StepBuilder description(String description) {
		return set("description", description);
	}
	
	public StepBuilder index(Integer index) {
		return set("index", index);
	}
		
	public StepBuilder action(ActionBuilder action) {
		
		add(step -> { 
			action.build();
			action.get().getSteps().add(step);
		});
		return this;
	}
}
