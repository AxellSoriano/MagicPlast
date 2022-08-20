package pe.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;


import pe.company.entity.RoleVo;

public interface RoleRepository extends JpaRepository<RoleVo, Integer>
{
	/*@Query(value="select * from roles where type = ?1",nativeQuery = true)
	public abstract RoleVo findByType(String type);*/

}
