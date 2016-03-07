package edu.lsnu.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import edu.lsnu.service.AdminUserService;
import edu.lsnu.service.BasicSettingsService;
import edu.lsnu.service.FreeTrainingBaseService;
import edu.lsnu.service.LoginService;
import edu.lsnu.service.MenuService;
import edu.lsnu.service.RoleService;
import edu.lsnu.service.StudentService;
import edu.lsnu.service.TrainingBaseService;
import edu.lsnu.service.TrainingItemService;
import edu.lsnu.utils.Code;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

	// ========================== DriveModel 的支持 ==========================
	protected T model;
	
	public BaseAction(){
		Type type = this.getClass().getGenericSuperclass();
		if(type instanceof ParameterizedType){
			Class<T> clazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
			try {
				model = clazz.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	@Override
	public T getModel() {
		return model;
	}

	// ========================== Service的声明 ==========================
	@Resource
	protected AdminUserService adminUserService;
	@Resource
	protected TrainingBaseService trainingBaseService;
	@Resource
	protected TrainingItemService trainingItemService;
	@Resource
	protected StudentService studentService;
	@Resource
	protected MenuService menuService;
	@Resource
	protected RoleService roleService;
	@Resource
	protected LoginService loginService;
	@Resource
	protected BasicSettingsService basicSettingsService;
	@Resource
	protected FreeTrainingBaseService freeTrainingBaseService;
	// ========================== 分页信息 ==========================
	protected int currentPage = 1;
	protected int pageSize = 3;

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	// ========================== json的支持 ==========================
	protected void printJson(String str){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().print(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void printJson(Object object){
		String msg = JSON.toJSONString(object,new ValueFilter(){
			@Override
			public Object process(Object obj, String s, Object value) {
				if(value == null){
					return "";
				}
				return value;
			}
		});
		printJson(msg);
	}
	// ========================== 取值 ==========================
	public String getStrParam(String key,String defaultVal){
		String retVal = ServletActionContext.getRequest().getParameter(key);
		return retVal == null ? retVal : defaultVal;
	}
	
	// ========================== 其它 ==========================
	protected void putContext(String key, Object value){
		ActionContext.getContext().put(key, value);
	}
	
	protected Object getSessionAttr(String key){
		return ActionContext.getContext().getSession().get(key);
	}
	
	protected Object getUser(){
		return getSessionAttr(Code.param.LOGIN_USER);
	}
}
