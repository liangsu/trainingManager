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

	// --构造方法
	public TrainingDate() {
	}
	public TrainingDate(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public TrainingDate(String startDate, String endDate) {
		this.startDate = DateUtil.getDate(startDate, "yyyy-MM-dd");
		this.endDate = DateUtil.getDate(endDate, "yyyy-MM-dd");
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
}
