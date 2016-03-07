package edu.lsnu.base;

import java.io.Serializable;
import java.util.List;

import edu.lsnu.domain.PageBean;
import edu.lsnu.utils.QueryHelper;

public interface DaoSupport<T> {

	T get(Serializable id);
	
	void delete(T entity);
	
	void delete(Serializable id);
	
	int add(T entity);

	void update(T entity);
	
	Number getCount(String hql,Object...params);
	
	Integer getIntegerCount(String hql,Object...params);
	
	Long getLongCount(String hql,Object...params);
	
	List<T> getList(String hql,Object...params);
	
	List<T> getList(String hql,int currentPage,int pageSize,Object...params);
	
	PageBean getByPage(int currentPage,int pageSize,QueryHelper queryHelper);
	
	PageBean getByPage(String hql, int currentPage, int pageSize,Object... params);
}
