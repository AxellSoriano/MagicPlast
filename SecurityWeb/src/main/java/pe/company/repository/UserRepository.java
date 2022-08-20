package pe.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.company.entity.UserVo;

public interface UserRepository extends JpaRepository<UserVo, Integer>
{
	@Query(value = "SELECT * FROM Users u WHERE u.status = ?1",nativeQuery = true)
	public abstract UserVo findByUsername(String username);

}
