package edu.lsnu.service;

import java.util.List;

import edu.lsnu.base.DaoSupport;
import edu.lsnu.domain.Menu;
import edu.lsnu.domain.PageBean;

public interface MenuService extends DaoSupport<Menu>{

	List<Menu> getMenuByPid(int pid);

	/**
	 * 获取菜单树
	 * @return
	 */
	List<Menu> getMenuTree();

	/**
	 * 添加、修改菜单
	 * @param menu
	 * @return 添加、修改失败，则返回的字符串长度大于0，添加、修改成功，返回的字符串长度等于0
	 */
	String edit(Menu menu);

	/**
	 * 删除菜单
	 * @param id
	 * @return 删除失败，则返回的字符串长度大于0，删除成功，返回的字符串长度等于0
	 */
	String deleteMenu(int id);

	List<String> getAllUrls();
	
}
