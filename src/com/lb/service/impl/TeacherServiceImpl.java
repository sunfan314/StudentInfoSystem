package com.lb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lb.dao.BaseDao;
import com.lb.model.Course;
import com.lb.model.CourseInfo;
import com.lb.model.StudentCourseScore;
import com.lb.model.User;
import com.lb.service.TeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{
	@Resource
	private BaseDao<User> userDao;
	
	@Resource
	private BaseDao<CourseInfo> courseInfoDao;
	
	@Resource
	private BaseDao<StudentCourseScore> courseScoreDao;
	
	@Override
	public List<CourseInfo> getTeacherCourses(int teacherId) {
		// TODO Auto-generated method stub
		User teacher=userDao.get(User.class, teacherId);
		return teacher.getTeacherCourseInfoList();
	}

	@Override
	public List<User> getCourseStudents(int courseInfoId) {
		// TODO Auto-generated method stub
//		CourseInfo courseInfo=courseInfoDao.get(CourseInfo.class, courseInfoId);
//		return courseInfo.getStudentList();
		List<User> list=new ArrayList<User>();
		List<User> studentList=userDao.find("from User where type = 2");
		CourseInfo courseInfo=courseInfoDao.get(CourseInfo.class, courseInfoId);
		for (User user : studentList) {
			if(user.getStudentCourseInfoList().contains(courseInfo)){
				list.add(user);
			}
		}
		return list;
	}

	@Override
	public void addCourseStudent(int courseInfoId, int studentId) {
		// TODO Auto-generated method stub
		CourseInfo courseInfo=courseInfoDao.get(CourseInfo.class, courseInfoId);
		User student=userDao.get(User.class, studentId);
		student.getStudentCourseInfoList().add(courseInfo);
		
	}

	@Override
	public void deleteCourseStudent(int courseInfoId, int studentId) {
		// TODO Auto-generated method stub
		CourseInfo courseInfo=courseInfoDao.get(CourseInfo.class, courseInfoId);
		User student=userDao.get(User.class, studentId);
		student.getStudentCourseInfoList().remove(courseInfo);
		
	}

	@Override
	public List<StudentCourseScore> getStudentCourseScoreList(int courseInfoId) {
		// TODO Auto-generated method stub
		List<Object> param=new ArrayList<Object>();
		param.add(courseInfoId);
		List<StudentCourseScore> list=courseScoreDao.find("from StudentCourseScore where courseInfoId = ?", param);
		for (StudentCourseScore s : list) {
			User student=userDao.get(User.class, s.getStudentId());
			s.setStudentName(student.getUsername());
		}
		return list;
	}

	@Override
	public void updateStudentCourseScore(StudentCourseScore scs) {
		// TODO Auto-generated method stub
		courseScoreDao.update(scs);
		
	}

}
