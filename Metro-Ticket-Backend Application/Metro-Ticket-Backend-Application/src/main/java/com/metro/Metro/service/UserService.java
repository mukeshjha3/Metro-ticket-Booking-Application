package com.metro.Metro.service;

import com.metro.Metro.model.User;
import com.metro.Metro.payload.UserRequest;

public interface UserService {

	public User createUser(User user) ;
	
	public User updateUser(String email,User user);
	
	public void deleteUser(String email);
	
	public User getUser(String email);
	
	public User mapUserRequestToUser(UserRequest request);
}
