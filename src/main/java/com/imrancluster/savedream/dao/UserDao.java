package com.imrancluster.savedream.dao;

import org.springframework.stereotype.Repository;

import com.imrancluster.savedream.entity.User;

@Repository
public interface UserDao {

    User findByUserName(String userName);
    
    void save(User user);
    
}
