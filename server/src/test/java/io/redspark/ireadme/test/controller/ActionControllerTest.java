package io.redspark.ireadme.test.controller;

import static io.redspark.ireadme.test.builders.ActionBuilder.action;
import static io.redspark.ireadme.test.builders.TeamBuilder.team;
import static io.redspark.ireadme.test.builders.ToolBuilder.tool;
import static io.redspark.ireadme.test.builders.UserBuilder.user;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import io.redspark.ireadme.entity.Action;
import io.redspark.ireadme.test.builders.ActionBuilder;
import io.redspark.ireadme.test.builders.TeamBuilder;
import io.redspark.ireadme.test.builders.ToolBuilder;
import io.redspark.ireadme.test.init.BasicControllerTest;
import io.redspark.ireadme.test.init.ControllerBase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@ControllerBase("/team/{teamId}/tool/{toolId}/action")
public class ActionControllerTest extends BasicControllerTest {

	@Before
	public void setup() throws Exception {
		user();
		saveAll();
		signIn();
	}
	
	@Test
	public void shouldGetAction() throws Exception {
		
		TeamBuilder redspark = team("redspark", "Imagining better");
		ToolBuilder holmes = tool("Holmes", "Sistema de gestão inteligente de documentos", redspark);
		saveAll();

		ActionBuilder actionBuilder = action("Reindexar os objetos", "Description", holmes.get());
		
		saveAll();
	
		
		MockHttpServletRequestBuilder get = get("/{id}", redspark.getId(), holmes.getId(), actionBuilder.getId());
		
		MvcResult result = perform(get, status().isOk());
		
		Action action = actionBuilder.get();
		
		jsonAssert(result)
			.assertThat("$.id", equalTo(holmes.getId().intValue()))
			.assertEquals("$.name", action.getName())
			.assertEquals("$.description", action.getDescription())
			.assertEquals("$.tool.id", holmes.getId().intValue());
	}
	
	@Test
	public void shouldCreateAction() throws Exception {
		
		TeamBuilder redspark = team("redspark", "Imagining better");
		ToolBuilder holmes = tool("Holmes", "Sistema de gestão inteligente de documentos", redspark);
		
		saveAll();
		
		MockHttpServletRequestBuilder post = post(redspark.getId(), holmes.getId());
		post.param("name", "Reindexar os documentos.");
		post.param("image", "img/main/img/mocks/holmes.jpg");
		post.param("description", "Processo de reindexamento de documentos utilizado para blá blá bá.");
		
		MvcResult result = perform(post, status().isCreated());
		
		jsonAssert(result)
			.assertNotNull("$.id")
			.assertEquals("$.name", "Reindexar os documentos.")
			.assertEquals("$.image", "img/main/img/mocks/holmes.jpg")
			.assertEquals("$.description", "Processo de reindexamento de documentos utilizado para blá blá bá.");
	}
//	
//	@Test
//	public void shouldListAction() throws Exception {
//		 
//		TeamBuilder redspark = team("redspark", "Imagining better");
//		ToolBuilder holmes = tool("Holmes", "Sistema de gestão inteligente de documentos", redspark);
//		ToolBuilder ireadme = tool("iReadme", "Interactive Readme", redspark);
//		
//		saveAll(); 
//		
//		MockHttpServletRequestBuilder get = get(redspark.getId());
//		
//		MvcResult result = perform(get, status().isOk());
//		
//		jsonAssert(result)
//			.assertThat("$.[*].id", contains(holmes.getId().intValue(), ireadme.getId().intValue()));
//	}

	@Test
	public void shouldUpdateAction() throws Exception {
		
		TeamBuilder redspark = team("redspark", "Imagining better");
		ToolBuilder tool = tool("Generic Tool", "Generic Description", redspark);

		saveAll();
		
		ActionBuilder action = action("Reindexar os objetos", "Description", tool.get());
		
		saveAll();
		
		MockHttpServletRequestBuilder put = put("/{id}", redspark.getId(), tool.getId(), action.getId());
		put.param("name", "Reindexar");
		put.param("image", "img/main/img/mocks/holmes.jpg");
		put.param("description", "Reindexar");
		
		MvcResult result = perform(put, status().isOk());
		
		jsonAssert(result)
			.assertNotNull("$.id")
			.assertEquals("$.name", "Reindexar")
			.assertEquals("$.image", "img/main/img/mocks/holmes.jpg")
			.assertEquals("$.description", "Reindexar");
	}

	@Test
	public void shouldDeleteAction() throws Exception {
		
		TeamBuilder redspark = team("redspark", "Imagining better");
		ToolBuilder tool = tool("Generic Tool", "Generic Description", redspark);
		saveAll();
		ActionBuilder action = action("Reindexar os objetos", "Description", tool.get());
		
		saveAll();
		
		MockHttpServletRequestBuilder delete = delete("/{id}", redspark.getId(), tool.getId(), action.getId());

		MvcResult result = perform(delete, status().isOk());
		
		jsonAssert(result)
			.assertThat("$.id", equalTo(action.getId().intValue()));
	}
}
