package com.lb.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.lb.model.User;
import com.lb.service.UserService;
import com.opensymphony.xwork2.Action;

/**
 * @author LiBing
 * @usage	处理用户登录
 */
@Controller("userAction")
@SuppressWarnings("all")
public class UserAction extends BaseAction{
	private int id;
	
	private User user;

	@Resource
	private UserService userService;

	/**
	 * @return
	 * @throws Exception
	 * @usage	用户登录
	 */
	@Override
	public String execute() throws Exception {
		User u=userService.findUserByNameAndPassword(user);
		try {
			u.setStudentCourseInfoList(null);
			u.setTeacherCourseInfoList(null);
			user.setId(u.getId());
			return String.valueOf(u.getType());
		} catch (NullPointerException e) {
			// TODO: handle exception
			return ERROR;
		}
	}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
