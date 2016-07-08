package com.lb.service;

import java.util.List;

import com.lb.model.StudentClass;
import com.lb.model.Course;
import com.lb.model.CourseInfo;
import com.lb.model.User;

/**
 * @author LiBing
 * @usage	管理员服务接口
 */
public interface AdminService {
	
	/**
	 * @return
	 * @usage	获取教师列表
	 */
	public List<User> getTeacherList();
	
	/**
	 * @return
	 * @usage	获取学生列表
	 */
	public List<User> getStudentList();
	
	/**
	 * @param id
	 * @return
	 * @usage	根据id获取
	 */
	public Course getCourseById(int id);
	
	/**
	 * @return	
	 * @usage	获取课程列表
	 */
	public List<Course> getCourseList();
	
	/**
	 * @return
	 * @usage	获取开设课程列表
	 */
	public List<CourseInfo> getCourseInfoList();
	
	/**
	 * @param courseInfoId
	 * @return
	 * @usage	获取课程教师列表
	 */
	public List<User> getCourseTeacher(int courseInfoId);
	
	/**
	 * @return
	 * @usage	获取班级列表
	 */
	public List<StudentClass> getClassList();
	
	/**
	 * @param classId
	 * @return	获取班级学生列表
	 */
	public List<User> getClassStudents(int classId);
	
	/**
	 * @param classId
	 * @return	获取不在班级中的学生列表
	 */
	public List<User> getOtherClassStudents(int classId);


	/**
	 * @param user
	 * @usage	增加学生用户
	 */
	public void addStudent(User user);
	
	/**
	 * @param user
	 * @usage	增加教师用户
	 */
	public void addTeacher(User user);
	
	/**
	 * @param courseInfo
	 * @usage	增加开设课程
	 */
	public void addCourseInfo(CourseInfo courseInfo);
	
	/**
	 * @param courseInfoId
	 * @param teacherId
	 * @usage	为开设课程分配新的教师
	 */
	public void addTeacherToCourseInfo(int courseInfoId, int teacherId);
	
	/**
	 * @param studentClass
	 * @usage	增加班级
	 */
	public void addClass(StudentClass studentClass);
	
	/**
	 * @param classId
	 * @param studentId
	 * @usage 添加班级学生
	 */
	public void addClassStudent(int classId, int studentId);
	
	/**
	 * @param user	
	 * @usage	更新学生用户信息
	 */
	public void updateStudent(User user);
	
	/**
	 * @param user
	 * @usage	更新教师用户信息
	 */
	public void updateTeacher(User user);
	
	/**
	 * @param courseInfo
	 * @usage	更新开设课程信息
	 */
	public void updateCourseInfo(CourseInfo courseInfo);
	
	/**
	 * @param temp
	 * @usage	更新班级信息
	 */
	public void updateClass(StudentClass temp);

	/**
	 * @param id
	 * @usage	删除用户
	 */
	public void deleteUser(int id);

	/**
	 * @param id
	 * @usage	删除开设课程
	 */
	public void deleteCourseInfo(int id);

	/**
	 * @param courseInfoId
	 * @param teacherId
	 * @usage	删除开设课程教师
	 */
	public void deleteTeacherFromCourse(int courseInfoId, int teacherId);

	/**
	 * @param id 班级id
	 * @usage	删除班级信息
	 */
	public void deleteClass(int id);

	/**
	 * @param classId
	 * @param studentId
	 * @usage 删除班级学生
	 */
	public void deleteCourseStudent(int classId, int studentId);

	/**
	 * @param id
	 * @return	判断学生详细信息是否存在
	 */
	public boolean studentInfoExists(int id);

	/**
	 * @param stuName
	 * @return	对学生进行模糊查找
	 */
	public List<User> searchStudent(String stuName);
	
	

	


}
