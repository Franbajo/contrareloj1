package com.fran;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource2;
	@Autowired
	private PasswordEncoder passwordEncoder; 
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
	.dataSource(dataSource2).passwordEncoder(passwordEncoder);
		}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll()
		.antMatchers("/clienteslistar").hasAnyAuthority("USER")
		.antMatchers("/new").hasAnyAuthority("ADMIN")
		.antMatchers("/clientesave").hasAnyAuthority("ADMIN")
		.antMatchers("/editarcliente/**").hasAnyAuthority("ADMIN")
		.antMatchers("/eliminarcliente/**").hasAnyAuthority("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin().loginPage("/login")
		.permitAll()
		.and()
		.logout().permitAll();
	}

}
