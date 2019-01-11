package com.bsk.mapper;

import java.util.List;

import com.bsk.entity.User;

/**
 * UserMapper接口
 * 
 * @author Lenovo
 *
 */
public interface UserMapper {
	/**
	 * 保存用户
	 * 
	 * @param user
	 */
	void saveUser(User user);

	/**
	 * 获取所有用户列表
	 * 
	 * @return
	 */
	List<User> getAllUser();

	/**
	 * 根据id查询用户信息
	 * 
	 * @param id
	 * @return
	 */
	User getUserById(Integer id);

	/**
	 * 更新用户名称
	 * 
	 * @param user
	 */
	void renameUser(User user);

	/**
	 * 根据id删除指定用户
	 * 
	 * @param id
	 */
	void deleteUserById(Integer id);
}
