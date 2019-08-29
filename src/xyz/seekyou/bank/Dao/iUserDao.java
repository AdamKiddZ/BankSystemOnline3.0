


package xyz.seekyou.bank.Dao;

import xyz.seekyou.bank.bean.UserBean;

import java.util.ArrayList;

public interface iUserDao {
	//用户注册方法，返回用户注册的ID
	public int register(String username, String password);
	//用户登录的声明
	public UserBean login(int userID, String password);
	//账户是否存在的声明
	public boolean isUserExisted(int userID);
//	//获取转账用户数据
//	public UserBean getBalance(int userID);


	//管理员——————————————————————————
	//管理员登录
	public UserBean adminLogin(int adminID, String password);
	//显示所有账户
	public ArrayList<UserBean> getAllUsers();
	//冻结某人的账户
	public boolean lockAccount(int userID);
	//解冻某人的账户
	public boolean unlockAccount(int userID);

}