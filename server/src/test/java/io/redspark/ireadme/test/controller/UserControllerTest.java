package io.redspark.ireadme.test.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import io.redspark.ireadme.test.init.BasicControllerTest;
import io.redspark.ireadme.test.init.ControllerBase;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@ControllerBase("/user")
public class UserControllerTest extends BasicControllerTest {

	@Test
	public void shouldCreateUser() throws Exception{
		
		MockHttpServletRequestBuilder post = post();
		post.param("email", "lucas.martins@redspark.io");
		post.param("password", "12345");
		 
		MvcResult result = perform(post, status().isCreated());
		
		jsonAssert(result)
			.assertEquals("$.email", "lucas.martins@redspark.io");
	}
}
