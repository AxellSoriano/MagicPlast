package pe.company.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class RoleVo implements Serializable 
{
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer roleId;
	
	@Column(unique=true,nullable=false)
	private String type=RoleType.USER.name();
	
	//lado:inverse
	@ManyToMany(mappedBy="itemsRole", fetch=FetchType.EAGER) //referencia a la otra colección
	private Set<UserVo> itemsUser=new HashSet<>();
	//lectura ansiosa la propiedad itemUser ya puede ser usada
	public RoleVo() {		
	}

	public RoleVo(Integer roleId, String type) {		
		this.roleId = roleId;
		this.type = type;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<UserVo> getItemsUser() {
		return itemsUser;
	}

	public void setItemsUser(Set<UserVo> itemsUser) {
		this.itemsUser = itemsUser;
	}
}
