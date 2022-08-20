package pe.company.service;



import java.util.Collection;

import pe.company.entity.RoleVo;

public interface RoleService 
{
	public abstract RoleVo findById(Integer role_id);
	public abstract Collection<RoleVo> findAll();
	
	

}
