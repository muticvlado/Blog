package com.vlado;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource myDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {				
		auth
		.jdbcAuthentication()
		.dataSource(myDataSource);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		
		
		  http.authorizeRequests()
		  
		  .antMatchers("/*").permitAll()
		  
		  .antMatchers("/administration/bloger-post-form").hasAnyRole("bloger", "admin")
		  .antMatchers("/administration/bloger-post-save").hasAnyRole("bloger", "admin")
		  .antMatchers("/administration/post-list").hasAnyRole("bloger", "admin")
		  .antMatchers("/administration/post-update-form").hasAnyRole("bloger", "admin")
		  .antMatchers("/administration/post-save").hasAnyRole("bloger", "admin")
		  .antMatchers("/administration/post-delete").hasAnyRole("bloger", "admin")
		  .antMatchers("/administration/user-form-edit").hasAnyRole("bloger", "admin")
		  .antMatchers("/administration/user-form-edit-save").hasAnyRole("bloger", "admin")
		  .antMatchers("/administration/user-change-password").hasAnyRole("bloger", "admin")
		  .antMatchers("/administration/user-change-password-save").hasAnyRole("bloger", "admin")		  
		  
		  .antMatchers("/administration/**").hasRole("admin")
		  
		  .and()
		  .formLogin()
		  .loginProcessingUrl("/authenticateTheUser").permitAll()
		  .and()
		  .logout().permitAll()
		  .and()
		  .exceptionHandling().accessDeniedPage("/access-deneid");
		  
	}

}
