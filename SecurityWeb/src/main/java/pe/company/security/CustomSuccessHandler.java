package pe.company.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler
{
	//objeto redireccionador
	private RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
	
	@Override //método que controla el éxito del logeo para ir a una URL específico
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
			throws IOException, ServletException 
	{
		//URL según el autenticado
		String targerURL=determineTargetUrl(authentication);
		
		//redireccionar a su URL correspondiente
		redirectStrategy.sendRedirect(request,response,targerURL);
	}
	
	//método que determina la url de un usuario autenticado
	protected String determineTargetUrl(Authentication authentication) 
	{
		String url=null;
		
		//colección de roles vacío
		Collection<String> roles=new ArrayList<>();
		
		//recorrer todos los permisos que tiene el usuario autenticado
		for(GrantedAuthority authority:authentication.getAuthorities()) {
			roles.add(authority.getAuthority());
		}
		
		//colección de roles cargado
			
		if(roles.contains("ROLE_DBA"))
			url="/dba";
		else if(roles.contains("ROLE_ADMIN"))
			url="/admin";
		else if(roles.contains("ROLE_USER"))
			url="/user";
		else
			url="/access_denied";
		
		return url;
	}
}











