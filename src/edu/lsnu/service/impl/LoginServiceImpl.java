package edu.lsnu.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.lsnu.base.DaoSupportImpl;
import edu.lsnu.domain.AdminUser;
import edu.lsnu.domain.Student;
import edu.lsnu.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl extends DaoSupportImpl<Object> implements LoginService{

	@Override
	public Student getStudent(String idStr, String password) {
		try {
			int id = Integer.parseInt(idStr);
			String hql = "from Student where id = ? and password = ?";
			List<Object> list = super.getList(hql, id,password);
			if(list != null && list.size() > 0){
				return (Student) list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public AdminUser getAdminUser(String username, String password) {
		String hql = "from AdminUser where username = ? and password = ?";
		List<Object> list = super.getList(hql, username, password);
		if(list != null && list.size() > 0){
			return (AdminUser) list.get(0);
		}
		return null;
	}

}
