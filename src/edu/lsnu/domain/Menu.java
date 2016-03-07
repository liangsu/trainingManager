package edu.lsnu.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 菜单
 * @author liangsu
 *
 */
@Entity
@Table(name="menu")
public class Menu implements Serializable{
	/* 主键 */
	private int id;
	/* 上级菜单id */
	private int pid;
	/* 名称 */
	private String name;
	/* url地址 */
	private String url;
	/* css的class */
	private String cssClass;
	/* 排列序号 */
	private int theOrder;
	/* 关联菜单 */
	private Set<Role> roles = new HashSet<Role>();
	
	private List<Menu> children;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	@Transient
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	public int getTheOrder() {
		return theOrder;
	}
	public void setTheOrder(int theOrder) {
		this.theOrder = theOrder;
	}
	@ManyToMany(mappedBy="menus")
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
