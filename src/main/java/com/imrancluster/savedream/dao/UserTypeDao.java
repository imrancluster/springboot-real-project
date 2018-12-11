package com.imrancluster.savedream.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.imrancluster.savedream.entity.UserType;

@Repository
public interface UserTypeDao {

	List<UserType> getUserTypes();
	
	UserType getUserTypeById(long id);
}
