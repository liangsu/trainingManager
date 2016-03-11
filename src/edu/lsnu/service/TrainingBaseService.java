package edu.lsnu.service;

import edu.lsnu.base.DaoSupport;
import edu.lsnu.domain.PageBean;
import edu.lsnu.domain.TrainingBase;

public interface TrainingBaseService extends DaoSupport<TrainingBase>{
	/**
	 * 按年度获取学校认可企业
	 * @param currentPage 第几页
	 * @param pageSize 每页条数
	 * @param grade 级别，如：2015级
	 * @param keyword 关键字，用于查询名称
	 * @return
	 */
	PageBean getYearDataByPage(int currentPage, int pageSize,int grade, String keyword);
	
	/**
	 * 获取开始的年份
	 */
	int getBeginYear();

	void updateNum(int id, int num);

	/**
	 * 保存
	 * @param trainingBase
	 * @return 保存成功返回长度为0的字符串，保存失败返回的字符串长度大于0
	 */
	String edit(TrainingBase trainingBase);

	/*List<TrainingBase> getByTrainingDate(TrainingDate trainingDate);*/
}
