package edu.lsnu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 管理员
 * @author liangsu
 *
 */
@Entity
@Table(name="adminuser")
public class AdminUser {
	/* 主键 */
	private int id;
	/* 用户名 */
	private String username;
	/* 密码 */
	private String password;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
}
