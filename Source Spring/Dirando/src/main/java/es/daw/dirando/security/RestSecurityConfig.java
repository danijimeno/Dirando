package es.daw.dirando.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public UsuarioAuthRepository usuarioAuthRepository;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//Public pages
		http.antMatcher("/rest/**");
		
		// URLs that need authentication to access to it
			//Admin querys ...
				http.authorizeRequests().antMatchers(HttpMethod.GET, "/rest/admin/**").hasRole("ADMIN");
				http.authorizeRequests().antMatchers(HttpMethod.POST, "/rest/admin/**").hasRole("ADMIN");
				http.authorizeRequests().antMatchers(HttpMethod.PUT, "/rest/admin/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/rest/getUser/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/rest/updateAccount/**").hasRole("USER");
			
		//Login form 
        http.formLogin().loginPage("/rest/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/rest/correctLogIn");
        http.formLogin().failureUrl("/rest/errorLogIn");

        //Logout
        http.logout().logoutUrl("/rest/logout");
        http.logout().logoutSuccessHandler((rq, rs, a) -> {	});
        
		// Disable CSRF protection (it is difficult to implement with ng2)
		http.csrf().disable();
		
		

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Database authentication provider
		auth.authenticationProvider(usuarioAuthRepository);
	}
}
