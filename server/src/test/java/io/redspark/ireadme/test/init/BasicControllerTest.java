package io.redspark.ireadme.test.init;

import static io.redspark.ireadme.init.AppProfile.TEST;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import io.redspark.ireadme.init.AppConfig;
import io.redspark.ireadme.init.AppWebConfig;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.context.WebApplicationContext;

import aleph.TestPersistentContext;

import com.jayway.jsonassert.JsonAssert;
import com.jayway.jsonassert.JsonAsserter;

@WebAppConfiguration
@ActiveProfiles(TEST)
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(classes = { AppConfig.class, AppWebConfig.class, AppTestProvider.class })
public abstract class BasicControllerTest {

	protected MockMvc mock;

	@Autowired
	private WebApplicationContext context;
	
	@Resource
    private FilterChainProxy springSecurityFilterChain;
	
	@Autowired
	private TransactionTemplate transactionManager;
	
	@Autowired
	private JpaPersistenceProvider jpaPersistenceProvider;

	private MockHttpSession session;

	@Before
	public void setupConfiguration() {

		jpaPersistenceProvider.clear();
		
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
		transactionManager.execute(new TransactionCallbackWithoutResult() {
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
