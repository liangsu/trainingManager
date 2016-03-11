package edu.lsnu.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import edu.lsnu.base.DaoSupport;
import edu.lsnu.domain.BasicSettings;
import edu.lsnu.vo.TrainingDate;

public interface BasicSettingsService extends DaoSupport<BasicSettings>{

	List<BasicSettings> getByType(String type);

	Map<String, Object> setUI();

	void set(Date startDate, Date endDate,Date evaluateDate, int grade);

	BasicSettings getByTypeAndKey(String type, String key);

	TrainingDate getTrainingDate();
}
