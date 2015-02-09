package io.redspark.ireadme.test.builders;

import io.redspark.ireadme.entity.Action;
import aleph.AbstractBuilder;

public class ActionBuilder extends AbstractBuilder<Action> {
 
	public static ActionBuilder action(String name, String description, ToolBuilder tool){
		return new ActionBuilder().name(name).description(description).tool(tool);
	} 
	
	public ActionBuilder name(String name) {
		return set("name", name);
	}

	public ActionBuilder description(String description) {
		return set("description", description);
	}
		
	public ActionBuilder tool(ToolBuilder tool) {
		
		add(action -> { 
			tool.build();
			tool.get().getActions().add(action);
		});
		return this;
	}

}
