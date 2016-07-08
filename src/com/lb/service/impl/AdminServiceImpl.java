package com.lb.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lb.dao.BaseDao;
import com.lb.model.StudentClass;
import com.lb.model.Course;
import com.lb.model.CourseInfo;
import com.lb.model.StudentInfo;
import com.lb.model.Student_Class;
import com.lb.model.User;
import com.lb.service.AdminService;
import com.lb.service.TeacherService;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	@Resource
	private BaseDao<User> userDao;
	
	@Resource
	private BaseDao<Course> courseDao;
	
	@Resource
	private BaseDao<CourseInfo> courseInfoDao;
	
	@Resource
	private BaseDao<StudentClass> classDao;
	
	@Resource
	private BaseDao<Student_Class> studentClassDao;
	
	@Resource
	private BaseDao<StudentInfo> studentInfoDao;
	
	@Resource
	private TeacherService teacherService;

	@Override
	public List<User> getTeacherList() {
		// TODO Auto-generated method stub
		return userDao.find("from User where type = 1");
	}

	@Override
	public List<User> getStudentList() {
		// TODO Auto-generated method stub
		return userDao.find("from User where type = 2");
	}
	

	@Override
	public Course getCourseById(int id) {
		// TODO Auto-generated method stub
		return courseDao.get(Course.class, id);
	}

	@Override
	public List<Course> getCourseList() {
		// TODO Auto-generated method stub
		return courseDao.find("from Course");
	}
	
	@Override
	public List<CourseInfo> getCourseInfoList() {
		// TODO Auto-generated method stub
		return courseInfoDao.find("from CourseInfo");
	}
	
	@Override
	public List<User> getCourseTeacher(int courseInfoId) {
		// TODO Auto-generated method stub
//		CourseInfo courseInfo=courseInfoDao.get(CourseInfo.class, courseInfoId);
//		return courseInfo.getTeacherList();
		List<User> list=new ArrayList<User>();
		List<User> teacherList=userDao.find("from User where type = 1");
		CourseInfo courseInfo=courseInfoDao.get(CourseInfo.class, courseInfoId);
		for (User user :teacherList) {
			if(user.getTeacherCourseInfoList().contains(courseInfo)){
				list.add(user);
			}
		}
		return list;
	}
	

	@Override
	public List<StudentClass> getClassList() {
		// TODO Auto-generated method stub
		return classDao.find("from StudentClass");
	}
	

	@Override
	public List<User> getClassStudents(int classId) {
		// TODO Auto-generated method stub
		List<Object> param=new ArrayList<Object>();
		param.add(classId);
		List<Student_Class> list=studentClassDao.find("from Student_Class where classId = ?",param);
		List<User> userList=new ArrayList<User>();
		for (Student_Class student_Class : list) {
			userList.add(userDao.get(User.class, student_Class.getStudentId()));
		}
		return userList;
	}
	
	@Override
	public List<User> getOtherClassStudents(int classId) {
		// TODO Auto-generated method stub
		List<Object> param=new ArrayList<Object>();
		param.add(classId);
		List<Student_Class> list=studentClassDao.find("from Student_Class where classId = ?",param);
		List<User> classStudentList=new ArrayList<User>();
		for (Student_Class student_Class : list) {
			classStudentList.add(userDao.get(User.class, student_Class.getStudentId()));
		}
		List<User> userList=userDao.find("from User where type = 2");
		userList.removeAll(classStudentList);
		return userList;
	}


	@Override
	public void addStudent(User user) {
		// TODO Auto-generated method stub
		user.setType(2);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		user.setRegisterTime(df.format(new Date()));
		userDao.save(user);
	}

	@Override
	public void addTeacher(User user) {
		// TODO Auto-generated method stub
		user.setType(1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		user.setRegisterTime(df.format(new Date()));
		userDao.save(user);
		
	}

	@Override
	public void addCourseInfo(CourseInfo courseInfo) {
		// TODO Auto-generated method stub
		courseInfoDao.save(courseInfo);
		
	}
	
	@Override
	public void addTeacherToCourseInfo(int courseInfoId, int teacherId) {
		// TODO Auto-generated method stub
		CourseInfo courseInfo=courseInfoDao.get(CourseInfo.class, courseInfoId);
		User teacher=userDao.get(User.class, teacherId);
		teacher.getTeacherCourseInfoList().add(courseInfo);
		userDao.saveOrUpdate(teacher);
	}
	
	@Override
	public void addClass(StudentClass studentClass) {
		// TODO Auto-generated method stub
		classDao.save(studentClass);
	}
	
	@Override
	public void addClassStudent(int classId, int studentId) {
		// TODO Auto-generated method stub
		Student_Class sc=new Student_Class();
		sc.setClassId(classId);
		sc.setStudentId(studentId);
		studentClassDao.saveOrUpdate(sc);
	}

	@Override
	public void updateStudent(User user) {
		// TODO Auto-generated method stub
		user.setType(2);
		userDao.update(user);
		
	}

	@Override
	public void updateTeacher(User user) {
		// TODO Auto-generated method stub
		user.setType(1);
		userDao.update(user);
		
	}

	@Override
	public void updateCourseInfo(CourseInfo courseInfo) {
		// TODO Auto-generated method stub
		courseInfoDao.update(courseInfo);
	}
	
	@Override
	public void updateClass(StudentClass temp) {
		// TODO Auto-generated method stub
		classDao.update(temp);
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		User user=userDao.get(User.class, id);
		userDao.delete(user);
		
	}

	@Override
	public void deleteCourseInfo(int id) {
		// TODO Auto-generated method stub
		List<Object> param=new ArrayList<Object>();
		param.add(id);
		courseInfoDao.executeHql("delete CourseInfo where id = ?", param);
		
	}

	@Override
	public void deleteTeacherFromCourse(int courseInfoId, int teacherId) {
		// TODO Auto-generated method stub
		CourseInfo courseInfo=courseInfoDao.get(CourseInfo.class, courseInfoId);
		User teacher=userDao.get(User.class, teacherId);
		teacher.getTeacherCourseInfoList().remove(courseInfo);
		userDao.saveOrUpdate(teacher);
	}

	@Override
	public void deleteClass(int id) {
		// TODO Auto-generated method stub
		List<Object> param=new ArrayList<Object>();
		param.add(id);
		List<Student_Class> list=studentClassDao.find("from Student_Class where classId = ?",param);
		for (Student_Class student_Class : list) {
			//删除关联的班级学生
			studentClassDao.delete(student_Class);
		}
		StudentClass studentClass=classDao.get(StudentClass.class, id);
		classDao.delete(studentClass);
	}

	@Override
	public void deleteCourseStudent(int classId, int studentId) {
		// TODO Auto-generated method stub
		Student_Class sc=new Student_Class();
		sc.setClassId(classId);
		sc.setStudentId(studentId);
		studentClassDao.delete(sc);
		
	}

	@Override
	public boolean studentInfoExists(int id) {
		// TODO Auto-generated method stub
		List<Object> param=new ArrayList<Object>();
		param.add(id);
		List<StudentInfo> info=studentInfoDao.find("from StudentInfo where id = ?",param);
		if(info.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public List<User> searchStudent(String stuName) {
		// TODO Auto-generated method stub
		String temp="%"+stuName+"%";
		List<Object> param=new ArrayList<Object>();
		param.add(temp);
		List<User> list=userDao.find("from User where type=2 and username like ?",param);
		return list;
	}

	
}
