package com.lb.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

/**
 * @author ChenBing
 * 学生班级中间表
 * studentId		学生id
 * classId			班级id
 */
@Entity
@Table(name = "student_class")
public class Student_Class {
	@Id
	private int studentId;

	private int classId;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

}
