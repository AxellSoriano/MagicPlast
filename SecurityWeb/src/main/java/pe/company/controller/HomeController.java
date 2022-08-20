package pe.company.controller;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pe.company.entity.UserVo;
import pe.company.service.RoleService;
import pe.company.service.UserService;

@Controller
public class HomeController 
{
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"/","/index"},method=RequestMethod.GET)
	public String index(Map map) 
	{
		map.put("var_username",username());
		map.put("var_authorities",authorities());
		
		return "index";
	}
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public String user() {
		return "user";
	}
	
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public String admin() {
		return "admin";
	}
	
	@RequestMapping(value="/dba",method=RequestMethod.GET)
	public String dba() {
		return "dba";
	}
	
	@RequestMapping(value="/access_denied",method=RequestMethod.GET)
	public String access_denied() {
		return "access_denied";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
   	public String logout(HttpServletRequest request,HttpServletResponse response) 
    {
		//llamar al usuario autenticado
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
  
        //si existe el usuario autenticado
        if(auth!=null)    
            new SecurityContextLogoutHandler().logout(request,response,auth); //cerrar sesión
  
        //redireccionar a login
        return "redirect:login?logout";
    }
	
	//método que devuelve el nombre del usuario autenticado
	public String username()
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		Object principal=auth.getPrincipal();
		
		if(principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		
		return principal.toString();
	}
	
	//método que devuelve los roles del usuario autenticado
	public Collection<? extends GrantedAuthority> authorities()
	{
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		return auth.getAuthorities();
	}
	
	@RequestMapping(value="/registration",method=RequestMethod.GET)
	public String registration_Get(Model model,Map map)
	{
		UserVo userModel=new UserVo();
		model.addAttribute("userVo",userModel);//userVo vacío
		
		map.put("bRoles",roleService.findAll());
		
		return "registration";
	}
	@RequestMapping(value="/registration",method=RequestMethod.POST)
	public String registration_POST(@Valid UserVo userVo,Errors errors,Map map)
	{
		if(errors.hasErrors())
		{
			map.put("bRoles",roleService.findAll());
			return "registration";
		}
		
		userService.insert(userVo);
		
		return "registration_succes";
	}
}














