package edu.lsnu.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.lsnu.base.DaoSupportImpl;
import edu.lsnu.domain.BasicSettings;
import edu.lsnu.service.BasicSettingsService;
import edu.lsnu.utils.DateUtil;
import edu.lsnu.vo.TrainingDate;

@Service
@Transactional
public class BasicSettingsServiceImpl extends DaoSupportImpl<BasicSettings> implements BasicSettingsService{

	private String type = "实训时间";
	
	@Override
	public List<BasicSettings> getByType(String type) {
		String hql = "from BasicSettings where type = ?";
		return super.getList(hql, type);
	}
	
	@Override
	public BasicSettings getByTypeAndKey(String type, String key){
		String hql = "from BasicSettings where type = ? and key = ?";
		List<BasicSettings> list = super.getList(hql, type, key);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Map<String, Object> setUI() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<BasicSettings> list = getByType(type);
		if(list != null && list.size() > 0){
			for (BasicSettings basicSettings : list) {
				map.put(basicSettings.getKey(), basicSettings.getValue());
			}
		}
		return map;
	}

	@Override
	public void set(Date startDate, Date endDate, Date evaluateDate, int grade) {
		//1.设置实习实训开始时间
		BasicSettings start = getByTypeAndKey(type, "startDate");
		if(start == null){
			start = new BasicSettings();
			start.setType(type);
			start.setKey("startDate");
			start.setDescription("实习实训开始日期");
		}
		String startDateStr = DateUtil.getDateString(startDate, "yyyy-MM-dd");
		start.setValue(startDateStr);
		if(start.getId() > 0){
			super.update(start);
		}else {
			super.add(start);
		}
		
		//2.设置实习实训结束时间
		BasicSettings end = getByTypeAndKey(type, "endDate");
		if(end == null){
			end = new BasicSettings();
			end.setType(type);
			end.setKey("endDate");
			end.setDescription("实习实训结束日期");
		}
		String endDateStr = DateUtil.getDateString(endDate, "yyyy-MM-dd");
		end.setValue(endDateStr);
		if(end.getId() > 0){
			super.update(end);
		}else {
			super.add(end);
		}
		
		//3.设置学生评价时间
		BasicSettings evaluate = getByTypeAndKey(type, "evaluateDate");
		if(evaluate == null){
			evaluate = new BasicSettings();
			evaluate.setType(type);
			evaluate.setKey("evaluateDate");
			evaluate.setDescription("学生评价时间");
		}
		String evaluateDateStr = DateUtil.getDateString(evaluateDate, "yyyy-MM-dd");
		evaluate.setValue(evaluateDateStr);
		if(evaluate.getId() > 0){
			super.update(evaluate);
		}else {
			super.add(evaluate);
		}
		
		//4.设置级数（如：2015级）
		BasicSettings gradeBs = getByTypeAndKey(type, "grade");
		if(gradeBs == null){
			gradeBs = new BasicSettings();
			gradeBs.setType(type);
			gradeBs.setKey("grade");
			gradeBs.setDescription("学生评价时间");
		}
		if(grade <= 0){
			grade = DateUtil.getYearByDate(startDate);
		}
		gradeBs.setValue(grade+"");
		if(gradeBs.getId() > 0){
			super.update(gradeBs);
		}else {
			super.add(gradeBs);
		}
	}

	@Override
	public TrainingDate getTrainingDate() {
		Date now = new Date();
		int defGrade = DateUtil.getYearByDate(now);
		TrainingDate trainingDate = new TrainingDate(now, now, defGrade, now);
		
		List<BasicSettings> list = getByType(type);
		if(list != null && list.size() > 0){
			for (BasicSettings bs : list) {
				if("startDate".equals(bs.getKey())){
					trainingDate.setStartDate(DateUtil.getDate(bs.getValue(), "yyyy-MM-dd"));
				}else if("endDate".equals(bs.getKey())){
					trainingDate.setEndDate(DateUtil.getDate(bs.getValue(), "yyyy-MM-dd"));
				}else if("grade".equals(bs.getKey())){
					trainingDate.setGrade( Integer.parseInt(bs.getValue()));
				}else if("evaluateDate".equals(bs.getKey())){
					trainingDate.setEvaluateDate(DateUtil.getDate(bs.getValue(), "yyyy-MM-dd"));
				}
			}
		}
		
		return trainingDate;
	}

}
