package com.ignoubadhega.studycentremanager.dao;

import com.ignoubadhega.studycentremanager.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
    void save(User user);

    User findByEmail(String email);
    
}
