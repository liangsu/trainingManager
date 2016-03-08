package edu.lsnu.listener;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.lsnu.service.MenuService;
import edu.lsnu.utils.Code;

public class InitListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		// 获取容器与相关的Service对象
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		MenuService menuService = (MenuService) ac.getBean("menuServiceImpl");
		
		// 准备数据：allPrivilegeUrls
		List<String> allMenuUrls = menuService.getAllUrls();
		sce.getServletContext().setAttribute(Code.param.URL_ALL_MENUS, allMenuUrls);
		System.out.println("------------> 已准备数据allMenuUrls <------------");
	}

	public void contextDestroyed(ServletContextEvent arg0) {

	}
}