package com.lb.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author ChenBing
 * 班级
 * id				班级id
 * className  		班级名
 * QQGroup			班级qq群
 * solgan			班级标语
 * department		班级院系
 * mainTeacher		班级班主任
 * teacherPhone		老师联系方式
 */
@Entity
@Table(name="stuClass")
public class StudentClass {
	@Id
	private int id;
	
	private String className;
	
	private String QQGroup;
	
	private String slogan;
	
	private String department;
	
	private String mainTeacher;
	
	private String teacherPhone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getQQGroup() {
		return QQGroup;
	}

	public void setQQGroup(String qQGroup) {
		QQGroup = qQGroup;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMainTeacher() {
		return mainTeacher;
	}

	public void setMainTeacher(String mainTeacher) {
		this.mainTeacher = mainTeacher;
	}

	public String getTeacherPhone() {
		return teacherPhone;
	}

	public void setTeacherPhone(String teacherPhone) {
		this.teacherPhone = teacherPhone;
	}
	

}
