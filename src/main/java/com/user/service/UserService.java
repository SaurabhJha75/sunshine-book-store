package com.user.service;
 
import com.user.model.UserDtls;
 
public interface UserService {
 
	public UserDtls createUser(UserDtls user);
	public boolean checkEmail(String email);
}