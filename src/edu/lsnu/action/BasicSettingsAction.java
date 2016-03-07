package edu.lsnu.action;

import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.lsnu.base.BaseAction;
import edu.lsnu.domain.BasicSettings;

@Controller
@Scope("prototype")
public class BasicSettingsAction extends BaseAction<BasicSettings>{

	private Date startDate;
	private Date endDate;
	
	/** 基本设置页面 */
	public String setUI(){
		Map<String,Object> map = basicSettingsService.setUI();
		ActionContext.getContext().put("map", map);
		return "setUI";
	}
	
	/** 基本设置 */
	public String set(){
		basicSettingsService.set(startDate,endDate);
		return "toSetUI";
	}

	//---
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
