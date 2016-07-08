package com.lb.service;

import java.util.List;

import com.lb.model.User;

/**
 * @author LiBing
 * @usage	用户登录服务接口
 */
public interface UserService {

	/**
	 * @param user
	 * @return
	 * @usage	根据用户名和密码查询用户
	 */
	public User findUserByNameAndPassword(User user);
	
}
