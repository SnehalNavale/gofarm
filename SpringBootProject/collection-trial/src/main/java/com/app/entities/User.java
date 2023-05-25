package com.app.entities;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends BaseEntity{
	private String firstName;
	private String lastName;
	@Column(name="user_email",unique = true)
	protected String email;
	@Column(name="user_password")
	protected String password;
	@Enumerated(EnumType.STRING)
	protected Role role;
}
