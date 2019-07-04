package com.ignoubadhega.studycentremanager.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ignoubadhega.studycentremanager.dto.StudyCentreUser;
import com.ignoubadhega.studycentremanager.entity.User;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(StudyCentreUser crmUser);

    User findByEmail(String email);
}
