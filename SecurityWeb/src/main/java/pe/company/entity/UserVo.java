package pe.company.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import pe.company.validator.Condori;
import pe.company.validator.Condori.TextType;

@Entity
@Table(name="users")
public class UserVo implements Serializable
{
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;	
	
	@Column(unique=true,nullable=false)
	@Condori(value=TextType.USERNAME,message="UserName Incorrecto")
	private String username;
	
	@Column(nullable=false)
	@Condori(value=TextType.PASSWORD,message="Password Incorrecto,rango 4-16 caracteres")
	private String password;
	
	@Column
	@Condori(value=TextType.LETTERS,message="Solo Se Permite Letras")
	private String nombre;
	
	@Column
	@Condori(value=TextType.LETTERS,message="Solo Se Permite Letras")
	private String apellidos;
	
	@Column(unique=true,nullable=false)
	@Email(message="Email incorrecto (no es una dirección de correo bien formada).")
	@NotEmpty(message="Ingrese Email Correcto")
	private String email;
	
	@Column(unique=true)
	@NotEmpty(message="Ingrese Celular")
	private Long celular;
	
	@Column(nullable=false)
	private String state=StateType.ACTIVE.name();
	
	//lado:owner
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="users_roles",
			   joinColumns=@JoinColumn(name="user_id",nullable=false,
			       foreignKey=@ForeignKey(foreignKeyDefinition="foreign key(user_id) references users(user_id)")),
			   inverseJoinColumns=@JoinColumn(name="role_id",nullable=false,
			       foreignKey=@ForeignKey(foreignKeyDefinition="foreign key(role_id) references roles(role_id)")))
	@NotEmpty(message="Seleccione un Rol")
	private Set<RoleVo> itemsRole=new HashSet<>();
	
	public UserVo() {		
	}

	public UserVo(Integer userId, String username, String password, String nombre, String apellidos, String email,
			Long celular, String state) {		
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.celular = celular;
		this.state = state;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCelular() {
		return celular;
	}

	public void setCelular(Long celular) {
		this.celular = celular;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<RoleVo> getItemsRole() {
		return itemsRole;
	}

	public void setItemsRole(Set<RoleVo> itemsRole) {
		this.itemsRole = itemsRole;
	}
}
