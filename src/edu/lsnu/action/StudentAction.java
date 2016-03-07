package edu.lsnu.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.lsnu.base.BaseAction;
import edu.lsnu.domain.PageBean;
import edu.lsnu.domain.Student;
import edu.lsnu.domain.TrainingBase;
import edu.lsnu.service.StudentService;
import edu.lsnu.utils.Code;
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
		//2.获取今年的实训时间
		TrainingDate trainingDate = basicSettingsService.getTrainingDate();
		//3.根据时间获取实习实训基地信息
		if(trainingDate != null && trainingDate.containsNowDate()){
			List<TrainingBase> trainingBases = trainingBaseService.getList("from TrainingBase tb where tb.addTime between ? and ?", trainingDate.getStartDate(),trainingDate.getEndDate());
			putContext("trainingBases", trainingBases);
		}
		return "chooseTrainingBaseUI";
	}
	
	/** 学生选择基地 */
	public String chooseTrainingBase() throws Exception{
		//集中实训
		if(model != null && model.getTrainingType() == Code.param.STUDNET_TRAINING_TYPE_CENTRALIZE){
			studentService.updateTid(model);
		}
		//自主实训
		else if(model != null && model.getTrainingType() == Code.param.STUDNET_TRAINING_TYPE_FREEDOM){
			
		}
		return "toChooseTrainingBaseUI";
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
