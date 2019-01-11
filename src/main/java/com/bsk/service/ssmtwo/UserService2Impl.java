package com.bsk.service.ssmtwo;
 
import java.util.List;
 
import javax.annotation.Resource;
 
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bsk.dao.UserDao;
import com.bsk.entity.User;
import com.bsk.service.UserService2;
 
/**
 * UserService实现类
 * @author
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class UserService2Impl implements UserService2 {
	@Resource
	private UserDao mUserDao;
	
	@Override
	@CacheEvict(value = { "saveUser"}, allEntries = true)
	public void saveUser(String name, String sex, Integer age) {
		User user = new User(name, sex, age);
		mUserDao.saveUser(user);
	}
 
	@Override
//	@Cacheable("getAllUser")
	public List<User> getAllUser() {
		return mUserDao.getAllUser();
	}
 
	@Override
	@Cacheable("getUserById")
	public User getUserById(Integer id) {
		return mUserDao.getById(id);
	}
 
	@Override
	@CacheEvict(value = { "getAllUser", "getUserById" }, allEntries = true)
	public void renameUser(String name, Integer id) {
		mUserDao.rename(new User(id, name));
	}
 
	@Override
	@CacheEvict(value = { "getAllUser", "getUserById" }, allEntries = true)
	public void deleteUserById(Integer id) {
		mUserDao.deleteById(id);
	}
 
}
