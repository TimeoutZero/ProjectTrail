package io.redspark.ireadme.test.controller;

import static io.redspark.ireadme.test.builders.ActionBuilder.action;
import static io.redspark.ireadme.test.builders.StepBuilder.step;
import static io.redspark.ireadme.test.builders.TeamBuilder.team;
import static io.redspark.ireadme.test.builders.ToolBuilder.tool;
import static io.redspark.ireadme.test.builders.UserBuilder.user;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import io.redspark.ireadme.entity.Action;
import io.redspark.ireadme.entity.Step;
import io.redspark.ireadme.entity.Tool;
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
		
		saveAll();

		ActionBuilder reindexar = action("Reindexar os objetos", "Description", holmes.get());

		saveAll();
		
		StepBuilder step1 = step("Abrir script.", "Abrir o script na url xxxxxxxxx.com", 0, reindexar.get());
		
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
	public void shouldListStep() throws Exception {
		
		TeamBuilder redspark = team("redspark", "Imagining better");
		ToolBuilder holmes = tool("Holmes", "Sistema de gestão inteligente de documentos", redspark);
		
		ActionBuilder reindexar = action("Reindexar os objetos", "Description", holmes.get());
		
		saveAll();
		
		StepBuilder step1 = step("Abrir script.", "Abrir o script na url xxxxxxxxx.com"			, 0, reindexar.get());
		StepBuilder step2 = step("Troca de id", "Trocar os ids do script"						, 1, reindexar.get());
		StepBuilder step3 = step("Trocar tipo", "Trocar o tipo de arquivo reindexavel"			, 2, reindexar.get());
		StepBuilder step4 = step("Logar ", "loggar no holmes"									, 3, reindexar.get());
		StepBuilder step5 = step("CSRF", "pegar o CSRF token da sessão ativa e trocar no script", 4, reindexar.get());
		StepBuilder step6 = step("Rodar script", "Rodar o script no console do dev tools"		, 5, reindexar.get());
		 
		saveAll();
		
		MockHttpServletRequestBuilder get = get(redspark.getId(), holmes.getId(), reindexar.getId());
		
		MvcResult result = perform(get, status().isOk());
		
		jsonAssert(result)
			.assertThat("$.[*].id", hasSize(6))
			.assertThat("$.[*].id", contains(
					step1.getId().intValue(), 
					step2.getId().intValue(), 
					step3.getId().intValue(),
					step4.getId().intValue(),
					step5.getId().intValue(),
					step6.getId().intValue()))
			.assertThat("$.[*].index", contains(0, 1, 2, 3, 4, 5));
	}
	
	@Test
	public void shouldCreateStep() throws Exception {
		
		TeamBuilder redspark = team("redspark", "Imagining better");
		ToolBuilder holmes = tool("Holmes", "Sistema de gestão inteligente de documentos", redspark);
		
		saveAll();
		
		ActionBuilder reindexar = action("Reindexar os objetos", "Description", holmes.get());
	
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
