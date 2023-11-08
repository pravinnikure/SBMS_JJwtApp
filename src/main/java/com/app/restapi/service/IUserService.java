package com.app.restapi.service;

import com.app.restapi.entity.User;

public interface IUserService 
{
	
	 Integer saveUser(User use);
	 User findByUsername(String username);	
}
