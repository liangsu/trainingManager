package edu.lsnu.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.lsnu.base.BaseAction;
import edu.lsnu.domain.FreeTrainingBase;
import edu.lsnu.domain.PageBean;
import edu.lsnu.domain.Student;
import edu.lsnu.domain.TrainingBase;
import edu.lsnu.utils.Code;
import edu.lsnu.utils.DateUtil;
import edu.lsnu.utils.TrainingDateUtil;
import edu.lsnu.utils.ValidateUtil;
import edu.lsnu.vo.TrainingDate;

@Controller
@Scope("prototype")
public class StudentAction extends BaseAction<Student> {

	private int gradeYear; //年级,由于用grade传值有问题,所以改用gradeYear
	private String keyword;
	private String sortField;
	private boolean asc = true;//默认升序
	
	/** 学生列表 */
	public String list() throws Exception {
		if(gradeYear <= 0){
			gradeYear = DateUtil.getCurrentYear();
		}
		
		PageBean pageBean = studentService.getPage(currentPage, pageSize, gradeYear, keyword, sortField, asc);
		
		putContext("pageBean", pageBean);
		
		return "list";
	}
	
	/** 添加、修改学生页面 */
	public String editUI() throws Exception {
		//1.判断是添加页面还是修改页面
		//1.1修改页面,判断是否能够修改
		if(model != null && model.getId() != null && model.getId() > 0){
			model = studentService.get(model.getId());
			if(!TrainingDateUtil.canEditStudent(model)){
				putContext("message", "历史数据不能修改!");
				putContext("url", "/student_list.action");
				return "message";
			}
		}
		//1.2添加页面，判断是否能够添加
		else{
			if(!TrainingDateUtil.canAddStudent()){
				putContext("message", "请在系统配置中设置好实习实训时间再添加学生");
				putContext("url", "/student_list.action");
				return "message";
			}
		}
		
		return "editUI";
	}
	
