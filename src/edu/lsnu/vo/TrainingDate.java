package edu.lsnu.vo;

import java.util.Date;

import edu.lsnu.utils.DateUtil;

/**
 * 实习实训起止时间
 * @author liangsu
 *
 */
public class TrainingDate {
	private Date startDate;
	private Date endDate;
	private int grade;
	private Date evaluateDate;

	// --构造方法
	public TrainingDate() {
	}
	public TrainingDate(Date startDate, Date endDate,int grade,Date evaluateDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.grade =  grade;
		this.evaluateDate = evaluateDate;
	}
	public TrainingDate(String startDate, String endDate,String grade,String evaluateDate) {
		this.startDate = DateUtil.getDate(startDate, "yyyy-MM-dd");
		this.endDate = DateUtil.getDate(endDate, "yyyy-MM-dd");
		this.grade = Integer.parseInt(grade);
		this.evaluateDate = DateUtil.getDate(evaluateDate, "yyyy-MM-dd");
	}
	// ---
	/**
	 * 判断当前时间是否在实习实训起止时间之间
	 * @return
	 */
	public boolean containsNowDate(){
		boolean flag = false;
		Date now = new Date();
		if(startDate != null && endDate != null 
				&& startDate.getTime() <= now.getTime() 
				&& endDate.getTime() >= now.getTime()){
			flag = true;
		}
		return flag;
	}

	// ---
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
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Date getEvaluateDate() {
		return evaluateDate;
	}
	public void setEvaluateDate(Date evaluateDate) {
		this.evaluateDate = evaluateDate;
	}
}
