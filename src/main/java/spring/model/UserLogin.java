package spring.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="admin_details")
public class UserLogin {
	@Id
	private String email;
	private String password;
	private String name;
	@Override
	public String toString() {
		return "UserLogin [email=" + email + ", password=" + password + ", name=" + name + "]";
	}
	
	

}
