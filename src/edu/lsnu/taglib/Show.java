package edu.lsnu.taglib;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.opensymphony.xwork2.ActionContext;

import edu.lsnu.domain.Menu;
import edu.lsnu.utils.Code;
import javassist.bytecode.Mnemonic;

public class Show extends TagSupport{

	private String module;
	private String url;
	
	@Override
	public int doStartTag() throws JspException {
		List<Menu> top = (List<Menu>) ActionContext.getContext().getSession().get(Code.param.LOGIN_USER_MENUS);
		boolean canUse = false;
		
		canUse = canViewByUrl(top);
		
		if(canUse){
			return EVAL_PAGE;
		}
		return SKIP_BODY;
	}
	
	private boolean canViewByUrl(List<Menu> top) {
		boolean canView = false;
		
		if(top != null && top.size() > 0){
			for (Menu first : top) {
				List<Menu> secondMenus = first.getChildren();
				if(secondMenus != null && secondMenus.size() > 0){
					for (Menu second : secondMenus) {
						if(url.equals(second.getUrl())){
							canView = true;
							break;
						}else{
							List<Menu> thirdMenus = second.getChildren();
							if(thirdMenus != null && thirdMenus.size() > 0){
								for (Menu third : thirdMenus) {
									if(url.equals(third.getUrl())){
										canView = true;
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		
		return canView;
	}

	private boolean canView(List<Menu> top) {
		boolean canView = false;
		
		if(top != null && top.size() > 0){
			//1.一级菜单
			for (Menu first : top) {
				List<Menu> secondMenus = first.getChildren();
				if(secondMenus != null && secondMenus.size() > 0){
					//2.二级菜单
					for (Menu second : secondMenus) {
						if(module.equals(second.getName())){
							List<Menu> thirdMenus = second.getChildren();
							if(thirdMenus != null && thirdMenus.size() > 0){
								//3.三级功能
								for (Menu third : thirdMenus) {
									if(url.equals(third.getUrl())){
										canView = true;
									}
								}
							}
						}
					}
				}
			}
		}
		return canView;
	}

	// ---
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
