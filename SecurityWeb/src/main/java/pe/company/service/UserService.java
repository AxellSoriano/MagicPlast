package pe.company.service;

import pe.company.entity.UserVo;

public interface UserService 
{
	public abstract UserVo findById(Integer userId);
	public abstract UserVo findByUsername(String username);
	
	public abstract void insert(UserVo user);

}
