package edu.lsnu.action;

import java.io.OutputStream;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import edu.lsnu.utils.RandomValidateCode;

/**
 * 验证码
 * @author Administrator
 */
@Controller
@Scope("prototype")
public class ValidateImageAction extends ActionSupport{
	
	@Override
	public String execute() throws Exception {
		
		RandomValidateCode rvc = new RandomValidateCode();
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		OutputStream out = ServletActionContext.getResponse().getOutputStream();
		
		rvc.createValidateImage(session,out);
		
		return NONE;
	}
}
