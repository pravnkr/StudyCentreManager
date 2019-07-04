package com.ignoubadhega.studycentremanager.dao;

import com.ignoubadhega.studycentremanager.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
