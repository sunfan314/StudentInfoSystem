package com.lb.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author LiBing
 * @usage	学生与课程映射的中间表中含有附加字段--成绩，所以需要中间类进行处理
 * courseInfoId			课程id
 * studentId			学生id
 * score				课程成绩
 */
@Entity
@Table(name = "course_student")
@IdClass(com.lb.model.StudentCourseScorePK.class)
public class StudentCourseScore {
	@Id
	private int courseInfoId;
	@Id
	private int studentId;

	@Transient
	private String studentName;
	
	private int score;

	public StudentCourseScore() {
		super();
	}

	public StudentCourseScore(int courseInfoId, int studentId, int score) {
		this.courseInfoId = courseInfoId;
		this.studentId = studentId;
		this.score = score;
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

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
