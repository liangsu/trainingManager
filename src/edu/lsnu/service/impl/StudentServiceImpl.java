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
import edu.lsnu.service.FreeTrainingBaseService;
import edu.lsnu.service.StudentService;
import edu.lsnu.service.TrainingBaseService;
import edu.lsnu.utils.Code;
import edu.lsnu.utils.TrainingDateUtil;

@Service
@Transactional
public class StudentServiceImpl extends DaoSupportImpl<Student> implements StudentService{

	@Resource
	private TrainingBaseService trainingBaseService;
	@Resource
	private FreeTrainingBaseService freeTrainingBaseService;
	
	@Override
	public String addStu(Student bean) {
		String msg = "";
		try {
			//1.判断能否添加学生
			if(!TrainingDateUtil.canAddStudent()){
				msg += "请在系统配置中设置好实习实训时间再添加学生";
				return msg;
			}
			
			//2.判断是否存在该学号
			Student oldBean = super.get(bean.getId());
			if(oldBean != null){
				msg += "该学生的学号已经存在!";
				return msg;
			}
			
			//3.设置一些初始化的参数
			bean.setPassword(bean.getId()+"");
			bean.setAddTime(new Date());
			bean.setInstitute(1);//默认计科系
			bean.setGrade(TrainingDateUtil.getTrainingDate().getGrade());

			//4.添加学生
			super.add(bean);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
	}
	
	@Override
	public String editStu(Student student,int oldId) {
		String msg = "";
		try {
			//1.判断修改的对象是否存在
			Student oldBean = super.get(oldId);
			if(oldBean == null){
				msg += "你修改的记录不存在!";
				return msg;
			}
			
			//2.判断能否修改
			if(!TrainingDateUtil.canEditStudent(oldBean)){
				msg += "历史数据不能修改!";
				return msg;
			}
			
			//3.判断是否修改了id
			//3.1没有修改了id
			if(student.getId() == oldId){
				oldBean.setUsername(student.getUsername());
				oldBean.setClassName(student.getClassName());
				oldBean.setState(student.getState());
				super.update(oldBean);
			}
			//3.2修改了id
			else{
				//3.2.1判断新的id是否存在
				Student newBean = super.get(student.getId());
				if(newBean != null){
					msg += "你修改的学生学号已经存在！";
					return msg;
				}
				
				//3.2.2设置原来的值
				//student.setId(oldBean.getId());
				student.setAddress(oldBean.getAddress());
				student.setAddTime(oldBean.getAddTime());
				//student.setClassName(oldBean.getClassName());
				student.setEmail(oldBean.getEmail());
				student.setEstimate(oldBean.getEstimate());
				student.setFreeTrainingBase(oldBean.getFreeTrainingBase());
				student.setGrade(oldBean.getGrade());
				student.setInstitute(oldBean.getInstitute());
				student.setPassword(oldBean.getPassword());
				student.setPhone(oldBean.getPhone());
				//student.setState(oldBean.getState());
				student.setTid(oldBean.getTid());
				student.setTrainingType(oldBean.getTrainingType());
				//student.setUsername(oldBean.getUsername());
				
				//3.2.3删除原来的对象，添加新对象
				super.delete(oldBean);
				super.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "error";
		}
		return msg;
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
				else if(student.getTrainingType() == Code.param.STUDNET_TRAINING_TYPE_FREEDOM && student.getTid() > 0){
					student.setFreeTrainingBase(freeTrainingBaseService.get(student.getTid()));
				}
			}
		}
		
		return new PageBean(currentPage, pageSize, recordCount, recordList);
	}

	@Override
	public void chooseBase(Student model) {
		try {
			if(model != null && model.getId() != null && model.getId() > 0){
				//1.获取要修改的对象
				Student oldBean = super.get(model.getId());
				if(oldBean != null){
					
					//2.维护相关字段，实训基地人数
					if(oldBean.getTrainingType() == Code.param.STUDNET_TRAINING_TYPE_FREEDOM){
						if(model.getTrainingType() == Code.param.STUDNET_TRAINING_TYPE_CENTRALIZE){
							//原来自主实习，现在集中实习,现在的基地+1
							trainingBaseService.updateNum(model.getTid(), +1);
						}
					}else if(oldBean.getTrainingType() == Code.param.STUDNET_TRAINING_TYPE_CENTRALIZE){
						if(model.getTrainingType() == Code.param.STUDNET_TRAINING_TYPE_FREEDOM){
							//原来集中实习，现在自主实习，原来的基地-1
							trainingBaseService.updateNum(oldBean.getTid(), -1);
						}else if(model.getTrainingType() == Code.param.STUDNET_TRAINING_TYPE_CENTRALIZE
								&& oldBean.getTid() != model.getTid()){
							//原来集中实习，现在也是集中实习，原来的基地-1，现在的基地+1
							trainingBaseService.updateNum(oldBean.getTid(), -1);
							trainingBaseService.updateNum(model.getTid(), +1);
						}
					}
					
					//3.修改相关属性值并更新到数据库
					oldBean.setTid(model.getTid());
					oldBean.setTrainingType(model.getTrainingType());
					super.update(oldBean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String evaluate(Student student) {
		String msg = "";
		//1.判断用户是否存在
		Student oldBean = super.get(student.getId());
		if(oldBean == null){
			msg += "用户不存在!";
			return msg;
		}
		
		//2.判断能否评价
		if(!TrainingDateUtil.canEvaluate(oldBean)){
			msg += "你已经不能评价了!";
			return msg;
		}
		
		//3.设置评价的参数
		oldBean.setEstimate(student.getEstimate());
		oldBean.setEvaluate(student.getEvaluate());

		//4.保存到数据库
		super.update(oldBean);
		
		return msg;
	}

}
