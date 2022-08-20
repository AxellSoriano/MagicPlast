package pe.company.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import pe.company.entity.RoleVo;
import pe.company.repository.RoleRepository;

public class RoleServiceImpl implements RoleService
{
	@Autowired
	private RoleRepository repository;

	@Override
	@Transactional(readOnly=true)
	public Collection<RoleVo> findAll() {
		
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public RoleVo findById(Integer role_id) {
		
		return repository.findById(role_id).orElse(null);
	}
	/*
	@Override
	@Transactional(readOnly=true)
	public RoleVo findByType(String type) {
		
		return repository.findByType(type);
	}*/

}
