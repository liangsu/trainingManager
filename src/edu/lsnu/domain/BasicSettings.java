package edu.lsnu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 基本设置，设置程纵向表，方便扩展
 * 如：
 * 1.今年实训开始时间（必须大于当前时间）
 * 2.今年实训结束时间（默认实训开始时间+1年，不能大于1年）
 * @author liangsu
 *
 */
@Entity
@Table(name="basic_settings")
public class BasicSettings {
	private int id;
	/* 所属类型 */
	private String type;
	/* 键,由于字段名特殊，数据库中存的是skey */
	private String key;
	/* 值,由于字段名特殊，数据库中存的是svalue */
	private String value;
	/* 描述 */
	private String description;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="skey")
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Column(name="svalue")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
