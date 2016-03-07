package edu.lsnu.service;

import java.util.List;

import edu.lsnu.base.DaoSupport;
import edu.lsnu.domain.PageBean;
import edu.lsnu.domain.TrainingBase;
import edu.lsnu.vo.TrainingDate;

public interface TrainingBaseService extends DaoSupport<TrainingBase>{
	/**
	 * 按年度获取学校认可企业
	 * @param currentPage 第几页
	 * @param pageSize 每页条数
	 * @param year 年份 
	 * @param keyword 关键字，用于查询名称
	 * @return
	 */
	PageBean getYearDataByPage(int currentPage, int pageSize,int year, String keyword);
	
	/**
	 * 获取开始的年份
	 */
	int getBeginYear();

	/*List<TrainingBase> getByTrainingDate(TrainingDate trainingDate);*/
}
