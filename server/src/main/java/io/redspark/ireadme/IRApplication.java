package io.redspark.ireadme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class IRApplication  {
	
	private static final String API_PATTERN = "/api/*";
 
	public static void main(String[] args) {
		SpringApplication.run(IRApplication.class, args);
	}
	
	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
	    
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
	    registration.addUrlMappings(API_PATTERN);
	   
	    return registration;
	}
}
