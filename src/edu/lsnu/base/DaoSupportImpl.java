package edu.lsnu.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import edu.lsnu.domain.PageBean;
import edu.lsnu.utils.QueryHelper;

@Transactional
public abstract class DaoSupportImpl<T> implements DaoSupport<T> {

	@Resource
	protected SessionFactory sessionFactory;

	Class<T> clazz;

	protected Log log;
	
	public DaoSupportImpl() {
		Type t = this.getClass().getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			clazz = (Class<T>) ((ParameterizedType) t).getActualTypeArguments()[0];
			System.out.println("clazz-------" + clazz.getSimpleName());
		}
		log = LogFactory.getLog(this.getClass());
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T get(Serializable id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@Override
	public void delete(Serializable id) {
		T obj = get(id);
		if (obj != null)
			getSession().delete(obj);
	}

	@Override
	public int add(T entity) {
		return (Integer) getSession().save(entity);
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	public Number getCount(String hql, Object... params) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		setQueryParameters(query, params);
		return (Number) query.uniqueResult();
	}
	
	@Override
	public Long getLongCount(String hql, Object... params) {
		return getCount(hql, params).longValue();
	}
	
	@Override
	public Integer getIntegerCount(String hql, Object... params) {
		return getCount(hql, params).intValue();
	}
	
	@Override
	public List<T> getList(String hql, Object... params) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		setQueryParameters(query, params);
		return query.list();
	}
	
	@Override
	public List<T> getList(String hql,int currentPage, int pageSize,
			Object... params) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		setQueryParameters(query, params);
		query.setFirstResult((currentPage - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	@Override
	public PageBean getByPage(int currentPage, int pageSize,QueryHelper queryHelper) {
		// 查询分页list
		List recordList = this.getList(queryHelper.getListHql(), currentPage, pageSize, queryHelper.getParameters().toArray());
		// 查询总记录条数recordCount
		Long recordCount = this.getLongCount(queryHelper.getCountHql(), queryHelper.getParameters().toArray());
		
		return new PageBean(currentPage, pageSize, recordCount.intValue(),recordList);
	}
	
	@Override
	public PageBean getByPage(String hql, int currentPage, int pageSize,
			Object... params) {
		//1.获取list数据
		List recordList = getList(hql, currentPage, pageSize, params);
		
		//2.获取总记录数量
		//2.1拼接hql的select count(*) 语句
		if(hql != null){
			hql = hql.replaceAll("select", "SELECT");
			hql = hql.replaceAll("from", "FROM");
			if(hql.startsWith("SELECT")){
				hql = "SELECT COUNT(*) " + hql.substring(hql.indexOf("FROM"));
			}else{
				hql = "SELECT COUNT(*) " + hql;
			}
		}
		//2.2查询数据库
		int recordCount = getCount(hql, params).intValue();
		
		//3.返回分页信息
		return new PageBean(currentPage, pageSize, recordCount, recordList);
	}
	
	/**
	 * 设置参数
	 * 
	 * @param query
	 * @param params
	 */
	private void setQueryParameters(Query query, Object... params) {
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
	}
}
