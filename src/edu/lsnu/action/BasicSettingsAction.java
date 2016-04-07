package edu.lsnu.action;

import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.lsnu.base.BaseAction;
import edu.lsnu.domain.BasicSettings;
import edu.lsnu.utils.DateUtil;
import edu.lsnu.utils.TrainingDateUtil;
import edu.lsnu.utils.ValidateUtil;

@Controller
@Scope("prototype")
public class BasicSettingsAction extends BaseAction<BasicSettings>{

	private Date startDate;
	private Date endDate;
	private Date evaluateDate;
	private int grade;
	
	/** 基本设置页面 */
	public String setUI(){
		Map<String,Object> map = basicSettingsService.setUI();
		ActionContext.getContext().put("map", map);
		return "setUI";
	}
	
	/** 基本设置 */
	public void set(){
		String msg = "";
		try {
			//1.校验字段
			msg += ValidateUtil.isNull(startDate, "开始时间不能为空！");
			msg += ValidateUtil.isNull(evaluateDate, "评价时间不能为空！");
			msg += ValidateUtil.isNull(endDate, "结束时间不能为空！");
			if(msg.length() > 0){
				printJson(msg);
				return ;
			}
			Date now = DateUtil.getNowShort();
			if(startDate.getTime() < now.getTime()){
				msg += "开始时间必须大于当前时间!";
				printJson(msg);
				return ;
			}
			if(endDate.getTime() <= startDate.getTime()){
				msg += "结束时间必须大于开始时间按！";
				printJson(msg);
				return ;
			}
			if(evaluateDate.getTime() <= startDate.getTime() || evaluateDate.getTime() >= endDate.getTime()){
				msg += "评价时间必须在开始时间和结束时间之间！";
				printJson(msg);
				return ;
			}
			
			//2.保存到数据库
			basicSettingsService.set(startDate,endDate,evaluateDate,grade);
			TrainingDateUtil.build();//重新加载
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		if(msg.length() == 0){
			msg = "ok";
		}
		printJson(msg);
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
	public Date getEvaluateDate() {
		return evaluateDate;
	}
	public void setEvaluateDate(Date evaluateDate) {
		this.evaluateDate = evaluateDate;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
}
