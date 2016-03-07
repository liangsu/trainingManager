package edu.lsnu.service;

import java.util.List;
import java.util.Set;

import edu.lsnu.base.DaoSupport;
import edu.lsnu.domain.Menu;
import edu.lsnu.domain.PageBean;
import edu.lsnu.domain.Role;

public interface RoleService extends DaoSupport<Role>{
	
	PageBean getPage(int currentPage,int pageSize);

	void manageRole(Role model, int[] mids);

	/**
	 * 删除角色
	 * @param id 角色id
	 * @return 如果删除失败，返回的字符串长度大于0，删除成功，返回的字符串长度等于0
	 */
	String deleteRole(int id);

	/**
	 * 通过角色关联获取菜单
	 * @param roleName
	 * @return
	 */
	List<Menu> getRoleMenusByName(String roleName);
}
