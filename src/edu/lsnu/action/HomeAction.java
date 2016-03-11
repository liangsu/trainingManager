package edu.lsnu.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.lsnu.base.BaseAction;
import edu.lsnu.domain.AdminUser;
import edu.lsnu.domain.Menu;
import edu.lsnu.domain.Student;
import edu.lsnu.utils.Code;

@Controller
@Scope("prototype")
public class HomeAction extends BaseAction<Object>{

	/** 首页 */
	@SuppressWarnings("unchecked")
	public String index(){
		//获取菜单
		//List<Menu> menus = menuService.getMenuTree();
		List<Menu> menus = (List<Menu>) ActionContext.getContext().getSession().get(Code.param.LOGIN_USER_MENUS);
		
		//获取登陆用户信息
		Map<String, Object> user = new HashMap<String, Object>();
		Object loginUser = getUser();
		String userType = null;
		if(loginUser instanceof Student){
			userType = "学生用户";
			Student stu = (Student) loginUser;
			user.put("username", stu.getUsername());
		}else if(loginUser instanceof AdminUser){
			userType = "管理员用户";
			AdminUser admin = (AdminUser) loginUser;
			user.put("username", admin.getUsername());
		}
		user.put("userType", userType);
		user.put("time", new Date());
		
		//放入页面域
		putContext("menus", menus);
		putContext("user", user);
		return "index";
	}
}
