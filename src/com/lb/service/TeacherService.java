package com.lb.service;

import java.util.List;

import com.lb.model.CourseInfo;
import com.lb.model.StudentCourseScore;
import com.lb.model.User;

/**
 * @author LiBing
 * @usage	教师服务接口
 */
public interface TeacherService {
	
	/**
	 * @param teacherId
	 * @return
	 * @usage	获取教师课程列表
	 */
	public List<CourseInfo> getTeacherCourses(int teacherId);

	/**
	 * @param courseInfoId
	 * @return
	 * @usage	获取课程学生列表
	 */
	public List<User> getCourseStudents(int courseInfoId);

	/**
	 * @param courseInfoId
	 * @param studentId
	 * @usage	为课程增加学生
	 */
	public void addCourseStudent(int courseInfoId, int studentId);

	/**
	 * @param courseInfoId
	 * @param studentId
	 * @usage	删除课程学生
	 */
	public void deleteCourseStudent(int courseInfoId, int studentId);

	/**
	 * @param courseInfoId
	 * @return
	 * @usage	获取课程学生成绩列表信息
	 */
	public List<StudentCourseScore> getStudentCourseScoreList(int courseInfoId);

	/**
	 * @param scs
	 * @usage	更新学生课程成绩
	 */
	public void updateStudentCourseScore(StudentCourseScore scs);

}
