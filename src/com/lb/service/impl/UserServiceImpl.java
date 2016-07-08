package com.lb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lb.dao.BaseDao;
import com.lb.model.User;
import com.lb.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	private BaseDao<User> baseDao;

	@Override
	public User findUserByNameAndPassword(User user) {
		List<Object> params=new ArrayList<Object>();
		params.add(user.getUsername());
		params.add(user.getPassword());
		return baseDao.get("from User where username = ? and password = ?", params);
		
	}
    
}
