package com.imrancluster.savedream.dao;

import org.springframework.stereotype.Repository;

import com.imrancluster.savedream.entity.Role;

@Repository
public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
