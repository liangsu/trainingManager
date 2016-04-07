package edu.lsnu.service;

import edu.lsnu.base.DaoSupport;
import edu.lsnu.domain.PageBean;
import edu.lsnu.domain.Student;

public interface StudentService extends DaoSupport<Student>{
	/**
	 * 获取分页数据
	 * @param currentPage
	 * @param pageSize 
	 * @param grade 年级
	 * @param keyword 关键字，用于查询姓名
	 * @param sortField 排序字段
	 * @param asc 升序还是降序
	 * @return
	 */
	PageBean getPage(int currentPage,int pageSize, int grade, String keyword, String sortField, boolean asc);

	/**
	 * 选择实习实训基地（修改Student的tid,trainingType）
	 * @param model
	 */
	void chooseBase(Student model);
	
	/**
	 * 添加学生
	 * @param student
	 * @return
	 */
	String addStu(Student student);

	/**
	 * 编辑学生
	 * @param student
	 * @param oldId
	 * @return
	 */
	String editStu(Student student, int oldId);

	/**
	 * 学生评价基地
	 * @param student
	 * @return
	 */
	String evaluate(Student student);

	/**
	 * 分页获取学生对某个基地的评价
	 * @param currentPage
	 * @param pageSize
	 * @param tid 集中实习基地的id
	 */
	PageBean getPageBeanEvaluate(int currentPage, int pageSize, int tid);

}
