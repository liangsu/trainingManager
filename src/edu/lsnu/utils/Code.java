package edu.lsnu.utils;

/**
 * 代码中常量的管理
 * 目的：方便统一管理
 * @author liangsu
 *
 */
public final class Code {
	/**
	 * 常用的常量
	 * @author liangsu
	 */
	public static final class param{
		//登陆用户在session中存的值
		public static final String LOGIN_USER = "login_user";
		//登陆用户的权限菜单在session中存的值
		public static final String LOGIN_USER_MENUS = "login_user_menus";
		//登陆用户类型
		public static final int LOGIN_TYPE_STUDENT = 0;
		public static final int LOGIN_TYPE_ADMINUSER = 1;
		//角色名称
		public static final String ROLE_STUDENT = "学生角色";
		public static final String ROLE_ADMINUSER = "教师角色";
		//实习方式
		public static final int STUDNET_TRAINING_TYPE_CENTRALIZE = 0;//集中实习
		public static final int STUDNET_TRAINING_TYPE_FREEDOM = 1;//自主实习
	}
}
