package edu.lsnu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.lsnu.base.DaoSupportImpl;
import edu.lsnu.domain.PageBean;
import edu.lsnu.domain.TrainingBase;
import edu.lsnu.service.TrainingBaseService;
import edu.lsnu.utils.DateUtil;
import edu.lsnu.utils.TrainingDateUtil;
import edu.lsnu.vo.TrainingDate;

@Service
@Transactional
public class TrainingBaseServiceImpl extends DaoSupportImpl<TrainingBase>
		implements TrainingBaseService {

	@Override
	public PageBean getYearDataByPage(int currentPage, int pageSize,int grade, String keyword) {
		List<Object> params = new ArrayList<Object>();
		//1.拼接查询语句
		String hqlSub = "from TrainingBase tb where grade = ? ";
		params.add(grade);
		if(keyword != null && keyword.length() > 0){
			hqlSub += "and tb.name like ? ";
			params.add('%'+keyword+'%');
		}
		
		//2.获取数据
		List list = super.getList("select tb "+hqlSub, currentPage, pageSize, params.toArray());
		Long count = super.getLongCount("select count(*) "+hqlSub, params.toArray());
		
		return new PageBean(currentPage, pageSize, count.intValue(), list);
	}

	@Override
	public int getBeginYear() {
		String hql = "select min(year(addTime)) from TrainingBase";
		Integer year = (Integer) getSession().createQuery(hql).uniqueResult();
		if(year ==null || year < 1){
			year = DateUtil.getCurrentYear();
		}
		return year;
	}

	@Override
	public void updateNum(int id, int num) {
		TrainingBase tb = super.get(id);
		if(tb != null){
			int oldNum = tb.getNum();
			oldNum = (oldNum + num) > 0 ? (oldNum + num) : 0;//如果相加后小于0，则等于0
			tb.setNum(oldNum);
			super.update(tb);
		}
	}

	@Override
	public String edit(TrainingBase trainingBase) {
		String msg = "";
		try {
			//1.获取要修改的对象
			TrainingBase oldBean = super.get(trainingBase.getId());
			if(oldBean == null){
				msg += "你修改的数据不存在!";
				return msg;
			}
			
			//2.判断能否修改
			if(!TrainingDateUtil.canEditTrainingBase(oldBean)){
				msg += "历史数据不能修改";
				return msg;
			}
			
			//2.设置能够修改的字段
			oldBean.setAddress(trainingBase.getAddress());
			oldBean.setDescription(trainingBase.getDescription());
			oldBean.setImg(trainingBase.getImg());
			oldBean.setLinkerName(trainingBase.getLinkerName());
			oldBean.setLinkerPhone(trainingBase.getLinkerPhone());
			oldBean.setName(trainingBase.getName());
			
			//3.修改数据
			super.update(oldBean);
			msg = "ok";
		} catch (Exception e) {
			msg = "error";
			e.printStackTrace();
		}
		return msg;
	}

}
