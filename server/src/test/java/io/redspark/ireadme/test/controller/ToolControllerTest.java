package io.redspark.ireadme.test.controller;

import static io.redspark.ireadme.test.builders.TeamBuilder.team;
import static io.redspark.ireadme.test.builders.ToolBuilder.tool;
import static io.redspark.ireadme.test.builders.UserBuilder.user;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import io.redspark.ireadme.entity.Tool;
import io.redspark.ireadme.test.builders.TeamBuilder;
import io.redspark.ireadme.test.builders.ToolBuilder;
import io.redspark.ireadme.test.init.BasicControllerTest;
import io.redspark.ireadme.test.init.ControllerBase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@ControllerBase("/team/{id}/tool")
public class ToolControllerTest extends BasicControllerTest {

	@Before
	public void setup() throws Exception {
		user();
		saveAll();
		signIn();
	}
	
	@Test
	public void shouldGetTool() throws Exception {
		
		TeamBuilder redspark = team("redspark", "Imagining better");
		ToolBuilder holmesBuilder = tool("Holmes", "Sistema de gestão inteligente de documentos", redspark);
		
		saveAll();
		
		Tool holmes = holmesBuilder.get();
		
		MockHttpServletRequestBuilder get = get("/{id}", redspark.getId(), holmes.getId());
		
		MvcResult result = perform(get, status().isOk());
		
		jsonAssert(result)
			.assertThat("$.id", equalTo(holmes.getId().intValue()))
			.assertEquals("$.name", holmes.getName())
			.assertEquals("$.description", holmes.getDescription());
	}
	
	@Test
	public void shouldListTool() throws Exception {
		 
		TeamBuilder redspark = team("redspark", "Imagining better");
		ToolBuilder holmes = tool("Holmes", "Sistema de gestão inteligente de documentos", redspark);
		ToolBuilder ireadme = tool("iReadme", "Interactive Readme", redspark);
		
		saveAll(); 
		
		MockHttpServletRequestBuilder get = get(redspark.getId());
		
		MvcResult result = perform(get, status().isOk());
		
		jsonAssert(result)
			.assertThat("$.[*].id", contains(holmes.getId().intValue(), ireadme.getId().intValue()));
	}

	@Test
	public void shouldCreateTool() throws Exception {
		
		TeamBuilder redspark = team("redspark", "Imagining better");
		
		saveAll();
		
		MockHttpServletRequestBuilder post = post(redspark.getId());
		post.param("name", "Holmes");
		post.param("image", "img/main/img/mocks/holmes.jpg");
		post.param("description", "Sistema de gestão inteligente de documentos");
		
		MvcResult result = perform(post, status().isCreated());
		
		jsonAssert(result)
			.assertNotNull("$.id")
			.assertEquals("$.name", "Holmes")
			.assertEquals("$.image", "img/main/img/mocks/holmes.jpg")
			.assertEquals("$.description", "Sistema de gestão inteligente de documentos");
	}
	
	@Test
	public void shouldUpdateTool() throws Exception {
		
		TeamBuilder redspark = team("redspark", "Imagining better");
		ToolBuilder tool = tool("Generic Tool", "Generic Description", redspark);
		
		saveAll();
		 
		MockHttpServletRequestBuilder put = put("/{id}", redspark.getId(), tool.getId());
		put.param("name", "Holmes");
		put.param("image", "img/main/img/mocks/holmes.jpg");
		put.param("description", "Sistema de gestão inteligente de documentos");
		
		MvcResult result = perform(put, status().isOk());
		
		jsonAssert(result)
			.assertNotNull("$.id")
			.assertEquals("$.name", "Holmes")
			.assertEquals("$.image", "img/main/img/mocks/holmes.jpg")
			.assertEquals("$.description", "Sistema de gestão inteligente de documentos");
	}
	
	@Test
	public void shouldDeleteTool() throws Exception {
		
		TeamBuilder redspark = team("redspark", "Imagining better");
		ToolBuilder tool = tool("Generic Tool", "Generic Description", redspark);
		
		saveAll();
		
		MockHttpServletRequestBuilder delete = delete("/{id}", redspark.getId(), tool.getId());

		MvcResult result = perform(delete, status().isOk());
		
		jsonAssert(result)
			.assertThat("$.id", equalTo(tool.getId().intValue()));
	}
}
