package com.lb.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author LiBing
 * @usage	开设课程model
 * id			课程id
 * course		课程内容
 * year			开设年份
 * term 		开设学期
 */
@Entity
@Table(name = "course_info")
public class CourseInfo {
	@Id
	private int id;
	@OneToOne
	@JoinColumn(name = "courseId", unique = true)
	private Course course;

	private int year;

	private int term;

	@Transient
	private int score;

	/**
	 *课程教师列表 
	 */
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "teacherCourseInfoList",cascade = CascadeType.ALL)
	private List<User> teacherList;

	/**
	 * 课程学生列表
	 */
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "studentCourseInfoList",cascade = CascadeType.ALL)
	private List<User> studentList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCourseId() {
		return course.getCourseId();
	}

	public String getCourseName() {
		return course.getCourseName();
	}

	public List<User> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<User> teacherList) {
		this.teacherList = teacherList;
	}

	public List<User> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<User> studentList) {
		this.studentList = studentList;
	}

	public String getTeachers() {
		String str = "";
		try {
			for (User user : teacherList) {
				str += user.getUsername() + " ";
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		return str;
	}

}
