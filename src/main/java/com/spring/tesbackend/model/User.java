package com.spring.tesbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="T_USER", uniqueConstraints=@UniqueConstraint(columnNames= {"USERID"}, name="UK_USER_ID"))
public class User {

	@Id
	@Column(name="USERID", length=50, nullable=false)
	private String userId;
	
	@Column(name="USERNAME", length=100, nullable=false)
	private String username;
	
	@Column(name="PASSWORD", length=100, nullable=false)
	private String password;
	
	@Column(name="STATUS")
	private Boolean status = false;

	public Boolean isStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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
	
}
