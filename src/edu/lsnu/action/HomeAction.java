package edu.lsnu.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.lsnu.base.BaseAction;
import edu.lsnu.domain.Menu;
import edu.lsnu.utils.Code;

@Controller
@Scope("prototype")
public class HomeAction extends BaseAction<Object>{

	/** 首页 */
	@SuppressWarnings("unchecked")
	public String index(){
		//List<Menu> menus = menuService.getMenuTree();
		List<Menu> menus = (List<Menu>) ActionContext.getContext().getSession().get(Code.param.LOGIN_USER_MENUS);
		ActionContext.getContext().put("menus", menus);
		return "index";
	}
}
