package edu.lsnu.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.lsnu.base.BaseAction;
import edu.lsnu.domain.Menu;
import edu.lsnu.utils.StringUtil;

/**
 * 菜单控制器
 * @author liangsu
 *
 */
@Controller
@Scope("prototype")
public class MenuAction extends BaseAction<Menu>{

	/** 获取菜单列表 */
	public String list() throws Exception{
		List<Menu> menuTree = menuService.getMenuTree();
		ActionContext.getContext().put("menuTree", menuTree);
		return "list";
	}
	
	/** 添加、修改菜单，返回json*/
	public void edit() throws Exception{
		String msg = "";
		
		//1.校验数据
		if(StringUtil.isBank(model.getName())){
			msg += "菜单名称不能为空";
		}
		//>>校验没通过，则msg的长度大于0，返回页面消息
		if(msg.length() > 0){
			printJson(msg);
			return ;
		}
		
		//2.保存到数据
		msg += menuService.edit(model);
		
		//3.如果msg的长度等于0，则保存成功
		if(msg.length() == 0){
			msg = "ok";
		}
		
		//4.返回消息
		printJson(msg);
	}
	
	/** 删除菜单 */
	public void delete(){
		String msg = menuService.deleteMenu(model.getId());
		//删除失败，则返回的字符串长度大于0，删除成功，返回的字符串长度等于0
		//删除成功，返回“ok”字符串
		if(msg.length() == 0){
			msg = "ok";
		}
		printJson(msg);
	}
}
