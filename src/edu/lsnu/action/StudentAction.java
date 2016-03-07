package edu.lsnu.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;

import com.opensymphony.xwork2.ActionContext;

import edu.lsnu.base.BaseAction;
import edu.lsnu.domain.FreeTrainingBase;
import edu.lsnu.domain.PageBean;
import edu.lsnu.domain.Student;
import edu.lsnu.domain.TrainingBase;
import edu.lsnu.service.StudentService;
import edu.lsnu.utils.Code;
import edu.lsnu.utils.StringUtil;
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
		PageBean pageBean = studentService.getPage(currentPage, pageSize, gradeYear, keyword, sortField, asc);
		
		ActionContext.getContext().put("pageBean", pageBean);
		
		return "list";
	}
	
	/** 添加、修改学生页面 */
	public String editUI() throws Exception {
		if(model != null && model.getId() != null && model.getId() > 0){
			model = studentService.get(model.getId());
		}
		return "editUI";
	}
	
	/** 添加学生 */
	public String eidt() throws Exception {
		studentService.add(model);
		return "toList";
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
