package edu.lsnu.action;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.lsnu.base.BaseAction;
import edu.lsnu.domain.PageBean;
import edu.lsnu.domain.TrainingBase;
import edu.lsnu.domain.TrainingItem;
import edu.lsnu.service.TrainingBaseService;
import edu.lsnu.service.TrainingItemService;
import edu.lsnu.utils.DateUtil;

@Controller
@Scope("prototype")
public class TrainingBaseAction extends BaseAction<TrainingBase>{

	private int year = 0;
	private String keyword;
	private String linkerName;
	private String linkerPhone;
	private int id;
	
	/** 按年度获取学院认可的企业 */
	public String list() throws Exception{
		//1.处理页面参数
		int	beginYear = trainingBaseService.getBeginYear();//第一个基地的年份
		int endYear = DateUtil.getCurrentYear();
		//1.1处理年份
		if(year <= 0 || year > endYear){
			year = endYear;
		}
		if(keyword == null){
			keyword = "";
		}
		
		//2.准备数据
		PageBean pageBean = trainingBaseService.getYearDataByPage(currentPage,pageSize,year,keyword);
		
		//3.返回页面参数
		ActionContext.getContext().put("beginYear", beginYear);
		ActionContext.getContext().put("endYear", endYear);
		ActionContext.getContext().put("pageBean", pageBean);
		return "list";
	}
	
	/** 添加实习实训基地页面 */
	public String addUI() throws Exception{
		
		return "addUI";
	}

	/** 添加实习实训基地 */
	public String add() throws Exception{
		//1.获取页面传来的数据
		TrainingItem trainingItem = new TrainingItem();
		
		//2.设置相关的参数
		model.setAddTime(new Date());
		trainingItem.setTrainingBase(model);
		
		//3.保存到数据库
		trainingBaseService.add(model);
		trainingItemService.add(trainingItem);
		
		return "toList";
	}
	
	/** 删除基地 */
	public String delete() throws Exception{
		//trainingBaseService.delete(model.getId());
		
		return "toList";
	}
	
	// ---
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getLinkerName() {
		return linkerName;
	}
	public void setLinkerName(String linkerName) {
		this.linkerName = linkerName;
	}
	public String getLinkerPhone() {
		return linkerPhone;
	}
	public void setLinkerPhone(String linkerPhone) {
		this.linkerPhone = linkerPhone;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKeyword() {
		return keyword;
	}
}
