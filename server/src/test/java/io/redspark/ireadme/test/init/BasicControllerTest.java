package io.redspark.ireadme.test.init;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import io.redspark.ireadme.IRApplication;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.context.WebApplicationContext;

import aleph.TestPersistentContext;

import com.jayway.jsonassert.JsonAssert;
import com.jayway.jsonassert.JsonAsserter;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { IRApplication.class })
@WebAppConfiguration
@IntegrationTest("server.port=10001")
public abstract class BasicControllerTest {

	protected MockMvc mock;

	@Autowired
	private WebApplicationContext context;
	
	@Resource
    private FilterChainProxy springSecurityFilterChain;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	private MockHttpSession session;

	@Before
	public void setupConfiguration() {

		
		mock = MockMvcBuilders
				.webAppContextSetup(context)
				.alwaysDo(print())
				.addFilters(this.springSecurityFilterChain)
				.build();
	}
	
	

	public BasicControllerTest signIn() throws Exception {

		MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/api/login");
		post.param("username", "lucas.gmmartins@gmail.com");
		post.param("password", "12345");

		session = (MockHttpSession) mock.perform(post).andExpect(status().isOk()).andReturn().getRequest().getSession();

		return this;
	}
	
	protected void saveAll() {
		 
		TransactionTemplate template = new TransactionTemplate(transactionManager);
		template.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				TestPersistentContext.get().saveAll();
			}
		});
	}

	// UTIL
	
	protected MockHttpServletRequestBuilder get(Object... variables) {
		return MockMvcRequestBuilders.get(getControllerBase(), variables);
	}

	protected MockHttpServletRequestBuilder get(String uri, Object... variables) {
		return MockMvcRequestBuilders.get(getControllerBase() + uri, variables);
	}
	
	protected MockHttpServletRequestBuilder post(Object... variables) {
		return MockMvcRequestBuilders.post(getControllerBase(), variables);
	}

	protected MockHttpServletRequestBuilder post(String endpoint, Object... variables) {
		return MockMvcRequestBuilders.post(getControllerBase() + endpoint, variables);
	}

	protected MockHttpServletRequestBuilder put(Object... variables) {
		return MockMvcRequestBuilders.put(getControllerBase(), variables);
	}
	
	protected MockHttpServletRequestBuilder put(String endpoint, Object... variables) {
		return MockMvcRequestBuilders.put(getControllerBase() + endpoint, variables);
	}

	protected MockHttpServletRequestBuilder delete(String endpoint, Object... variables){
		return MockMvcRequestBuilders.delete(getControllerBase() + endpoint, variables);
	}
	
	private String getControllerBase() {

		ControllerBase annotation = getClass().getAnnotation(ControllerBase.class);
		String base = annotation.value();

		return StringUtils.isNotBlank(base) ? base : StringUtils.EMPTY;
	}

	protected JsonAsserter jsonAssert(MvcResult result) throws UnsupportedEncodingException {
		return JsonAssert.with(result.getResponse().getContentAsString());
	}

	protected AssertUtil jsonError(MvcResult result) throws UnsupportedEncodingException {
		return AssertUtil.with(result.getResponse().getContentAsString()).isJsonError();
	}

	protected MvcResult perform(final MockHttpServletRequestBuilder requestBuilder, final ResultMatcher resultMatcher) throws Exception {
		
		if(session != null) {
			requestBuilder.session(session);
		}
		return mock.perform(requestBuilder).andExpect(resultMatcher).andDo(print()).andReturn();
	}
}
