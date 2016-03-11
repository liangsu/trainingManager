package edu.lsnu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.lsnu.base.DaoSupportImpl;
import edu.lsnu.domain.PageBean;
import edu.lsnu.service.StatisticalService;

@Service
@Transactional
public class StatisticalServiceImpl extends DaoSupportImpl<Object> implements StatisticalService{

	@Override
	public List<Map<String, Object>> basePsnNum(int grade) {
		String sql = "SELECT "
	               +     "tb.name, "
	               +     "IFNULL((SELECT COUNT(*) from student s where s.trainingType = 0 and s.tid = tb.id),0) as psnNum, "
                   +     "IFNULL((SELECT SUM(s.estimate) from student s where s.trainingType = 0 and s.tid = tb.id),0) as totalScore "
                   + "FROM "
	               +     "training_base tb "
                   + "WHERE tb.grade = ?";
		
		List<Map<String, Object>> list = super.getSqlMapList(sql, grade);
		
		
		int minNum = 0;
		int maxNum = 0;
		if(list != null && list.size() > 0){
			//1.寻找最多学生人数,和最少学生人数,0除外
			Map<String, Object> mapf = list.get(0);
			minNum = Integer.parseInt(mapf.get("psnNum").toString());
			for (Map<String, Object> map : list) {
				int psnNum = Integer.parseInt(map.get("psnNum").toString());
				if(psnNum > maxNum) maxNum = psnNum;
				if(psnNum < minNum && psnNum != 0) minNum = psnNum;
			}
			
			//某个基地综合评价 = 学生评价（最高50分） + 学生人数评价（最高50分）
			//   score   =    avgScore   +    numScore
			//人数评价总分 = ( num – minNum ) * 50 / ( maxNum – minNum )
			//2.计算基地的加权平均分
			double score = 0.0f; //综合得分
			double numScore = 0.0f; //人数评价总分
			double avgScore = 0.0f; //学生评价平均分
			for (Map<String, Object> map : list) {
				int psnNum = Integer.parseInt(map.get("psnNum").toString());//总人数
				int totalScore = Integer.parseInt(map.get("totalScore").toString());//学生评价总得分
				
				//学生评价平均分
				if(psnNum != 0){
					avgScore = totalScore * 1.0f / psnNum;
				}
				//学生人数评价分
				if( (maxNum - minNum) != 0){
					numScore = ((psnNum - minNum) * 50.0f / (maxNum - minNum));
				}
				//综合得分
				if(psnNum != 0){
					score =  avgScore + numScore;
				}
				map.put("score", String.format("%.2f", score));
				map.put("numScore", String.format("%.2f", numScore));
				map.put("avgScore", String.format("%.2f", avgScore));
			}
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> baseStudent(int grade) {
		String sql = "SELECT "
				   +   "s.id, "
				   +   "s.username, "
				   +   "s.classname, "
				   +   "s.phone, "
				   +   "s.email, "
				   +   "s.address, "
				   +   "'集中实习' as trainingType, "
				   +   "tb.name as tName, "
			       +   "tb.address as tAddress "
			       + "FROM "
				   +   "student s LEFT JOIN training_base tb on s.tid = tb.id "
			       + "where s.trainingType = 0 and s.grade = ? "
				   + "UNION ALL "
				   + "SELECT "
				   +   "s.id, "
				   +   "s.username, "
				   +   "s.classname, "
				   +   "s.phone, "
				   +   "s.email, "
				   +   "s.address, "
				   +   "'自主实习' as trainingType, "
				   +   "ftb.name as tName, "
			       +   "ftb.address as tAddress "
			       + "FROM "
				   +   "student s LEFT JOIN free_training_base ftb on s.tid = ftb.id "
			       + "where s.trainingType = 1 and s.grade = ?"; 
		return super.getSqlMapList(sql, grade,grade);
	}

	@Override
	public PageBean baseStudent(int currentPage, int pageSize, int grade) {
		String sql = "SELECT "
				   +   "s.id, "
				   +   "s.username, "
				   +   "s.classname, "
				   +   "s.phone, "
				   +   "s.email, "
				   +   "s.address, "
				   +   "'集中实习' as trainingType, "
				   +   "tb.name as tName, "
			       +   "tb.address as tAddress "
			       + "FROM "
				   +   "student s LEFT JOIN training_base tb on s.tid = tb.id "
			       + "where s.trainingType = 0 and s.grade = ? "
				   + "UNION ALL "
				   + "SELECT "
				   +   "s.id, "
				   +   "s.username, "
				   +   "s.classname, "
				   +   "s.phone, "
				   +   "s.email, "
				   +   "s.address, "
				   +   "'自主实习' as trainingType, "
				   +   "ftb.name as tName, "
			       +   "ftb.address as tAddress "
			       + "FROM "
				   +   "student s LEFT JOIN free_training_base ftb on s.tid = ftb.id "
			       + "where s.trainingType = 1 and s.grade = ?"; 
		
		return super.getSqlMapListByPage(currentPage, pageSize, sql, grade, grade);
	}

}
