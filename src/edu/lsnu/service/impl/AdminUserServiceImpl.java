package edu.lsnu.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.lsnu.base.DaoSupportImpl;
import edu.lsnu.domain.AdminUser;
import edu.lsnu.service.AdminUserService;

@Service
@Transactional 
@SuppressWarnings("unchecked")
public class AdminUserServiceImpl extends DaoSupportImpl<AdminUser> implements
		AdminUserService {

	@Override
	public boolean checkAdminUser(AdminUser adminUser) {

		Session session = getSession();
		List<AdminUser> list =  session.createQuery(//
				"from AdminUser a where a.password = :password and a.username = :username")//
				.setParameter("password", adminUser.getPassword())//
				.setParameter("username", adminUser.getUsername())//
				.list();
		
		if(list != null && list.size()>0){
			adminUser.setId(list.get(0).getId());
			return true;
		}
		
		return false;
	}
}
