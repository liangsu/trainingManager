package edu.lsnu.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
//@Table(name="attachFile")
public class AttachFile {
	/* 主键 */
	private int id;
	/* 上传文件名 */
	private String name;
	/* 更改后的真实文件名 */
	private String realName;
	/* 文件类型 */
	private String type;
	/* 上传时间 */
	private Date updloadTime;
	
	// ---
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getUpdloadTime() {
		return updloadTime;
	}
	public void setUpdloadTime(Date updloadTime) {
		this.updloadTime = updloadTime;
	}
}
