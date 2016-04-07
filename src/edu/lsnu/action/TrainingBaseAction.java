package edu.lsnu.action;

import java.util.Date;

import org.apache.catalina.valves.StuckThreadDetectionValve;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.lsnu.base.BaseAction;
import edu.lsnu.domain.PageBean;
import edu.lsnu.domain.TrainingBase;
import edu.lsnu.utils.DateUtil;
import edu.lsnu.utils.TrainingDateUtil;
import edu.lsnu.utils.ValidateUtil;

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
	
	/** 按年度获取学院认可的企业 */
	public String simpleList() throws Exception{
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
		return "simpleList";
	}
	
	/** 添加实习实训基地页面 */
	public String addUI() throws Exception{
		
		return "addUI";
	}

	/** 添加实习实训基地 */
	public void add() throws Exception{
		String msg = "";
		try {
			//1.判断能否添加基地
			if(!TrainingDateUtil.canAddTrainingBase()){
				msg = "请在系统配置中设置好实习实训时间再添加基地";
				printJson(msg);
				return ;
			}
			
			//2.校验相关字段
			msg += ValidateUtil.isMobile(model.getLinkerPhone(), "联系电话不正确!");
			if(msg.length() > 0){
				printJson(msg);
				return ;
			}
			
			//3.设置相关的参数
			model.setAddTime(new Date());
			model.setNum(0);
			model.setGrade( TrainingDateUtil.getTrainingDate().getGrade());
			
			//4.保存到数据库
			trainingBaseService.add(model);
			msg = "ok";
		} catch (Exception e) {
			msg = "error";
			e.printStackTrace();
		}
		
		//5.返回消息
		printJson(msg);
	}
	
	/** 编辑实习实训基地页面 */
	public String editUI() throws Exception{
		String msg = "";
		//1.判断能否编辑基地
		if(!TrainingDateUtil.canAddTrainingBase()){
			msg += "历史数据不能修改!";
			printJson(msg);
		}
		
		//2.判断修改的数据是否为空
		model = trainingBaseService.get(model.getId());
		if(model == null){
			msg += "你修改的数据部存在!";
		}
		
		//3.判断能否修改
		if(model.getGrade() != TrainingDateUtil.getTrainingDate().getGrade()){
			msg += "历史数据不允许修改!";
		}
		
		if(msg.length() > 0){
			putContext("message", msg);
			putContext("url", "/trainingBase_list.action");
			return "message";
		}
		
		return "editUI";
	}
	
	/** 编辑实习实训基地 */
	public void edit() throws Exception{
		String msg = "";
		try {
			//1.校验字段
			msg += ValidateUtil.isMobile(model.getLinkerPhone(), "联系电话不正确!");
			if(msg.length() > 0){
				printJson(msg);
				return ;
			}
			
			//2.保存到数据库
			msg += trainingBaseService.edit(model);
			if(msg.length() == 0){
				msg = "ok";
			}
		} catch (Exception e) {
			msg = "error";
			e.printStackTrace();
		}
		
		//2.返回消息
		printJson(msg);
	}
	
	/** 删除基地 */
	public void delete(){
		String msg = "";
		try {
			//1.判断删除的信息是否存在
			model = trainingBaseService.get(model.getId());
			if(model == null){
				msg = "你删除的信息不存在";
				printJson(msg);
				return ;
			}
			
			//2.判断能否删除
			if(!TrainingDateUtil.canDeleteTrainingBase(model)){
				msg = "历史数据不允许删除";
				printJson(msg);
				return ;
			}
			
			//3.删除
			trainingBaseService.delete(model);
			msg = "ok";
		} catch (Exception e) {
			msg = "error";
			e.printStackTrace();
		}
		printJson(msg);
	}
	
	/**基地详细信息*/
	public String detailInfo(){
		model = trainingBaseService.get(model.getId());
		
		return "detailInfo";
	}
	
	/** 查看基地评价*/
	public String evaluate(){
		model = trainingBaseService.get(model.getId());
		
		return "evaluate";
	}
	
	/** 获取基地评价*/
	public void getEvaluate(){
		if(currentPage < 1){
			currentPage = 1;
		}
		pageSize = 1;
		PageBean pageBean = studentService.getPageBeanEvaluate(currentPage, pageSize, model.getId());
		printJson(pageBean);
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
