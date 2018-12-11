package com.imrancluster.savedream.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.imrancluster.savedream.entity.User;
import com.imrancluster.savedream.entity.UserType;
import com.imrancluster.savedream.user.SDUser;

public interface UserService extends UserDetailsService {

	User findByUserName(String userName);
	
	void save(SDUser crmUser);

	List<UserType> getUserTypes();
}
