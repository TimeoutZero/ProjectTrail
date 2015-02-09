package io.redspark.ireadme.test.builders;

import io.redspark.ireadme.entity.Tool;
import aleph.AbstractBuilder;

public class ToolBuilder extends AbstractBuilder<Tool> {

	public static ToolBuilder tool(String name, String description, TeamBuilder team){
		return new ToolBuilder().name(name).description(description).team(team);
	}
	
	public ToolBuilder name(String name) {
		return set("name", name);
	}

	public ToolBuilder description(String description) {
		return set("description", description);
	}
		
	public ToolBuilder team(TeamBuilder team) {
		
		add(tool -> { 
			team.build();
			tool.setTeam(team.get());
		});
		return this;
	}

}
