package com.lb.service;

import java.util.List;

import com.lb.model.CourseInfo;
import com.lb.model.StudentClass;
import com.lb.model.StudentInfo;

/**
 * @author LiBing
 * @usage	学生服务接口
 */
public interface StudentService {

	/**
	 * @param studentId
	 * @return	获取学生课程列表
	 * @usage
	 */
	public List<CourseInfo> getStudentCourses(int studentId);

	/**
	 * @param studentId
	 * @param courseInfoId
	 * @return
	 * @usage	获取学生课程成绩
	 */
	public int getStudentCourseScore(int studentId, int courseInfoId);

	/**
	 * @param id
	 * @return
	 * @usage	获取学生详细信息
	 */
	public StudentInfo getStudentInfo(int id);

	/**
	 * @param info
	 * @usage	更新学生详细信息
	 */
	public void updateStudentInfo(StudentInfo info);

	/**
	 * @param id
	 * @param oldPassword
	 * @return
	 * @usage	检查学生密码是否匹配
	 */
	public boolean passwordMatches(int id, String oldPassword);

	/**
	 * @param id
	 * @param newPassword
	 * @usage	更新学生密码
	 */
	public void updatePassword(int id, String newPassword);

	/**
	 * @param id
	 * @param picPath
	 * @usage	更新学生照片信息
	 */
	public void updatePic(int id, String picPath);

	/**
	 * @param id
	 * @return
	 * @usage	获取学生照片信息
	 */
	public String getStudentPic(int id);

	/**
	 * @param id
	 * @return 判断学生是否分配到班级
	 */
	public boolean studentAllocatedToClass(int id);

	/**
	 * @param id
	 * @return 获取学生班级信息
	 */
	public StudentClass getStudentClassInfo(int id);

}
