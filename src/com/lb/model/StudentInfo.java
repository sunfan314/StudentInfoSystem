package com.lb.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author LiBing
 * @usage	学生详细信息
 * id				用户id
 * stuId			学生用户名
 * stuName			学生姓名
 * sex				性别
 * nation			民族	
 * birthday			生日
 * nativePlace		籍贯
 * identityID		身份证号
 * phone			电话号码
 * pic				图片
 * admissionDay		入学日期
 * politicalState	政治面貌
 * examCardNum		准考证号
 * department		院系
 * major			专业
 * majorYear		学制
 * QQ				QQ
 * email			邮箱
 * address			地址
 */
@Entity
@Table(name = "student_info")
public class StudentInfo {
	@Id
	private int id;

	private String stuId;

	private String stuName;

	private String sex;

	private String nation;

	private String birthday;

	private String nativePlace;

	private String identityID;

	private String phone;
	
	private String pic;
	
	private String admissionDay;
	
	private String politicalState;
	
	private String examCardNum;
	
	private String department;
	
	private String major;
	
	private String majorYear;
	
	private String QQ;
	
	private String email;
	
	private String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getIdentityID() {
		return identityID;
	}

	public void setIdentityID(String identityID) {
		this.identityID = identityID;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getAdmissionDay() {
		return admissionDay;
	}

	public void setAdmissionDay(String admissionDay) {
		this.admissionDay = admissionDay;
	}

	public String getPoliticalState() {
		return politicalState;
	}

	public void setPoliticalState(String politicalState) {
		this.politicalState = politicalState;
	}

	public String getExamCardNum() {
		return examCardNum;
	}

	public void setExamCardNum(String examCardNum) {
		this.examCardNum = examCardNum;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getMajorYear() {
		return majorYear;
	}

	public void setMajorYear(String majorYear) {
		this.majorYear = majorYear;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
}
