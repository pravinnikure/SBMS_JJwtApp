package com.app.restapi.service.impl;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.restapi.entity.User;
import com.app.restapi.repositories.UserRepo;
import com.app.restapi.service.IUserService;


@Service

public class IUserServiceImpl implements IUserService , UserDetailsService 
{
	
	@Autowired
	private UserRepo repository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public Integer saveUser(User user) {
		
		String encPwd = encoder.encode(user.getPassword());
		user.setPassword(encPwd);
		return repository.save(user).getId(); 
	}

	@Override
	public User findByUsername(String username) 
	{
		Optional<User> opt =repository.findByUsername(username);
		if(opt.isPresent())
			return opt.get();
		else
			return null;
	}
	@Override
	public UserDetails loadUserByUsername(String username) 
				throws UsernameNotFoundException 
	{
		User user = findByUsername(username);
		
		if(user == null)
			throw new UsernameNotFoundException(username+"not exists ");
		
		
		List<GrantedAuthority> list = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role))
							.collect(Collectors.toList());
		
		//return object of spring security user
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(), 
				list);
	}

}
