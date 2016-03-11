package edu.lsnu.service;

import java.util.List;
import java.util.Map;

import edu.lsnu.base.DaoSupport;
import edu.lsnu.domain.PageBean;

public interface StatisticalService extends DaoSupport<Object>{

	List<Map<String,Object>> basePsnNum(int grade);

	List<Map<String, Object>> baseStudent(int grade);

	PageBean baseStudent(int currentPage, int pageSize, int grade);

}
