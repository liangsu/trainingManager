package edu.lsnu.action;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import edu.lsnu.base.BaseAction;
import edu.lsnu.domain.PageBean;
import edu.lsnu.utils.TrainingDateUtil;

/**
 * 统计分析
 * @author liangsu
 */
@Controller
@Scope("prototype")
public class StatisticalAction extends BaseAction<Object>{

	private int grade;
	
	/**
	 * 统计去各个基地的学生人数（基地人数统计）
	 * @return
	 */
	public String basePsnNumUI() {
		if(grade <= 0){
			grade = TrainingDateUtil.getTrainingDate().getGrade();
		}
		List<Map<String, Object>> list = statisticalService.basePsnNum(grade);
		putContext("list", list);
		return "basePsnNumUI";
	}
	
	/**
	 * 统计去各个基地的学生表
	 * @return
	 */
	public String baseStudentUI() {
		if(grade <= 0){
			grade = TrainingDateUtil.getTrainingDate().getGrade();
		}
		PageBean pageBean = statisticalService.baseStudent(currentPage,pageSize,grade);
		putContext("pageBean", pageBean);
		return "baseStudentUI";
	}
	
	// ---
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
