package io.redspark.ireadme.test.controller;

import static io.redspark.ireadme.test.builders.ActionBuilder.action;
import static io.redspark.ireadme.test.builders.TeamBuilder.team;
import static io.redspark.ireadme.test.builders.ToolBuilder.tool;
import static io.redspark.ireadme.test.builders.UserBuilder.user;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import io.redspark.ireadme.test.builders.ActionBuilder;
import io.redspark.ireadme.test.builders.StepBuilder;
import io.redspark.ireadme.test.builders.TeamBuilder;
import io.redspark.ireadme.test.builders.ToolBuilder;
import io.redspark.ireadme.test.init.BasicControllerTest;
import io.redspark.ireadme.test.init.ControllerBase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static io.redspark.ireadme.test.builders.StepBuilder.step;

@ControllerBase("/team/{teamId}/tool/{toolId}/action/{id}/step")
public class StepControllerTest extends BasicControllerTest {

	@Before
	public void setup() throws Exception {
		user();
		saveAll();
		signIn();
	}
	
	@Test
	public void shouldGetStep() throws Exception {

		TeamBuilder redspark = team("redspark", "Imagining better");
		ToolBuilder holmes = tool("Holmes", "Sistema de gestão inteligente de documentos", redspark);
		ActionBuilder reindexar = action("Reindexar os objetos", "Description", holmes);
		StepBuilder step1 = step("Abrir script.", "Abrir o script na url xxxxxxxxx.com", 0, reindexar);
		 
		saveAll();
		
		MockHttpServletRequestBuilder get = get("/{id}", redspark.getId(), holmes.getId(), reindexar.getId(), step1.getId());
		
		MvcResult result = perform(get, status().isOk());
		
		jsonAssert(result)
			.assertThat("$.id", equalTo(step1.getId().intValue()))
			.assertEquals("$.name", "Abrir script.")
			.assertEquals("$.description", "Abrir o script na url xxxxxxxxx.com")
			.assertEquals("$.index", 0);
	}
	
	@Test
	public void shouldCreateStep() throws Exception {
		
		TeamBuilder redspark = team("redspark", "Imagining better");
		ToolBuilder holmes = tool("Holmes", "Sistema de gestão inteligente de documentos", redspark);
		ActionBuilder reindexar = action("Reindexar os objetos", "Description", holmes);
	
		saveAll();
		
		MockHttpServletRequestBuilder post = post(redspark.getId(), holmes.getId(), reindexar.getId());
		post.param("name", "Abrir script");
		post.param("description", "1- abrir o script na url xxxxxxxxx.com");
		post.param("index", "0");
	
		MvcResult result = perform(post, status().isCreated());
		
		jsonAssert(result)
			.assertNotNull("$.id")
			.assertEquals("$.name", "Abrir script")
			.assertEquals("$.description", "1- abrir o script na url xxxxxxxxx.com")
			.assertEquals("$.index", 0);
	}
	
}
