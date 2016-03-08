package edu.lsnu.interceptor;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import edu.lsnu.domain.Menu;
import edu.lsnu.utils.Code;
import edu.lsnu.utils.StringUtil;

public class PrivilegeInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Object user = ActionContext.getContext().getSession().get(Code.param.LOGIN_USER); // 当前登录用户
		List<Menu> menus = (List<Menu>) ActionContext.getContext().getSession().get(Code.param.LOGIN_USER_MENUS); //获取当前登录用户的菜单
		List<String> allUrls = (List<String>) ServletActionContext.getServletContext().getAttribute(Code.param.URL_ALL_MENUS);//需要判断权限的菜单
		
		String namespace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		String privUrl = namespace + actionName; // 对应的权限URL
		
		// 如果未登录
		if (user == null) {
			if (privUrl.startsWith("/login_login")) { // "/user_loginUI", "/user_login"
				// 如果是去登录，就放行
				return invocation.invoke();
			} else {
				// 如果不是去登录，就转到登录页面
				return "loginUI";
			}
		}
		// 如果已登 录，就判断权限
		else {
			if (hasPrivilegeByUrl(allUrls,menus,privUrl)) {
				// 如果有权限，就放行
				return invocation.invoke();
			} else {
				// 如果没有权限，就转到提示页面
				return "noPrivilegeError";
			}
		}
	}

	/**
	 * 判断是否有权限
	 * @param menus
	 * @param privUrl
	 * @return
	 */
	private boolean hasPrivilegeByUrl(List<String> allUrls,List<Menu> menus,String privUrl){
		boolean flag = false;
		
		//1.判断该权限菜单是否需要做权限判断
		//不需要检测则直接放行
		if(!isNeedCheck(allUrls, privUrl)){
			return true;
		}
		
		if(menus != null && menus.size() > 0){
			//2.判断一级菜单是否包含次权限
			for (Menu first : menus) {
				if(isMenuContainsUrl(first, privUrl)){
					flag = true;
					break;
				}
			}
			
			//3.如果一级菜单没有权限，判断二级菜单是否包含次权限
			if(!flag){
				for (Menu first : menus) {
					List<Menu> secondMenus = first.getChildren();
					if(secondMenus != null && secondMenus.size() > 0){
						for (Menu second : secondMenus) {
							if(isMenuContainsUrl(second, privUrl)){
								flag = true;
								break;
							}
						}
					}
				}
			}
		}
		
		return flag;
	}
	
	/**
	 * 判断用户访问的url是否需要检测
	 * @param allUrls
	 * @param privUrl
	 * @return
	 */
	private boolean isNeedCheck(List<String> allUrls, String url){
		boolean isNeedCheck = false; //所有的url中是否含有用户访问的url
		
		if(allUrls != null){
			for (String str : allUrls) {
				if(str.indexOf(url) != -1){
					isNeedCheck = true;
					break;
				}
			}
		}else{
			isNeedCheck = true;//如果所有的url为空，则要检测
		}
		
		return  isNeedCheck;
	}
	
	private boolean isMenuContainsUrl(Menu menu,String privUrl){
		boolean flag = false;
		
		String url = menu.getUrl();
		if(url != null && StringUtil.isNotBank(url)){
			if(url.contains(privUrl)){
				flag = true;
			}
		}
		
		return flag;
	}
	
}
