package io.redspark.ireadme;

import io.redspark.ireadme.security.CustomAuthenticationEntryPoint;
import io.redspark.ireadme.security.CustomAuthenticationFailureHandler;
import io.redspark.ireadme.security.CustomAuthenticationSuccessHandler;
import io.redspark.ireadme.security.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebMvcSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class IRSecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticateFailureHandler;
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Autowired 
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{
		builder
			.userDetailsService(customUserDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/user").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginProcessingUrl("/login")
				.successHandler(customAuthenticationSuccessHandler)
				.failureHandler(customAuthenticateFailureHandler)
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/logout")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.and()
			.exceptionHandling()
				.authenticationEntryPoint(customAuthenticationEntryPoint)
				.and()
			.sessionManagement()	
				.maximumSessions(1);
	}
}
