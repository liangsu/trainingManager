package edu.lsnu.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.lsnu.base.DaoSupportImpl;
import edu.lsnu.domain.Menu;
import edu.lsnu.service.MenuService;

@Service
@Transactional
public class MenuServiceImpl extends DaoSupportImpl<Menu> implements MenuService{

	@Override
	public List<Menu> getMenuByPid(int pid) {
		String hql = "from Menu m where m.pid = ? order by m.theOrder asc";
		return super.getList(hql, pid);
	}

	@Override
	public List<Menu> getMenuTree() {
		List<Menu> topMenus = getMenuByPid(0);
		if(topMenus != null && topMenus.size() > 0){
			for (Menu top : topMenus) {
				top.setChildren( getMenuByPid(top.getId()));
			}
		}
		
		return topMenus;
	}
	
	private int getMaxTheOrderByPid(int pid){
		Long max = null;
		String hql = "select max(theOrder) from Menu where pid = ?";
		max = (Long) super.getCount(hql, pid);
		if(max == null){
			return 0;
		}
		return max.intValue();
	}
	
	@Override
	public String edit(Menu menu) {
		String msg = "";
		try {
			//1.空值处理
			//1.1如果上级菜单的id<0,则上级菜单为顶级菜单
			if(menu.getPid() < 1){
				menu.setPid(0);
			}
			//1.2如果theOrder等于空,则为自增
			if(menu.getTheOrder() < 1){
				menu.setTheOrder(getMaxTheOrderByPid(menu.getPid()) + 1);
			}
			
			//2.判断上级菜单是否存在
			if(menu.getPid() > 0){
				Menu parentMenu = super.get(menu.getPid());
				if(parentMenu == null){
					msg += "上级菜单不存在,";
					return msg;
				}
			}
			
			//3.保存到数据库
			//3.1修改菜单
			if(menu.getId() > 0){
				super.update(menu);
			}
			//3.2添加菜单
			else{
				super.add(menu);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		
		return msg;
	}
	
	@Override
	public String deleteMenu(int id) {
		String msg = "";
		try {
			//1.判断删除的对象是否存在
			Menu bean = null;
			if(id > 0){
				bean = super.get(id);
			}
			if(bean == null){
				msg += "你删除的菜单不存在";
				return msg;
			}
			
			//2.已经分配了权限的菜单不能删除
			if(bean.getRoles() != null && bean.getRoles().size() > 0){
				msg += "你删除的菜单已经被分配了权限，不能删除";
				return msg;
			}
			
			//3.有子菜单的菜单不能删除,
			if(hasChildren(id)){
				msg += "有子菜单，不能删除";
				return msg;
			}
			
			//4.删除对象
			super.delete(bean);
			msg = "ok";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		
		return msg;
	}
	
	/**
	 * 判断菜单是否能够删除
	 * @param id
	 * @return
	 */
	private boolean hasChildren(int id){
		boolean flag = false;
		String hql = "select count(*) from Menu where pid = ?";
		int childrenNum = super.getCount(hql, id).intValue();
		if(childrenNum > 0){
			flag = true;
		}
		return flag;
	}

	@Override
	public List<String> getAllUrls() {
		List<String> list = null;
		String hql = "select distinct url from Menu where url is not null";
		list = super.getSession().createQuery(hql).list();
		return list;
	}
}
