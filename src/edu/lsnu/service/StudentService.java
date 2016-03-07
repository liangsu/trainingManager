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

}
