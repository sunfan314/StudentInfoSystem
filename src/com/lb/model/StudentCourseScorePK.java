package com.lb.model;

import java.io.Serializable;

/**
 * @author LiBing
 * @usage	StudentCourseScore主键类
 */
public class StudentCourseScorePK implements Serializable{

	private int courseInfoId;

	private int studentId;

	public StudentCourseScorePK() {
		super();
	}

	public StudentCourseScorePK(int courseInfoId, int studentId) {
		super();
		this.courseInfoId = courseInfoId;
		this.studentId = studentId;
	}

	public int getCourseInfoId() {
		return courseInfoId;
	}

	public void setCourseInfoId(int courseInfoId) {
		this.courseInfoId = courseInfoId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + courseInfoId;
		result = prime * result + studentId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentCourseScorePK other = (StudentCourseScorePK) obj;
		if (courseInfoId != other.courseInfoId)
			return false;
		if (studentId != other.studentId)
			return false;
		return true;
	}

}
