package edu.lsnu.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 角色
 * @author liangsu
 *
 */
@Entity
@Table(name="role")
public class Role {
	/*主键*/
	private int id;
	/*角色名称*/
	private String name;
	/* 本角色关联的菜单 多对多*/
	private Set<Menu> menus = new HashSet<Menu>();
	
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
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="role_menu",
	joinColumns={@JoinColumn(name="rid",referencedColumnName="id")},
	inverseJoinColumns={@JoinColumn(name="mid",referencedColumnName="id")})
	public Set<Menu> getMenus() {
		return menus;
	}
	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	
}
