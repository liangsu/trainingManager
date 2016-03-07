package edu.lsnu.service;

import edu.lsnu.base.DaoSupport;
import edu.lsnu.domain.AdminUser;

public interface AdminUserService extends DaoSupport<AdminUser>{


	/**
	 * 检测用户名和密码是否正确
	 * @param adminUser 被检测的用户
	 * @return
	 */
	boolean checkAdminUser(AdminUser adminUser);
}
