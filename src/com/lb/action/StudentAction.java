package com.lb.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.lb.model.CourseInfo;
import com.lb.model.Property;
import com.lb.model.StudentClass;
import com.lb.model.StudentInfo;
import com.lb.service.StudentService;
import com.opensymphony.xwork2.Action;

/**
 * @author LiBing
 * @usage	处理学生请求
 */
@Controller("studentAction")
public class StudentAction implements Action {
	@Resource
	private StudentService studentService;

	private int id;

	private JSONObject json;

	private StudentInfo info;

	private String oldPassword;

	private String newPassword;
	
	private File pic;
	
	private String picPath;
		
	/**
	 * @return
	 * @throws Exception
	 * @usage	跳转至学生课程界面
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	/**
	 * @return
	 * @throws Exception
	 * @usage	跳转至学生信息界面
	 */
	public String studentInfo() throws Exception {
		return SUCCESS;
	}
	
	/**
	 * @return	
	 * @throws Exception
	 * @usage 跳转至学生班级信息页面
	 */
	public String studentClassInfo() throws Exception{
		if(studentService.studentAllocatedToClass(id)){
			return SUCCESS;
		}
		return ERROR;
	}

	/**
	 * @return
	 * @throws Exception
	 * @usage	跳转至修改密码界面
	 */
	public String changePassword() throws Exception {
		return SUCCESS;
	}

	/**
	 * @return
	 * @throws Exception
	 * @usage	获取学生课程列表
	 */
	public String getStudentCourses() throws Exception {
		List<CourseInfo> courseInfoList = studentService.getStudentCourses(id);
		for (CourseInfo c : courseInfoList) {
			c.setScore(studentService.getStudentCourseScore(id, c.getId()));
			c.setStudentList(null);
			c.setTeacherList(null);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", courseInfoList);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return
	 * @throws Exception
	 * @usage	获取学生详细信息
	 */
	public String getStudentInfo() throws Exception {
		List<Property> list = new ArrayList<Property>();
		String group1 = "账号信息";
		String group2 = "学籍信息";
		String group3 = "个人信息";
		String group4 = "联系方式";
		String editor1 = "can not be edited";
		StudentInfo studentInfo = studentService.getStudentInfo(id);
		list.add(new Property("用户Id", studentInfo.getId(), group1, editor1));
		list.add(new Property("学号", studentInfo.getStuId(), group1, editor1));
		list.add(new Property("准考证号", studentInfo.getExamCardNum(), group2, editor1));
		list.add(new Property("院系", studentInfo.getDepartment(), group2, editor1));
		list.add(new Property("专业", studentInfo.getMajor(), group2, editor1));
		list.add(new Property("学制", studentInfo.getMajorYear(), group2, editor1));
		list.add(new Property("姓名", studentInfo.getStuName(), group3, editor1));
		list.add(new Property("性别", studentInfo.getSex(), group3, editor1));
		list.add(new Property("民族", studentInfo.getNation(), group3, editor1));
		list.add(new Property("出生日期", studentInfo.getBirthday(), group3,editor1));
		list.add(new Property("籍贯", studentInfo.getNativePlace(), group3,editor1));
		list.add(new Property("身份证号", studentInfo.getIdentityID(), group3,editor1));
		list.add(new Property("入学日期", studentInfo.getAdmissionDay(), group3, editor1));
		list.add(new Property("政治面貌", studentInfo.getPoliticalState(), group3, editor1));
		list.add(new Property("电话号码", studentInfo.getPhone(), group4, editor1));
		list.add(new Property("QQ", studentInfo.getQQ(), group4, editor1));
		list.add(new Property("邮箱", studentInfo.getEmail(), group4, editor1));
		list.add(new Property("地址", studentInfo.getAddress(), group4, editor1));
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return
	 * @throws Exception
	 * @usage 获取学生班级信息
	 */
	public String getStudentClassInfo() throws Exception{
		List<Property> list = new ArrayList<Property>();
		String group1 = "班级信息";
		String editor1 = "can not be edited";
		StudentClass classInfo=studentService.getStudentClassInfo(id);
		list.add(new Property("班级编号", classInfo.getId(), group1, editor1));
		list.add(new Property("班级名", classInfo.getClassName(), group1, editor1));
		list.add(new Property("班级QQ群",classInfo.getQQGroup(), group1, editor1));
		list.add(new Property("班级标语", classInfo.getSlogan(), group1, editor1));
		list.add(new Property("班级院系", classInfo.getDepartment(), group1, editor1));
		list.add(new Property("班主任老师", classInfo.getMainTeacher(), group1, editor1));
		list.add(new Property("老师联系方式",classInfo.getTeacherPhone(), group1, editor1));		
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return	学生详细信息
	 * @throws Exception
	 */
	public String getJsonStudentInfo() throws Exception{
		StudentInfo studentInfo=studentService.getStudentInfo(id);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", studentInfo);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return
	 * @throws Exception
	 * @usage	更新学生详细信息
	 */
	public String updateStudentInfo() throws Exception {
		studentService.updateStudentInfo(info);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return
	 * @throws Exception
	 * @usage	更新用户密码
	 */
	public String updatePassword() throws Exception {
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		if(studentService.passwordMatches(id,oldPassword)){
			studentService.updatePassword(id,newPassword);
			map.put("success", true);
		}
		else{
			map.put("success", false);
		}
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return
	 * @throws Exception
	 * @usage	学生上传个人图片
	 */
	public String uploadFile() throws Exception{
		if (pic == null) {
			java.util.Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", false);
			json = JSONObject.fromObject(map);

		} else {
			String fileFolderPath=ServletActionContext.getServletContext().getRealPath("/picture/studentPic")+"/"+id;
			File fileFolder=new File(fileFolderPath);
			if(!fileFolder.exists()){
				fileFolder.mkdirs();
			}
			try {
				for(File f:fileFolder.listFiles()){
					f.delete();
				}
			} catch (NullPointerException e) {
				// TODO: handle exception
			}
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String timeStr = sdf.format(date);
			String filePath = fileFolderPath+"/"+id+timeStr;;
			File saveFile = new File(filePath);
			FileUtils.copyFile(pic, saveFile);
			studentService.updatePic(id,id+timeStr);
			java.util.Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", true);
			json = JSONObject.fromObject(map);
		}
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

	public StudentInfo getInfo() {
		return info;
	}

	public void setInfo(StudentInfo info) {
		this.info = info;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}

	public String getPicPath() {
		try {
			picPath=studentService.getStudentPic(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

}
