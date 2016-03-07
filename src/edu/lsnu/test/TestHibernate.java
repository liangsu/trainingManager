package edu.lsnu.test;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.lsnu.domain.AdminUser;
import edu.lsnu.domain.PageBean;
import edu.lsnu.domain.TrainingBase;
import edu.lsnu.domain.TrainingItem;
import edu.lsnu.service.AdminUserService;
import edu.lsnu.service.TrainingBaseService;
import edu.lsnu.utils.QueryHelper;

public class TestHibernate {

	ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");

	@Test
	public void testGetAdminUser() {
		AdminUserService as = (AdminUserService) ac
				.getBean("adminUserServiceImpl");
		AdminUser adminUser = as.get(1);
		Assert.assertEquals("数据不匹配", adminUser.getUsername(), "admin");
	}

	@Test
	public void testDeleteAdminUser() {
		AdminUserService as = (AdminUserService) ac
				.getBean("adminUserServiceImpl");
		AdminUser adminUser = as.get(4);
		as.delete(adminUser.getId());
	}

	@Test
	public void testAddTrainingBase() {
		TrainingBaseService tbs = (TrainingBaseService) ac
				.getBean("trainingBaseServiceImpl");

		TrainingBase tb = new TrainingBase();
		tb.setName("达内");
		tb.setDescription("国内最好的培训机构");
		tb.setAddress("成都");
		tb.setAddTime(new Date());

		tbs.add(tb);

	}

	@Test
	public void testGetTrainingBase() {
		TrainingBaseService tbs = (TrainingBaseService) ac
				.getBean("trainingBaseServiceImpl");
		TrainingBase tb = tbs.get(1);
		System.out.println(tb.getAddTime());
	}

	@Test
	public void testGetCurrentTrainingBase() {
		TrainingBaseService tbs = (TrainingBaseService) ac.getBean("trainingBaseServiceImpl");
		PageBean pageBean = tbs.getYearDataByPage(1, 3,2015,null);
		
		List<TrainingBase> list = pageBean.getRecordList();
		System.out.println(pageBean.getRecordCount());
		for (TrainingBase trainingBase : list) {
			System.out.println(trainingBase);
			for(TrainingItem ti : trainingBase.getTrainingItems()){
				System.out.println(ti.getId());
			}
		}
	}
}
