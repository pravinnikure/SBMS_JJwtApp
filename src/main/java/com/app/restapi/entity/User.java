package com.app.restapi.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//To map class with DB and store user data

@Entity
@Table(name = "UserTable")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer id;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="UserName")
	private String username;
	
	@Column(name="Password")
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;

	
}
