package ch.gibm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
// @NamedQuery(name="User.findUserByEmailPwd", query= "select u from User u where u.email = :email and u.password = :password")
@NamedQuery(name="User.findUserByEmailSaltedpwd", query= "select u from User u where u.email = :email and SUBSTRING(u.password, 17, 64) = SHA2(CONCAT(SUBSTRING(u.password, 1, 16), :password), 256)")
@NamedQuery(name="User.findUserByEmail", query="select u from User u where u.email = :email")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	//public static final String FIND_BY_EMAILPWD = "User.findUserByEmailPwd";
	public static final String FIND_BY_EMAILPWD = "User.findUserByEmailSaltedpwd";
	public static final String FIND_BY_EMAIL = "User.findUserByEmail";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private String email;

	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	public User() {
	}

	public User(String email, String password, Role role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			User user = (User) obj;
			return user.getId() == this.id;
		}
		return false;
	}

	@Override
	public String toString() {
		return getEmail();
	}
}
