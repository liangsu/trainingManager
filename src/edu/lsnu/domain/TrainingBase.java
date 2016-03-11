package edu.lsnu.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 集中实习实训基地
 * @author liangsu
 * 
 */
@Entity
@Table(name="training_base")
public class TrainingBase {
	/* 基地主键 */
	private int id;
	/* 基地名称 */
	private String name;
	/* 基地描述 */
	private String description;
	/* 地址 */
	private String address;
	/* 联系人姓名 */
	private String linkerName;
	/* 联系人电话 */
	private String linkerPhone;
	/* 去基地实习人数 */
	private int num;
	/* 所属级别（如：2015级） */
	private int grade;
	/* 图片 */
	private String img;
	/* 添加时间 */
	private Date addTime;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return super.toString() + " id:" + id + " name:" + name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getLinkerName() {
		return linkerName;
	}
	public void setLinkerName(String linkerName) {
		this.linkerName = linkerName;
	}
	public String getLinkerPhone() {
		return linkerPhone;
	}
	public void setLinkerPhone(String linkerPhone) {
		this.linkerPhone = linkerPhone;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
