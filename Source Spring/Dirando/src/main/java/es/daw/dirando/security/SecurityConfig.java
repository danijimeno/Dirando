package es.daw.dirando.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioAuthRepository usuarioAuthRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	
    	//Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/usuario").permitAll();
        http.authorizeRequests().antMatchers("/registro").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        http.authorizeRequests().antMatchers("/Producto/{id}").permitAll();
        http.authorizeRequests().antMatchers("/ListadoProducto").permitAll();
        http.authorizeRequests().antMatchers("/ListadoProductoAjax").permitAll();
        


        //Private pages (all other pages)
        http.authorizeRequests().antMatchers("/usuario").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");

        //Login form 
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/usuario");
        http.formLogin().failureUrl("/registro");

        //Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");
        
        //Disable CSRF at the moment
        http.csrf().disable();
        
        //How to disable 'X-Frame-Options' response header in Spring Security
    	http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        // Database authentication provider
        auth.authenticationProvider(usuarioAuthRepository);
    }


}
