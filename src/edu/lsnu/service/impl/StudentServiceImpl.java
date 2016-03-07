package edu.lsnu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.lsnu.base.DaoSupportImpl;
import edu.lsnu.domain.PageBean;
import edu.lsnu.domain.Student;
import edu.lsnu.service.StudentService;
import edu.lsnu.service.TrainingBaseService;
import edu.lsnu.utils.Code;

@Service
@Transactional
public class StudentServiceImpl extends DaoSupportImpl<Student> implements StudentService{

	@Resource
	private TrainingBaseService trainingBaseService;
	
	@Override
	public void add(Student bean) {
		//1.判断是否存在该学号
		Student oldBean = super.get(bean.getId());
		if(oldBean != null){
			return ;
		}
		
		//2.设置一些初始化的参数
		bean.setPassword(bean.getId()+"");
		bean.setAddTime(new Date());
		bean.setInstitute(1);//默认计科系
		super.add(bean);
	}

	@Override
	public PageBean getPage(int currentPage, int pageSize, int grade,
			String keyword, String sortField, boolean asc) {
		List<Object> params = new ArrayList<Object>();
		
		//1.拼接order by语句
		if(sortField == null || sortField.trim().length() < 0){
			sortField = "s.id";
		}
		String orderBy = "order by "+sortField + (asc ? " asc" : " desc");
		
		//2.拼接hql语句
		String subHql = "from Student s where 1=1 ";
		if(keyword != null && keyword.length() > 0){
			subHql += "and s.username like ? ";
			params.add('%'+keyword+'%');
		}
		if(grade > 0){
			subHql += "and s.grade = ? ";
			params.add(grade);
		}
		
		//3.获取数据
		List<Student> recordList = super.getList("select s "+subHql + orderBy, currentPage, pageSize, params.toArray());
		int recordCount = super.getCount("select count(*) "+subHql, params.toArray()).intValue();
		
		//4.获取基地信息
		if(recordList != null && recordList.size() > 0){
			for (Student student : recordList) {
				//集中实习
				if(student.getTrainingType() == Code.param.STUDNET_TRAINING_TYPE_CENTRALIZE && student.getTid() > 0){
					student.setTrainingBase(trainingBaseService.get(student.getTid()));
				}
				//自主实习
				else{
					
				}
			}
		}
		
		return new PageBean(currentPage, pageSize, recordCount, recordList);
	}

	@Override
	public void updateTid(Student model) {
		try {
			if(model != null && model.getId() != null && model.getId() > 0){
				Student oldBean = super.get(model.getId());
				if(oldBean != null){
					oldBean.setTid(model.getTid());
					super.update(oldBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
