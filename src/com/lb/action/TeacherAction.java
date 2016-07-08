package com.lb.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lb.model.CourseInfo;
import com.lb.model.StudentCourseScore;
import com.lb.model.User;
import com.lb.service.AdminService;
import com.lb.service.StudentService;
import com.lb.service.TeacherService;
import com.opensymphony.xwork2.Action;


/**
 * @author LiBing
 * @usage	处理教师请求
 */
@Controller("teacherAction")
public class TeacherAction implements Action {
	@Resource
	private TeacherService teacherService;
	
	@Resource
	private AdminService adminService;
	
	@Resource
	private StudentService studentService;

	private int id;

	private JSONObject json;

	private String students;

	private String actionType;

	private int courseInfoId;
	
	private User student;
	
	private String data;
	
	private String picPath;
	
	private String stuName;

	/**
	 * @return
	 * @throws Exception
	 * @usage	界面跳转至课程学生管理界面
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	
	public String studentInfo() throws Exception{
		if(adminService.studentInfoExists(id)){
			picPath=studentService.getStudentPic(id);
			return SUCCESS;
		}
		return ERROR;
	}

	/**
	 * @return
	 * @throws Exception
	 * @usage	获取教师课程列表
	 */
	public String getTeacherCourses() throws Exception {
		List<CourseInfo> teacherCourseList = teacherService
				.getTeacherCourses(id);
		for (CourseInfo courseInfo : teacherCourseList) {
			courseInfo.setStudentList(null);
			courseInfo.setTeacherList(null);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", teacherCourseList);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return
	 * @throws Exception
	 * @usage	获取课程学生列表
	 */
	public String getCourseStudents() throws Exception{
		boolean vagueSearch=false;
		try {
			if(stuName!=null){
				vagueSearch=true;
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
			vagueSearch=false;
		}
		if(vagueSearch){
			List<User> resultList=new ArrayList<User>();
			List<User> studentList = adminService.searchStudent(stuName);
			List<User> temp=teacherService.getCourseStudents(courseInfoId);
			if(actionType.equals("delete")){
				for (User user : studentList) {
					for (User user1 : temp) {
						if(user.getUsername().equals(user1.getUsername())){
							resultList.add(user);
						}
					}
				}
			}else{
				for (User user : studentList) {
					boolean isCourseStudent=false;
					for (User user1 : temp) {
						if(user.getUsername().equals(user1.getUsername())){
							isCourseStudent=true;
						}
					}
					if(!isCourseStudent){
						resultList.add(user);
					}
				}
			}
			for (User user : resultList) {
				user.setStudentCourseInfoList(null);
				user.setTeacherCourseInfoList(null);
			}
			java.util.Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", resultList);
			json = JSONObject.fromObject(map);
			return SUCCESS;
		}
		if (actionType.equals("delete")) {
			List<User> courseStudentList=teacherService.getCourseStudents(courseInfoId);
			for (User user : courseStudentList) {
				user.setStudentCourseInfoList(null);
				user.setTeacherCourseInfoList(null);
			}
			java.util.Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", courseStudentList);
			json = JSONObject.fromObject(map);
		}
		if (actionType.equals("add")) {
			List<User> studentList=adminService.getStudentList();
			List<User> courseStudentList=teacherService.getCourseStudents(courseInfoId);
			List<User> otherStudentList=new ArrayList<User>();
			for (User user : studentList) {
				boolean studentInCourse=false;
				for (User user2 : courseStudentList) {
					if(user.getId()==user2.getId()){
						studentInCourse=true;
					}
				}
				if(!studentInCourse){
					otherStudentList.add(user);
				}
			}
			for (User user : otherStudentList) {
				user.setStudentCourseInfoList(null);
				user.setTeacherCourseInfoList(null);
			}
			java.util.Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", otherStudentList);
			json = JSONObject.fromObject(map);
		}
		return SUCCESS;
	}
	
	/**
	 * @return
	 * @throws Exception
	 * @usage	获取学生课程分数信息列表
	 */
	public String getCourseStudentScore() throws Exception{
		List<StudentCourseScore> list=teacherService.getStudentCourseScoreList(id);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return
	 * @throws Exception
	 * @usage	为课程增加学生
	 */
	public String addCourseStudents() throws Exception {
		List<Integer> list = this.getStudentIdList(students);
		for (int studentId : list) {
			teacherService.addCourseStudent(courseInfoId, studentId);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return
	 * @throws Exception
	 * @usage	删除课程学生
	 */
	public String deleteCourseStudents() throws Exception {
		List<Integer> list = this.getStudentIdList(students);
		for (int studentId : list) {
			teacherService.deleteCourseStudent(courseInfoId, studentId);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return
	 * @throws Exception
	 * @usage	更新学生课程得分
	 */
	public String updateStudentCourseScore() throws Exception{
		JSONArray array = JSONArray.fromObject(data);
		for (Object object : array) {
			JSONObject json = (JSONObject) object;
			StudentCourseScore scs = (StudentCourseScore) JSONObject.toBean(json, StudentCourseScore.class);
			teacherService.updateStudentCourseScore(scs);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @param list
	 * @return
	 * @usage	解析界面传来的字符串，从中获取学生id列表
	 */
	private List<Integer> getStudentIdList(String list) {
		List<Integer> studentIdList = new ArrayList<Integer>();
		list = list.substring(0, list.length() - 1);
		String[] idList = list.split(":");
		for (int i = 0; i < idList.length; i++) {
			int id = Integer.parseInt(idList[i]);
			studentIdList.add(id);
		}
		return studentIdList;
	}
	
	/**
	 * @return
	 * @throws Exception
	 * @usage	界面跳转至学生成绩管理界面
	 */
	public String studentScoreManage() throws Exception{
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public String getStudents() {
		return students;
	}

	public void setStudents(String students) {
		this.students = students;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public int getCourseInfoId() {
		return courseInfoId;
	}

	public void setCourseInfoId(int courseInfoId) {
		this.courseInfoId = courseInfoId;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	
}
