package edu.lsnu.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.lsnu.base.BaseAction;
import edu.lsnu.domain.Menu;
import edu.lsnu.domain.Student;
import edu.lsnu.utils.Code;
import edu.lsnu.utils.StringUtil;

@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<Object>{
	
	Log log = LogFactory.getLog(HomeAction.class);
	
	private String username;
	private String password;
	private int type = 0;//登陆类型，0：学生、1：教师
	
	/** 登陆界面 */
	public String loginUI(){
		return "loginUI";
	}
	
	/**
	 * 登陆处理
	 * @return
	 */
	public void login(){
		String msg = "";
		try {
			//1.校验数据
			//1.1校验登陆用户
			if(StringUtil.isBank(username)){
				msg += "用户名不能为空,";
			}
			if(StringUtil.isBank(password)){
				msg += "登陆密码不能为空,";
			}
			//1.2校验失败，返回消息
			if(msg.length() > 0){
				msg = msg.substring(0, msg.length() - 1);
				printJson(msg);
				return;
			}
			
			//2.登陆处理
			Object user = null;
			List<Menu> menus = null;
			//2.1学生登陆,获取学生信息和菜单
			if(type == Code.param.LOGIN_TYPE_STUDENT){
				Student student = loginService.getStudent(username, password);
				if(student == null){
					msg += "用户名或者密码不存在";
				}else if(student.getState() == Code.param.STUDENT_ALLOW_LOGIN){
					user = student;
					menus =roleService.getRoleMenusByName(Code.param.ROLE_STUDENT);
				}else{
					msg += "该用户不允许登陆";
					printJson(msg);
					return ;
				}
			}
			//2.2教师登陆，获取教师信息和菜单
			else if(type == Code.param.LOGIN_TYPE_ADMINUSER){
				user = loginService.getAdminUser(username, password);
				menus =roleService.getRoleMenusByName(Code.param.ROLE_ADMINUSER);
				System.out.println("LoginAction menus 1"+menus);
			}
			
			//3.用户登陆，存放用户的信息和菜单
			if(user != null){
				ActionContext.getContext().getSession().put(Code.param.LOGIN_USER, user);
				if(menus!= null && menus.size() > 0){
					System.out.println("LoginAction menus"+menus.size());
					ActionContext.getContext().getSession().put(Code.param.LOGIN_USER_MENUS, menus);
				}
				msg = "ok";
			}else{
				msg += "用户名或者密码不存在";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		printJson(msg);
	}
	
	/**
	 * 登陆处理
	 * @return
	 */
	public String logout(){
		ServletActionContext.getContext().getSession().clear();
		return "loginUI";
	}

	// ---
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
