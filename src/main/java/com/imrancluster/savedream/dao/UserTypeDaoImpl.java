package com.imrancluster.savedream.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.imrancluster.savedream.entity.UserType;

@Repository
public class UserTypeDaoImpl implements UserTypeDao {

	private EntityManager entityManager;
	
	@Autowired
	public UserTypeDaoImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<UserType> getUserTypes() {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		List<UserType> userTypes = currentSession.createQuery("from UserType order by id", UserType.class).getResultList();
				
		return userTypes;
	}

	@Override
	public UserType getUserTypeById(long id) {
		
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		UserType userType = currentSession.get(UserType.class, id);
		
		return userType;
	}
}
