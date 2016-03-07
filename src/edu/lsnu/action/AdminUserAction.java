package edu.lsnu.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import edu.lsnu.base.BaseAction;
import edu.lsnu.domain.AdminUser;
import edu.lsnu.utils.RandomValidateCode;

@Controller
@Scope("prototype")
public class AdminUserAction extends BaseAction<AdminUser>{

	private String code;
	
	/** 处理登录 */
	public String login() throws Exception {
		
		//校验验证码
		String codeInSession = (String) ServletActionContext.getRequest().getSession().getAttribute(RandomValidateCode.RANDOM_CODE_KEY);
		if(codeInSession == null || !codeInSession.equalsIgnoreCase(code)){
			return "loginUI";
		}
		
		//校验用户名和密码
		if(!adminUserService.checkAdminUser(model)){
			return "loginUI";
		}
		
		return "toTrainingBaseList";
	}

	
	// ---
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
