package edu.lsnu.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.lsnu.base.BaseAction;
import edu.lsnu.domain.Menu;
import edu.lsnu.domain.PageBean;
import edu.lsnu.domain.Role;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private int[] mid;//角色的菜单id数组
	
	/** 获取角色列表页 */
	public String list() {
		PageBean pageBean = roleService.getPage(currentPage, pageSize);
		ActionContext.getContext().put("pageBean", pageBean);
		return "list";
	}
	
	/** 角色权限分配页面 */
	public String manageRoleUI() throws Exception{
		List<Menu> menuTree = menuService.getMenuTree();
		if(model != null && model.getId() > 0){
			model = roleService.get(model.getId());
		}
		ActionContext.getContext().put("menuTree", menuTree);
		return "manageRoleUI";
	}
	
	/** 角色权限分配页面 */
	public String manageRole() throws Exception{
		roleService.manageRole(model, mid);
		return "toManageRoleUI";
	}

	/** 删除角色 */
	public String delete() {
		String msg = roleService.deleteRole(model.getId());
		return "toList";
	}

	/** 修改角色 */
	public String update() {
		roleService.update(model);
		return "toList";
	}
	
	// ---
	public int[] getMid() {
		return mid;
	}
	public void setMid(int[] mid) {
		this.mid = mid;
	}
}
