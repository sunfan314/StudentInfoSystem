package com.lb.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author LiBing
 * @usage	课程model
 * courseId			课程id
 * courseName		课程名
 */
@Entity
@Table(name="course")
public class Course {
	@Id
	private int courseId;
	
	private String courseName;
	
	public Course(){
		super();
	}
	
	public Course(int id,String name){
		super();
		this.courseId=id;
		this.courseName=name;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	

}
