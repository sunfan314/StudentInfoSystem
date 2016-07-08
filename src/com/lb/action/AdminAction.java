package com.lb.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;

import com.lb.model.Course;
import com.lb.model.CourseInfo;
import com.lb.model.StudentClass;
import com.lb.model.User;
import com.lb.service.AdminService;
import com.lb.service.StudentService;
import com.opensymphony.xwork2.Action;

/**
 * @author LiBing
 * @usage	处理管理员请求
 */
@Controller("adminAction")
public class AdminAction implements Action {
	@Resource
	private AdminService adminService;
	
	@Resource
	private StudentService studentService;
	
	private int id;

	private User user;

	private CourseInfo courseInfo;
	
	private JSONObject json;

	private JSONArray jsonArray;
	
	private User teacherToAdd;
	
	private User teacherToRemove;
	
	private StudentClass temp;
	
	private int classId;
	
	private String actionType;
	
	private String students;
	
	private String picPath;
	
	private String stuName;

	
	/**
	 * @return
	 * @throws Exception
	 * @usage	获取学生列表
	 */
	@Override
	public String execute() throws Exception {
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
			List<User> studentList = adminService.searchStudent(stuName);
			for (User user : studentList) {
				user.setStudentCourseInfoList(null);
				user.setTeacherCourseInfoList(null);
//				user.setStuClass(null);
			}
			java.util.Map<String, Object> map = new HashMap<String, Object>();
			map.put("rows", studentList);
			json = JSONObject.fromObject(map);
			return SUCCESS;
		}
		List<User> studentList = adminService.getStudentList();
		for (User user : studentList) {
			user.setStudentCourseInfoList(null);
			user.setTeacherCourseInfoList(null);
//			user.setStuClass(null);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", studentList);
		json = JSONObject.fromObject(map);
		return SUCCESS;
		
	}

