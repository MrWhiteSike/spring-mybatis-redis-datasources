package com.bsk.mapper;

import java.util.Set;

import com.bsk.entity.T_user;

public interface T_userMapper {
	T_user findUserByUsername(String userName);
	Set<String> findRoles(String userName);
	Set<String> findPermissions(String userName);
	
}
