package com.lb.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lb.dao.BaseDao;
import com.lb.model.CourseInfo;
import com.lb.model.StudentClass;
import com.lb.model.StudentCourseScore;
import com.lb.model.StudentCourseScorePK;
import com.lb.model.StudentInfo;
import com.lb.model.Student_Class;
import com.lb.model.User;
import com.lb.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService{
	@Resource
	private BaseDao<User> userDao;
	
	@Resource
	private BaseDao<CourseInfo> courseInfoDao;
	
	@Resource
	private BaseDao<StudentCourseScore> courseScoreDao;
	
	@Resource
	private BaseDao<StudentInfo> studentInfoDao;
	
	@Resource
	private BaseDao<Student_Class> studentClassDao;
	
	@Resource
	private BaseDao<StudentClass> classDao;

	@Override
	public List<CourseInfo> getStudentCourses(int studentId) {
		// TODO Auto-generated method stub
		User user=userDao.get(User.class, studentId);
		return user.getStudentCourseInfoList();
	}

	@Override
	public int getStudentCourseScore(int studentId, int courseInfoId) {
		// TODO Auto-generated method stub
		StudentCourseScorePK pk=new StudentCourseScorePK(courseInfoId, studentId);
		StudentCourseScore scs=courseScoreDao.get(StudentCourseScore.class, pk);
		return scs.getScore();
	}

	@Override
	public StudentInfo getStudentInfo(int id) {
		// TODO Auto-generated method stub
		User student=userDao.get(User.class, id);
		List<Object> param=new ArrayList<Object>();
		param.add(id);
		List<StudentInfo> list=studentInfoDao.find("from StudentInfo where id = ?",param);
		if(list.size()==0){
			StudentInfo temp=new StudentInfo();
			temp.setId(student.getId());
			temp.setStuId(student.getUsername());
			temp.setAddress("尚未填写");
			temp.setAdmissionDay("尚未填写");
			temp.setBirthday("尚未填写");
			temp.setDepartment("尚未填写");
			temp.setEmail("尚未填写");
			temp.setExamCardNum("尚未填写");
			temp.setIdentityID("尚未填写");
			temp.setMajor("尚未填写");
			temp.setMajorYear("尚未填写");
			temp.setNation("尚未填写");
			temp.setNativePlace("尚未填写");
			temp.setPhone("尚未填写");
			temp.setPic("尚未填写");
			temp.setPoliticalState("尚未填写");
			temp.setQQ("尚未填写");
			temp.setSex("尚未填写");
			temp.setStuName("尚未填写");
			return temp;
		}
		return list.get(0);
		
	}

	@Override
	public void updateStudentInfo(StudentInfo info) {
		// TODO Auto-generated method stub
		studentInfoDao.saveOrUpdate(info);
		
	}

	@Override
	public boolean passwordMatches(int id, String oldPassword) {
		// TODO Auto-generated method stub
		User user=userDao.get(User.class, id);
		if(user.getPassword().equals(oldPassword)){
			return true;
		}
		return false;
	}

	@Override
	public void updatePassword(int id, String newPassword) {
		// TODO Auto-generated method stub
		User user=userDao.get(User.class, id);
		user.setPassword(newPassword);
		userDao.update(user);
		
	}

	@Override
	public void updatePic(int id, String picPath) {
		// TODO Auto-generated method stub
		StudentInfo studentInfo=getStudentInfo(id);
		studentInfo.setPic(picPath);
		studentInfoDao.saveOrUpdate(studentInfo);
		
	}

	@Override
	public String getStudentPic(int id) {
		// TODO Auto-generated method stub
		StudentInfo studentInfo=studentInfoDao.get(StudentInfo.class, id);
		return studentInfo.getPic();
	}

	@Override
	public boolean studentAllocatedToClass(int id) {
		// TODO Auto-generated method stub
		List<Object> param=new ArrayList<Object>();
		param.add(id);
		List<Student_Class> sc=studentClassDao.find("from Student_Class where studentId = ?",param);
		if(sc.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public StudentClass getStudentClassInfo(int id) {
		// TODO Auto-generated method stub
		Student_Class sc=studentClassDao.get(Student_Class.class, id);
		StudentClass classInfo=classDao.get(StudentClass.class, sc.getClassId());
		return classInfo;
	}

}
