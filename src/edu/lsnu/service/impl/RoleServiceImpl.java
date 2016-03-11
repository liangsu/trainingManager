package edu.lsnu.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.lsnu.base.DaoSupportImpl;
import edu.lsnu.domain.Menu;
import edu.lsnu.domain.PageBean;
import edu.lsnu.domain.Role;
import edu.lsnu.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends DaoSupportImpl<Role> implements
		RoleService {

	@Override
	public PageBean getPage(int currentPage, int pageSize) {
		String hql = "from Role";
		return super.getByPage(hql, currentPage, pageSize);
	}

	@Override
	public void manageRole(Role role, int[] mids) {
		role = super.get(role.getId());

		Set<Menu> menus = new HashSet<Menu>();
		Menu menu = null;
		if (mids != null && mids.length > 0) {
			for (int mid : mids) {
				menu = new Menu();
				menu.setId(mid);
				menus.add(menu);
			}
		}
		role.setMenus(menus);

		super.update(role);
	}

	@Override
	public String deleteRole(int id) {
		String msg = "";
		try {
			Role role = super.get(id);
			if (role.getMenus() == null || role.getMenus().size() == 0) {
				super.delete(role);
			} else {
				msg += "不能删除分配了菜单的角色";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}

	@Override
	public void update(Role role) {
		Role roleInDB = null;
		if (role != null && role.getId() > 0) {
			roleInDB = super.get(role.getId());
		}

		if (roleInDB != null) {
			roleInDB.setName(role.getName());
		}
		super.update(roleInDB);
	}

	@Override
	public List<Menu> getRoleMenusByName(String roleName) {
		String hql = "from Role where name = '"+roleName+"'";
		List<Role> roles = super.getList(hql);
		Set<Menu> menuSet = null;
		if (roles != null && roles.size() > 0) {
			menuSet = roles.get(0).getMenus();
		}
		
		//组合一级菜单和二级菜单
		List<Menu> menuList = new ArrayList<Menu>();
		if (menuSet != null && menuSet.size() > 0) {
			for (Menu first : menuSet) {
				if (first.getPid() == 0) {
					menuList.add(first);
				}
			}

			for (Menu first : menuList) {
				List<Menu> second = new ArrayList<Menu>();
				for (Menu menu : menuSet) {
					if (menu.getPid() == first.getId()) {
						second.add(menu);
					}
				}
				first.setChildren(second);
			}
		}
		
		//为菜单排序
		Comparator menuComparator = new Comparator<Menu>() {
			@Override
			public int compare(Menu o1, Menu o2) {
				return (o1.getTheOrder() - o2.getTheOrder());
			}
		};
		Collections.sort(menuList,menuComparator);
		for (Menu menu : menuList) {
			Collections.sort(menu.getChildren(),menuComparator);
		}

		return menuList;
	}
}
