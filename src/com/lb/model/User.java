package com.lb.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author LiBing
 * @usage	用户
 * id				用户id
 * username			用户名
 * password			密码
 * type				用户类型（0：管理员；1：教师；2：学生）
 * registerTime		注册时间
 * status			学生学籍状态
 * class			学生所在班级
 */
@Entity
@Table(name = "user")
public class User {
	@Id
	private int id;

	private String username;

	private String password;
	
	private String registerTime;

	private int type;
	
	private String status;

	/**
	 * 教师课程列表
	 */
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "course_teacher", joinColumns = @JoinColumn(name = "teacherId"), inverseJoinColumns = @JoinColumn(name = "courseInfoId")

	)
	private List<CourseInfo> teacherCourseInfoList;

	/**
	 * 学生课程列表
	 */
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "studentId"), inverseJoinColumns = @JoinColumn(name = "courseInfoId")

	)
	private List<CourseInfo> studentCourseInfoList;

	public User() {
		super();
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, int type) {
		super();
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CourseInfo> getTeacherCourseInfoList() {
		return teacherCourseInfoList;
	}

	public void setTeacherCourseInfoList(List<CourseInfo> teacherCourseInfoList) {
		this.teacherCourseInfoList = teacherCourseInfoList;
	}

	public List<CourseInfo> getStudentCourseInfoList() {
		return studentCourseInfoList;
	}

	public void setStudentCourseInfoList(List<CourseInfo> studentCourseInfoList) {
		this.studentCourseInfoList = studentCourseInfoList;
	}
	
	
	

}
