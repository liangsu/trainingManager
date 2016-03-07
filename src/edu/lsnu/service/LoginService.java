package edu.lsnu.service;

import edu.lsnu.base.DaoSupport;
import edu.lsnu.domain.AdminUser;
import edu.lsnu.domain.Student;

public interface LoginService extends DaoSupport<Object>{

	Student getStudent(String idStr,String password);
	
	AdminUser getAdminUser(String username, String password);
}
