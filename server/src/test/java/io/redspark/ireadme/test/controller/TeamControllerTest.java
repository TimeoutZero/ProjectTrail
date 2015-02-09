package io.redspark.ireadme.test.controller;

import static io.redspark.ireadme.test.builders.TeamBuilder.team;
import static io.redspark.ireadme.test.builders.UserBuilder.user;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import io.redspark.ireadme.entity.User;
import io.redspark.ireadme.test.builders.TeamBuilder;
import io.redspark.ireadme.test.builders.UserBuilder;
import io.redspark.ireadme.test.init.BasicControllerTest;
import io.redspark.ireadme.test.init.ControllerBase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@ControllerBase("/team")
public class TeamControllerTest extends BasicControllerTest {

	private UserBuilder lucas;

	@Before
	public void setup() throws Exception {
		lucas = user();
		saveAll();
		signIn();
	}
	
	@Test
	public void shouldGetTeam() throws Exception{
		
		TeamBuilder redspark = team("redspark", "Imagining better");
		
		saveAll();
		
		MockHttpServletRequestBuilder get = get("/{id}", redspark.getId());
		
		MvcResult result = perform(get, status().isOk());
		
		jsonAssert(result)
			.assertThat("$.id", equalTo(redspark.getId().intValue()))
			.assertEquals("$.name", "redspark")
			.assertEquals("$.description", "Imagining better");
	}	
	
	@Test
	public void shouldListTeamOfUser() throws Exception{
		
		TeamBuilder redspark    = team("redspark", "Imagining better").addUser(lucas.get());
		TeamBuilder timeoutzero = team("TimeoutZero", "There are no string on me.").addUser(lucas.get());
		
		saveAll();
		
		MvcResult result = perform(get("/me"), status().isOk());
		
		jsonAssert(result)
			.assertThat("$.[*]", hasSize(2))
			.assertThat("$.[*].id", contains(redspark.getId().intValue(), timeoutzero.getId().intValue()));
	}
	
	@Test
	public void shouldCreateTeam() throws Exception{
		
		MockHttpServletRequestBuilder post = post();
		post.param("name", "redspark");
		post.param("image", "");
		post.param("description", "Imagining better");
		
		MvcResult result = perform(post, status().isCreated());
		
		jsonAssert(result)
			.assertNotNull("$.id")
			.assertThat("$.[*].users.id", contains(lucas.getId().intValue()))
			.assertEquals("$.name", "redspark")
			.assertEquals("$.description", "Imagining better");
	}
	
	@Test
	public void shouldUpdateTeam() throws Exception{
		
		TeamBuilder dclick = team("DClick", "description");
		saveAll();

		MockHttpServletRequestBuilder put = put("/{id}", dclick.getId());
		put.param("name", "redspark");
		put.param("image", "http://mock.con/logo.png");
		put.param("description", "Imagining better");
		
		MvcResult result = perform(put, status().isOk());
		
		jsonAssert(result)
			.assertNotNull("$.id")
			.assertEquals("$.image", "http://mock.con/logo.png")
			.assertEquals("$.name", "redspark")
			.assertEquals("$.description", "Imagining better");
	}
	
	@Test
	public void shouldAddMember() throws Exception{
		
		User user = lucas.get();
		
		TeamBuilder redspark = team("redspark", "Imagining better").addUser(user);
		UserBuilder carlos = user("carlos.hpds@gmail.com", "12345");
		
		saveAll();
		
		MockHttpServletRequestBuilder put = put("/{id}/member", redspark.getId());
		put.param("userId", carlos.getId().toString());
		 
		MvcResult result = perform(put, status().isOk());
		
		jsonAssert(result)
			.assertThat("$.id", equalTo(redspark.getId().intValue()))
			.assertThat("$.users.[*].id", contains(lucas.getId().intValue(), carlos.getId().intValue()));
	}
	
	@Test
	public void shouldDeleteTeam() throws Exception {
		
		TeamBuilder redspark = team("redspark", "Imagining better");
		
		saveAll();
		
		MockHttpServletRequestBuilder delete = delete("/{id}", redspark.getId());
		
		MvcResult result = perform(delete, status().isOk());
		
		jsonAssert(result)
			.assertThat("$.id", equalTo(redspark.getId().intValue()));
		
	}
}
