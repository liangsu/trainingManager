package edu.lsnu.utils;

import java.util.Date;

import edu.lsnu.domain.Student;
import edu.lsnu.domain.TrainingBase;
import edu.lsnu.service.BasicSettingsService;
import edu.lsnu.vo.TrainingDate;

public class TrainingDateUtil {
	private static TrainingDate trainingDate;
	
	static{
		build();
	}
	
	private TrainingDateUtil(){}
	
	public static void build(){
		BasicSettingsService basicSettingsService = SpringContextUtil.getBean("basicSettingsServiceImpl");
		trainingDate = basicSettingsService.getTrainingDate();
	}
	
	public static TrainingDate getTrainingDate() {
		return trainingDate;
	}

	/**
	 * 判断能否添加基地
	 * @return
	 */
	public static boolean canAddTrainingBase(){
		boolean flag = false;
		Date now = new Date();
		if(trainingDate.getEndDate().getTime() >= now.getTime()){
			flag = true;
		}
		return flag;
	}
	
	public static boolean canEditTrainingBase(TrainingBase trainingBase){
		boolean flag = false;
		Date now = new Date();
		//当前时间在起止时间之内，并且年份grade相等
		if(trainingDate.getStartDate() != null 
				&& trainingDate.getEndDate() != null 
				&& trainingDate.getStartDate().getTime() <= now.getTime() 
				&& trainingDate.getEndDate().getTime() >= now.getTime()
				&& trainingDate.getGrade() == trainingBase.getGrade()){
			flag = true;
		}
		
		return flag;
	}
	
	public static boolean canDeleteTrainingBase(TrainingBase trainingBase){
		return canEditTrainingBase(trainingBase);
	}
	
	/**
	 * 判断能否添加学生
	 * @return
	 */
	public static boolean canAddStudent(){
		boolean flag = false;
		Date now = new Date();
		//当前时间在起止时间之内，并且年份grade相等
		if(trainingDate.getStartDate() != null 
				&& trainingDate.getEndDate() != null 
				&& trainingDate.getStartDate().getTime() <= now.getTime() 
				&& trainingDate.getEndDate().getTime() >= now.getTime()){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 判断能否修改学生
	 * @param student
	 * @return
	 */
	public static boolean canEditStudent(Student student){
		boolean flag = false;
		Date now = new Date();
		//当前时间在起止时间之内，并且年份grade相等
		if(trainingDate.getStartDate() != null 
				&& trainingDate.getEndDate() != null 
				&& trainingDate.getStartDate().getTime() <= now.getTime() 
				&& trainingDate.getEndDate().getTime() >= now.getTime()
				&& trainingDate.getGrade() == student.getGrade()){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 判断能否删除学生
	 * 这里是直接调用是否能否编辑学生，虽然方法重复，但仍然保留的目的是：
	 *  防止以后业务逻辑变了，修改学生和删除学生的判断不一样，可以在这里统一修改
	 * @param student
	 * @return
	 */
	public static boolean canDeleteStudent(Student student){
		return canEditStudent(student);
	}
	
	public static boolean canEvaluate(Student student){
		boolean flag = false;
		Date now = DateUtil.getNowShort();
		if(trainingDate.getEvaluateDate() !=null
				&& trainingDate.getEndDate() != null
				&& trainingDate.getEvaluateDate().getTime() <= now.getTime()
				&& trainingDate.getEndDate().getTime() >= now.getTime()){
			flag = true;
		}
		return flag;
	}
	
	public static boolean canChooseBase(Student student){
		boolean flag = false;
		Date now = new Date();
		if(trainingDate.getStartDate() !=null
				&& trainingDate.getEndDate() != null
				&& trainingDate.getStartDate().getTime() <= now.getTime()
				&& trainingDate.getEndDate().getTime() >= now.getTime()
				&& trainingDate.getGrade() == student.getGrade()){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 判断当前时间是否在实习实训起止时间之间
	 * @return
	 */
	public static boolean isNowInStartAndEndDate(){
		boolean flag = false;
		Date now = new Date();
		if(trainingDate.getStartDate() != null && trainingDate.getEndDate() != null 
				&& trainingDate.getStartDate().getTime() <= now.getTime() 
				&& trainingDate.getEndDate().getTime() >= now.getTime()){
			flag = true;
		}
		return flag;
	}
}
