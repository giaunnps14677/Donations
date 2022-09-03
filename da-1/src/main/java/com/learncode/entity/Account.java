package com.learncode.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ACCOUNT")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_ACCOUNT")
	private Integer accountId;
	
	@Column(name="EMAIL", length=50)
	private String email;
	
	@Column(name="USERNAME", length=20)
	private String username;
	
	@Column(name="PASSWORD", length=20)
	private String password;
	
	@Column(name="ROLE")
	private Integer role = 0;

	public Account() {
		super();
	}

	public Account(Integer accountId, String email, String username, String password, Integer role) {
		super();
		this.accountId = accountId;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	
}
