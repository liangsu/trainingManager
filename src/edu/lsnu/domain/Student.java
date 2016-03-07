package edu.lsnu.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
/**
 * 学生
 * @author liangsu
 *
 */
@Entity
@Table(name="student")
public class Student {
	/* 学号 */
	private Integer id;
	/* 学生姓名 */
	private String username;
	/* 密码 */
	private String password;
	/* 班级 */
	private String className;
	/* 电子邮件 */
	private String email;
	/* 家庭地址 */
	private String address;
	/* 电话号码 */
	private String phone;
	/* 评价 */
	private Integer estimate;
	/* 年级 */
	private Integer grade;
	/* 学院 */
	private Integer institute;
	/* 状态：是否允许登陆，1：允许、0：禁止 */
	private Integer state;
	/* 实习方式，0:集中实习，1：自主实习 */
	private int trainingType;
	/* 所在基地的id，如果是集中实习对应TrainingBase,如果是自主实习对应 */
	private int tid;
	/* 添加时间 */
	private Date addTime;
	
	/*实习基地*/
	private TrainingBase trainingBase;
	private FreeTrainingBase freeTrainingBase;
	
	@Id 
	@GeneratedValue(generator = "paymentableGenerator") 
	@GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
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
	@Column(length=11)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getEstimate() {
		return estimate;
	}
	public void setEstimate(Integer estimate) {
		this.estimate = estimate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Integer getInstitute() {
		return institute;
	}
	public void setInstitute(Integer institute) {
		this.institute = institute;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public int getTrainingType() {
		return trainingType;
	}
	public void setTrainingType(int trainingType) {
		this.trainingType = trainingType;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	@Transient
	public TrainingBase getTrainingBase() {
		return trainingBase;
	}
	public void setTrainingBase(TrainingBase trainingBase) {
		this.trainingBase = trainingBase;
	}
	@Transient
	public FreeTrainingBase getFreeTrainingBase() {
		return freeTrainingBase;
	}
	public void setFreeTrainingBase(FreeTrainingBase freeTrainingBase) {
		this.freeTrainingBase = freeTrainingBase;
	}
	
}
