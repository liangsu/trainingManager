package edu.lsnu.utils;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import edu.lsnu.base.DaoSupport;
import edu.lsnu.domain.PageBean;

public class QueryHelper {

	/* from子句 */
//	private String fromClause ="";
	private StringBuffer fromClause = new StringBuffer();
	private String whereClause ="";//where子句
	private String orderbyClause ="";//order by子句
	
	private List<Object> parameters = new ArrayList<Object>();//参数列表
	
	/**
	 * 通过构造函数生成from子句
	 * @param clazz
	 * @param alias
	 */
	public QueryHelper(Class clazz, String alias){
//		fromClause = "FROM " + clazz.getSimpleName() + " " + alias + " ";
		fromClause.append("FROM ");
		fromClause.append(clazz.getSimpleName());
		fromClause.append(" ");
		fromClause.append(alias);
		fromClause.append(" ");
	}

	
	/**
	 * 拼接where子句
	 * @param condition
	 */
	public QueryHelper addCondition(String condition ,Object... params){
		if(whereClause.length() == 0 ){
			whereClause += "WHERE "+condition; 
		}else{
			whereClause += " AND "+condition;
		}
		
		if(params != null){
			for(Object param : params){
				parameters.add(param);
			}
		}
		
		return this;
	}
	
	/**
	 * 是否拼接该where子句
	 * 
	 * @param append
	 * @param condition
	 * @param params
	 * @return
	 */
	public QueryHelper addCondition(boolean append,String condition ,Object... params){
		if(append){
			addCondition(condition, params);
		}
		
		return this;
	}
	
	/**
	 * 拼接order by 子句
	 * @param orderProperty
	 */
	public QueryHelper addOrderProperty(String orderProperty , boolean asc){
		if(orderbyClause.length() ==0){
			orderbyClause += " ORDER BY "+orderProperty + (asc ? " ASC" : " DESC");
		}else{
			orderbyClause += ","+orderProperty + (asc ? " ASC" : " DESC");
		}
		
		return this;
	}
	
	/**
	 * 是否拼接该order by子句
	 * 
	 * @param append
	 * @param orderProperty
	 * @param asc
	 * @return
	 */
	public QueryHelper addOrderProperty(boolean append,String orderProperty , boolean asc){
		if(append){
			addOrderProperty(orderProperty, asc);
		}
		return this;
	}
	
	/**
	 * 获取查询list集合的语句
	 * @return
	 */
	public String getListHql(){
		return fromClause + whereClause + orderbyClause;
	}
	
	/**
	 * 获取统计集合个数的语句
	 */
	public String getCountHql(){
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}
	
	/**
	 * 获取参数列表
	 * 
	 * @return
	 */
	public List<Object> getParameters() {
		return parameters;
	}
	
	/**
	 * 获取分页信息的pageBean
	 * 
	 * @param service
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageBean getPageBean(DaoSupport<?> service,int currentPage,int pageSize){
		return service.getByPage(currentPage, pageSize, this);
	}

	/**
	 * 获取pageBean并放入值栈
	 * @param service
	 * @param currentPage
	 * @param pageSize
	 */
	public void preparePageBean(DaoSupport<?> service,int currentPage,int pageSize){
		PageBean pageBean = service.getByPage(currentPage, pageSize, this);
//		ActionContext.getContext().getValueStack().push(pageBean);
		ActionContext.getContext().put("pageBean", pageBean);
	}
}
