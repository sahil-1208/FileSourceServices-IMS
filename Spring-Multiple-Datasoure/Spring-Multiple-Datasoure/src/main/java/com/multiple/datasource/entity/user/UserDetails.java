package com.multiple.datasource.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;


import com.multiple.datasource.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserDetails {
	
	@Id
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "phone_number")
	private Long phone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
    
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;

}