	/** 修改学生 */
	public void edit() throws Exception {
		String msg = "";
		try {
			//1.获取特殊字段
			int oldId = getIntParam("oldId", 0);
			
			//2.校验字段
			
			//3.判断添加还是修改学生
			//3.1修改学生
			if(oldId > 0){
				msg += studentService.editStu(model,oldId);
			}
			//3.2添加学生
			else{
				msg += studentService.addStu(model);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		
		//3.如果返回的消息的长度等于0，则修改成功，返回结果"ok"
		if(msg.length() == 0){
			msg = "ok";
		}
		
		//4.返回消息
		printJson(msg);
	}
	
	/** 删除学生 */
	public void delete() {
		String msg = "";
		try {
			if(model != null && model.getId() > 0){
				//1.获取要删除的学生
				model =  studentService.get(model.getId());
				
				//2.判断要删除的数据是否存在
				if(model == null){
					msg = "你删除的数据不存在！";
					printJson(msg);
					return;
				}
				
				//3.判断能否删除
				if(!TrainingDateUtil.canDeleteStudent(model)){
					msg += "历史数据不允许删除!";
					printJson(msg);
					return ;
				}
				
				//4.保存到数据库
				studentService.delete(model);
				msg += "ok";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		printJson(msg);
	}

	/** 个人中心页面 */
	public String infoUI(){
		Student student = (Student) ActionContext.getContext().getSession().get(Code.param.LOGIN_USER);
		ActionContext.getContext().put("info", student);
		return "infoUI";
	}
	
	/** 学生选择基地页面 */
	public String chooseTrainingBaseUI() {
		//1.获取登录用户信息
		Student user = (Student) getUser();
		model = studentService.get(user.getId());
		
		//2.判断是否能够选择基地
		if(!TrainingDateUtil.canChooseBase(model)){
			putContext("message", "你已经不能选择实习基地");
			return "message";
		}
		
		//2.如果是自主实习，获取自主实习企业信息
		if(model != null && model.getTrainingType() == Code.param.STUDNET_TRAINING_TYPE_FREEDOM){
			FreeTrainingBase freeTrainingBase = freeTrainingBaseService.get(model.getTid());
			putContext("freeTrainingBase", freeTrainingBase);
		}
		//3.获取今年的实训时间
		TrainingDate trainingDate = basicSettingsService.getTrainingDate();
		//4.根据时间获取实习实训基地信息
		if(trainingDate != null && trainingDate.containsNowDate()){
			List<TrainingBase> trainingBases = trainingBaseService.getList("from TrainingBase tb where tb.addTime between ? and ?", trainingDate.getStartDate(),trainingDate.getEndDate());
			putContext("trainingBases", trainingBases);
		}
		return "chooseTrainingBaseUI";
	}
	
	/** 学生选择基地 */
	public void chooseTrainingBase() {
		String msg = "";
		try {
			//1.校验字段
			if(model.getTrainingType() != 0 && model.getTrainingType() != 1){
				msg += "实习类型不能为空,";
			}
			if(model.getTrainingType() == Code.param.STUDNET_TRAINING_TYPE_CENTRALIZE && model.getTid() <= 0){
				msg += "请选择实习基地,";
			}
			//1.2自主实习校验
			if(model.getTrainingType() == Code.param.STUDNET_TRAINING_TYPE_FREEDOM){
				msg += ValidateUtil.isBank(model.getFreeTrainingBase().getName(), "实习企业名称不能为空,");
				msg += ValidateUtil.isBank(model.getFreeTrainingBase().getAddress(), "实习企业地址不能为空,");
				msg += ValidateUtil.isBank(model.getFreeTrainingBase().getLinkerName(), "实习企业联系人不能为空,");
				msg += ValidateUtil.isBank(model.getFreeTrainingBase().getLinkerPhone(), "实习企业联系电话不能为空,");
				msg += ValidateUtil.isBank(model.getFreeTrainingBase().getTeacherName(), "实习企业指导老师不能为空,");
				msg += ValidateUtil.isBank(model.getFreeTrainingBase().getTeacherPhone(), "实习企业指导老师电话不能为空,");
			}
			//1.3校验失败
			if(msg.length() > 0){
				msg = msg.substring(0, msg.length() - 1);
				printJson(msg);
				return;
			}
			
			//2.保存到数据库
			//集中实训
			if(model != null && model.getTrainingType() == Code.param.STUDNET_TRAINING_TYPE_CENTRALIZE){
				studentService.chooseBase(model);
			}
			//自主实训
			else if(model != null && model.getTrainingType() == Code.param.STUDNET_TRAINING_TYPE_FREEDOM){
				int fid = freeTrainingBaseService.AddandGet(model.getFreeTrainingBase());
				model.setTid(fid);
				studentService.chooseBase(model);
			}
			msg = "ok";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		printJson(msg);
	}
	
	/**学生评价页面*/
	public String evaluateUI(){
		Object user = getUser();
		Student stu = null;
		//1.判断登陆用户是学生还是管理员
		//1.1学生登陆
		if(user instanceof Student){
			stu = studentService.get(((Student)user).getId());
			
			//1.1.1判断是否能够评价
			if(!TrainingDateUtil.canEvaluate(stu)){
				putContext("message", "你已经过了评价基地的时间了");
				return "message";
			}
			
			//1.1.2获取训练基地
			//1.1.2.1集中训练
			if(stu.getTrainingType() == Code.param.STUDNET_TRAINING_TYPE_CENTRALIZE
					&& stu.getTid() > 0){
				TrainingBase tb = trainingBaseService.get(stu.getTid());
				stu.setTrainingBase(tb);
			}
			//1.1.2.1集中训练
			else if(stu.getTrainingType() == Code.param.STUDNET_TRAINING_TYPE_FREEDOM
					&& stu.getTid() > 0){
				FreeTrainingBase ftb = freeTrainingBaseService.get(stu.getTid());
				stu.setFreeTrainingBase(ftb);
			}
			putContext("info", stu);
		}
		//1.2管理员登陆
		else{
			putContext("message", "教师不能够评价基地");
			return "message";
		}
		
		return "evaluateUI";
	}
	
	/**学生评价页面*/
	public void evaluate(){
		String msg = "";
		try {
			msg = studentService.evaluate(model);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		
		if(msg.length() == 0){
			msg = "ok";
		}
		printJson(msg);
	}
	
	// ---
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public boolean isAsc() {
		return asc;
	}
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	public int getGradeYear() {
		return gradeYear;
	}
	public void setGradeYear(int gradeYear) {
		this.gradeYear = gradeYear;
	}
}
