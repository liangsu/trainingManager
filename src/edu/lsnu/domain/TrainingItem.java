package edu.lsnu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 基地存在年份
 * @author liangsu
 *
 */
@Entity
@Table(name="base_exist")
public class TrainingItem {
	/* 主键 */
	private int id;
	/* 对应基地 */
	private TrainingBase trainingBase;
	
	@Id @GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@ManyToOne @JoinColumn(name="tid")
	public TrainingBase getTrainingBase() {
		return trainingBase;
	}
	public void setTrainingBase(TrainingBase trainingBase) {
		this.trainingBase = trainingBase;
	}
}
