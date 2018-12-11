package com.imrancluster.savedream.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imrancluster.savedream.dao.RoleDao;
import com.imrancluster.savedream.dao.UserDao;
import com.imrancluster.savedream.dao.UserTypeDao;
import com.imrancluster.savedream.entity.Role;
import com.imrancluster.savedream.entity.User;
import com.imrancluster.savedream.entity.UserType;
import com.imrancluster.savedream.user.SDUser;

@Service
public class UserServiceImpl implements UserService {
	
	// need to inject user dao
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserTypeDao userTypeDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}
	
	@Override
	@Transactional
	public void save(SDUser sdUser) {
		User user = new User();
		 // assign user details to the user object
		user.setUserName(sdUser.getUserName());
		user.setPassword(passwordEncoder.encode(sdUser.getPassword()));
		user.setName(sdUser.getName());
		user.setMobile(sdUser.getMobile());
		user.setEmail(sdUser.getEmail());
		
		UserType userType = userTypeDao.getUserTypeById(sdUser.getUserTypeId());
		
		user.setUserType(userType);

		// give user default role of "member"
		String role = "ROLE_" + userType.getType().toUpperCase();
		if (roleDao.findRoleByName(role) != null) {
			user.setRoles(Arrays.asList(roleDao.findRoleByName(role)));
		} else {
			user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_MEMBER")));
		}

		 // save user in the database
		userDao.save(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public List<UserType> getUserTypes() {
		
		return userTypeDao.getUserTypes();
	}

}