	/**
	 * @return
	 * @throws Exception
	 * @usage	获取教师列表
	 */
	public String getTeachers() throws Exception {
		List<User> teacherList = adminService.getTeacherList();
		for (User user : teacherList) {
			user.setStudentCourseInfoList(null);
			user.setTeacherCourseInfoList(null);
//			user.setStuClass(null);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", teacherList);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return
	 * @throws Exception
	 * @usage	获取开课列表
	 */
	public String getCourseInfos() throws Exception {
		List<CourseInfo> courseInfoList = adminService.getCourseInfoList();
		for (CourseInfo courseInfo : courseInfoList) {
			for (User user : courseInfo.getTeacherList()) {
				user.setStudentCourseInfoList(null);
				user.setTeacherCourseInfoList(null);
//				user.setStuClass(null);
			}
			for (User user : courseInfo.getStudentList()) {
				user.setStudentCourseInfoList(null);
				user.setTeacherCourseInfoList(null);
//				user.setStuClass(null);
			}
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", courseInfoList);
		json = JSONObject.fromObject(map);
		return SUCCESS;

	}

	/**
	 * @return
	 * @throws Exception
	 * @usage	获取课程列表
	 */
	public String getCourseList() throws Exception {
		List<Course> courseList = adminService.getCourseList();
		jsonArray = JSONArray.fromObject(courseList);
		return SUCCESS;
	}
	
	/**
	 * @return
	 * @throws Exception
	 * @usage	获取某门课程教师列表
	 */
	public String getCourseTeacherList() throws Exception{
		List<User> courseTeacherList=adminService.getCourseTeacher(id);
		for (User user : courseTeacherList) {
			user.setStudentCourseInfoList(null);
			user.setTeacherCourseInfoList(null);
//			user.setStuClass(null);
		}
		jsonArray = JSONArray.fromObject(courseTeacherList);
		return SUCCESS;
	}
	
	/**
	 * @return
	 * @throws Exception
	 * @usage	获取可分配给某课程的教师列表（不教授该课程的教师列表）
	 */
	public String getAvailableTeachers() throws Exception {
		List<User> teacherList=adminService.getTeacherList();
		List<User> courseTeacherList=adminService.getCourseTeacher(id);
		List<User> list=new ArrayList<User>();
		for (User user : teacherList) {
			boolean isCourseTeacher=false;
			for (User user2 : courseTeacherList) {
				if(user.getId()==user2.getId()){
					isCourseTeacher=true;
				}
			}
			if(!isCourseTeacher){
				list.add(user);
			}
		}
		for (User user : list) {
			user.setStudentCourseInfoList(null);
			user.setTeacherCourseInfoList(null);
//			user.setStuClass(null);
		}
		jsonArray = JSONArray.fromObject(list);
		return SUCCESS;
	}
	
	/**
	 * @return	获取班级列表
	 * @throws Exception
	 */
	public String getClassList() throws Exception{
		List<StudentClass> classList=adminService.getClassList();
		jsonArray = JSONArray.fromObject(classList);
		return SUCCESS;
	}
	
	/**
	 * @return	获取班级学生列表
	 * @throws Exception
	 */
	public String getClassStudents() throws Exception{
		List<User> classStudents=new ArrayList<User>();
		if(actionType.equals("delete")){
			classStudents=adminService.getClassStudents(classId);
		}else if(actionType.equals("add")){
			classStudents=adminService.getOtherClassStudents(classId);
		}
		for (User user : classStudents) {
			user.setStudentCourseInfoList(null);
			user.setTeacherCourseInfoList(null);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows",classStudents );
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return 界面跳转至班级学生界面
	 * @throws Exception
	 */
	public String classStudentList() throws Exception{
		return SUCCESS;
	}
	
	/**
	 * @return 界面跳转至学生信息页面
	 * @throws Exception
	 */
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
	 * @usage	新增学生
	 */
	public String addStudent() throws Exception {
		adminService.addStudent(user);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return
	 * @throws Exception
	 * @usage	新增教师
	 */
	public String addTeacher() throws Exception {
		adminService.addTeacher(user);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return
	 * @throws Exception
	 * @usage	新增课程
	 */
	public String addCourseInfo() throws Exception {
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		Course course = adminService.getCourseById(courseInfo.getCourse()
				.getCourseId());
		courseInfo.setCourse(course);
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		if (year > courseInfo.getYear()) {
			map.put("success", false);
		} else {
			adminService.addCourseInfo(courseInfo);
			map.put("success", true);
		}
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return
	 * @throws Exception
	 * @usage	为课程添加教师
	 */
	public String addTeacherToCourse() throws Exception {
		adminService.addTeacherToCourseInfo(courseInfo.getId(),teacherToAdd.getId());
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return	
	 * @throws Exception
	 * @usage	新增班级信息
	 */
	public String addClass() throws Exception{
		adminService.addClass(temp);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return	添加班级学生
	 * @throws Exception
	 */
	public String addClassStudents() throws Exception{
		List<Integer> list = this.getStudentIdList(students);
		for (int studentId : list) {
			adminService.addClassStudent(classId, studentId);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return
	 * @throws Exception
	 * @usage	更新学生信息
	 */
	public String updateStudent() throws Exception {
		adminService.updateStudent(user);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return
	 * @throws Exception
	 * @usage	更新教师信息
	 */
	public String updateTeacher() throws Exception {
		adminService.updateTeacher(user);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return
	 * @throws Exception
	 * @usage	更新课程信息
	 */
	public String updateCourseInfo() throws Exception {
		Course course = adminService.getCourseById(courseInfo.getCourse()
				.getCourseId());
		courseInfo.setCourse(course);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		if (year > courseInfo.getYear()) {
			map.put("success", false);
		} else {
			adminService.updateCourseInfo(courseInfo);
			map.put("success", true);
		}
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return
	 * @throws Exception
	 * @usage	更新班级信息
	 */
	public String updateClass() throws Exception{
		adminService.updateClass(temp);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;

	}

	/**
	 * @return
	 * @throws Exception
	 * @usage	删除用户
	 */
	public String deleteUser() throws Exception {
		adminService.deleteUser(id);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	

	/**
	 * @return
	 * @throws Exception
	 * @usage	删除开设的课程
	 */
	public String deleteCourseInfo() throws Exception {
		adminService.deleteCourseInfo(id);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return
	 * @throws Exception
	 * @usage	删除课程任课教师
	 */
	public String deleteTeacherFromCourse() throws Exception {
		adminService.deleteTeacherFromCourse(courseInfo.getId(),teacherToRemove.getId());
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return	删除班级信息
	 * @throws Exception
	 */
	public String deleteClass() throws Exception{
		adminService.deleteClass(id);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	public String deleteClassStudents() throws Exception{
		List<Integer> list = this.getStudentIdList(students);
		for (int studentId : list) {
			adminService.deleteCourseStudent(classId, studentId);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CourseInfo getCourseInfo() {
		return courseInfo;
	}

	public void setCourseInfo(CourseInfo courseInfo) {
		this.courseInfo = courseInfo;
	}

	public StudentClass getTemp() {
		return temp;
	}

	public void setTemp(StudentClass temp) {
		this.temp = temp;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public User getTeacherToAdd() {
		return teacherToAdd;
	}

	public void setTeacherToAdd(User teacherToAdd) {
		this.teacherToAdd = teacherToAdd;
	}

	public User getTeacherToRemove() {
		return teacherToRemove;
	}

	public void setTeacherToRemove(User teacherToRemove) {
		this.teacherToRemove = teacherToRemove;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getStudents() {
		return students;
	}

	public void setStudents(String students) {
		this.students = students;
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
