package io.redspark.ireadme.test.controller;

import static io.redspark.ireadme.test.builders.ActionBuilder.action;
import static io.redspark.ireadme.test.builders.TeamBuilder.team;
import static io.redspark.ireadme.test.builders.ToolBuilder.tool;
import static io.redspark.ireadme.test.builders.UserBuilder.user;
import io.redspark.ireadme.test.builders.ActionBuilder;
import io.redspark.ireadme.test.builders.TeamBuilder;
import io.redspark.ireadme.test.builders.ToolBuilder;
import io.redspark.ireadme.test.init.BasicControllerTest;
import io.redspark.ireadme.test.init.ControllerBase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@ControllerBase("/team/{teamId}/tool/{toolId}/action/{id}/step")
public class StepControllerTest extends BasicControllerTest {

	@Before
	public void setup() throws Exception {
		user();
		saveAll();
		signIn();
	}
	
	@Test
	public void shouldCreateStep() {
		
		TeamBuilder redspark = team("redspark", "Imagining better");
		ToolBuilder holmes = tool("Holmes", "Sistema de gestão inteligente de documentos", redspark);
		ActionBuilder reindexar = action("Reindexar os objetos", "Description", holmes);
	
		saveAll();
		
		MockHttpServletRequestBuilder post = post(redspark.getId(), holmes.getId(), reindexar.getId());
	}
	
}
