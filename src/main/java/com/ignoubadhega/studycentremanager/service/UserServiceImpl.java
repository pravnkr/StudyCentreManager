package com.ignoubadhega.studycentremanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ignoubadhega.studycentremanager.dao.RoleDao;
import com.ignoubadhega.studycentremanager.dao.UserDao;
import com.ignoubadhega.studycentremanager.dto.StudyCentreUser;
import com.ignoubadhega.studycentremanager.entity.Role;
import com.ignoubadhega.studycentremanager.entity.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	// need to inject user dao
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	
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
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

	@Override
	@Transactional
	public void save(StudyCentreUser theUser) {
		User user = new User();
		 // assign user details to the user object
		user.setUserName(theUser.getUserName());
		user.setPassword(passwordEncoder.encode(theUser.getPassword()));
		user.setFirstName(theUser.getFirstName());
		user.setLastName(theUser.getLastName());
		user.setEmail(theUser.getEmail());

		List<Role> roles = Arrays.stream(theUser.getRoles()).map(roleDao::findRoleByName).collect(Collectors.toList());
		System.out.println("roles " + roles);
		// give user default role of "employee"
		user.setRoles(roles);

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
    
}
