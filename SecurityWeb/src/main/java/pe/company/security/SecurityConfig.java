package pe.company.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import pe.company.service.UserServiceImpl;

@Configuration //se indica que esta clase es de configuración
@EnableWebSecurity //habilito spring security
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired//Se inyecta el bean donde tiene el metodo loadByUsername()
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private CustomSuccessHandler customSuccessHandler;
	
	@Override //método de autenticación
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		
		//usuarios en memoria
		//auth.inMemoryAuthentication().withUser("antoine").password("{noop}a123").roles("USER");
		//auth.inMemoryAuthentication().withUser("werther").password("{noop}w123").roles("USER");
		//auth.inMemoryAuthentication().withUser("margarita").password("{noop}m123").roles("ADMIN");
		//auth.inMemoryAuthentication().withUser("charlotte").password("{noop}c123").roles("DBA","ADMIN");
		
		//spring security verifica si el usuario se encuentra en la BD
		auth.userDetailsService(userServiceImpl);
		
		//spring security comprueba si el password coincide con el cifrado de la BD
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override //método de autorización
	protected void configure(HttpSecurity http) throws Exception 
	{
		http.authorizeRequests()
		    .antMatchers("/","/index").permitAll()
		    .antMatchers("/user/**").access("hasRole('USER')")
		    .antMatchers("/admin/**","/registration").access("hasRole('ADMIN')")
		    .antMatchers("/dba/**").access("hasRole('DBA') and hasRole('ADMIN')");
		
		http.authorizeRequests().and()
		    .formLogin()
		    .loginPage("/login")
		    .successHandler(customSuccessHandler); //después de logearse redireccionarse a su URL correspondiente
		
			//se define la URL para un login personalizado http://localhost:8090/company/login
		    //.usernameParameter("txtUsername").passwordParameter("txtPassword"); //se define los parámetros de entrada para el login
		
		//intercepta solicitudes sin permiso por su rol
		//se va redirigir a la URL http://localhost:8090/company/access_denied
		http.authorizeRequests().and()
		    .exceptionHandling().accessDeniedPage("/access_denied"); 
		
		//por defecto ya viene activado la protección CSRF
		http.authorizeRequests().and()
		    .csrf();
	}
	
	@Bean//bean cifrado
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}//codigo de cifrado de constraseña
	
	@Bean//bean proveedor
	public DaoAuthenticationProvider authenticationProvider() 
	{
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userServiceImpl);
		
		return provider;
	}
}













