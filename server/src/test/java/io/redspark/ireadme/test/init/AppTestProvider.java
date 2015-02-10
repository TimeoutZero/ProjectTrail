package io.redspark.ireadme.test.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import aleph.ChainPersistenceProvider;
import aleph.ContextUtil;
import aleph.PersistenceProvider;

@Configuration
@PropertySource("classpath:config/test.properties")
public class AppTestProvider {

	@Bean
	public ContextUtil contextUtil() {
		return new ContextUtil();
	}

	@Bean
	public ChainPersistenceProvider chainPersistenceProvider() {
		return new ChainPersistenceProvider();
	}

	@Bean
	public PersistenceProvider persistenceProvider() {
		return new JpaPersistenceProvider()
			.addNativeQuery("delete from ROLES")
			.addQuery("delete from Step")
			.addQuery("delete from Action")
			.addQuery("delete from Tool")
			.addQuery("delete from Team")
			.addQuery("delete from User");
	}
}
