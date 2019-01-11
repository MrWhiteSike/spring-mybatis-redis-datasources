package com.bsk.service.ssmone;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bsk.dao.T_userDao;
import com.bsk.entity.T_user;
import com.bsk.service.T_UserService;
@Service
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class T_UserServiceImpl implements T_UserService{
	@Resource
	private T_userDao tUserDao;

	@Override
	public T_user findUserByUsername(String userName) {
		return tUserDao.findUserByUsername(userName);
	}

	@Override
	public Set<String> findRoles(String userName) {
		return tUserDao.findRoles(userName);
	}

	@Override
	public Set<String> findPermissions(String userName) {
		return tUserDao.findPermissions(userName);
	}

}
