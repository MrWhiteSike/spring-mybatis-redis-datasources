package com.bsk.dao.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.bsk.dao.T_userDao;
import com.bsk.entity.T_user;
import com.bsk.mapper.T_userMapper;
@Repository
public class T_UserDaodImpl implements T_userDao{
	@Resource
	private T_userMapper tUserMapper;

	@Override
	public T_user findUserByUsername(String userName) {
		return tUserMapper.findUserByUsername(userName);
	}

	@Override
	public Set<String> findRoles(String userName) {
		return tUserMapper.findRoles(userName);
	}

	@Override
	public Set<String> findPermissions(String userName) {
		return tUserMapper.findPermissions(userName);
	}

}
