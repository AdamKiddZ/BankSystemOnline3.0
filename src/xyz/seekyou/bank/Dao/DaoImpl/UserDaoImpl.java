
package xyz.seekyou.bank.Dao.DaoImpl;


import xyz.seekyou.bank.Dao.iUserDao;
import xyz.seekyou.bank.bean.UserBean;
import xyz.seekyou.bank.factory.DaoFactory;
import xyz.seekyou.bank.util.CustomCryptUtil;
import xyz.seekyou.bank.util.MD5Util;

import java.sql.*;
import java.util.ArrayList;

// 存储方法实现—addBank()
// 用户注册实现—register()
// 用户登录实现—login()
// 获取余额实现—getBalance()
// 转账实现—transfer()

/**
* @author Adam_Zed
* @version 2.0
* 单例饱汉模式的控制层
*/
public class UserDaoImpl implements iUserDao {
	//连接
	private static Connection conn;

	static {
		//加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem", "root", "19981223zcy");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//私有构造方法
	private UserDaoImpl(){
	}
	//静态内部类
	private static class SingletonBuild{
        private static UserDaoImpl instance = DaoFactory.getInstance().factoryMake("bankdaoimpl");
    }

	//获取单例对象
	public synchronized static UserDaoImpl getInstance(){
		return  SingletonBuild.instance;
	}

	//用户注册方法，返回用户注册的ID
	public int register(String username,String password){
		int flag=0;
		// 发送sql语句块
		String insert_sql = "insert into t_user(user_name,user_password) values(?,?)";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps = conn.prepareStatement(insert_sql,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, CustomCryptUtil.cryptData(username));// 设置参数
			ps.setString(2, MD5Util.getMD5(password));
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				flag=rs.getInt(1);
			}
			// 完成页面的跳转
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
//				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return flag;
	}

	//用户登录的声明
	public UserBean login(int userID, String password){
		String sql="select * from t_user where user_id=? AND user_password=?";
		UserBean user=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, userID);
				ps.setString(2, MD5Util.getMD5(password));
				rs = ps.executeQuery();
				while (rs.next()) {
					user=new UserBean();
					user.setId(userID);
					user.setUsername(CustomCryptUtil.cryptData(rs.getString("user_name")));
					user.setFlag(rs.getInt("user_flag"));
					user.setBalance(rs.getBigDecimal("balance"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					ps.close();
//					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return user;
	}


//	//获取转账用户数据
//	public UserBean getBalance(int userid){
//		String sql="select user_id,user_name,balance from t_user where user_id=? AND user_password=?";
//		UserBean user=null;
//		PreparedStatement ps;
//		ResultSet rs;
//		try {
//				if(conn==null) {
//					//加载驱动
//					Class.forName("com.mysql.jdbc.Driver");
//					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem", "root", "19981223zcy");
//				}
//				ps = conn.prepareStatement(sql);
//				ps.setInt(1, userid);
//				ps.setString(2, password);
//				rs = ps.executeQuery();
//				while (rs.next()) {
//					user.setId(userid);
//					user.setUsername(rs.getString("user_name"));
//					user.setBalance(new MoneyBean(rs.getDouble("balance")));
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return user;
//	}


	public boolean isUserExisted(int userID){
		String sql="select * from t_user where user_id=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
			try {
				ps=conn.prepareStatement(sql);
				ps.setInt(1,userID);
				rs=ps.executeQuery();
				while(rs.next()){
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					ps.close();
//					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		return false;
	}

	@Override
	public UserBean adminLogin(int userID, String password) {
		String sql="select * from t_user where user_id=? AND user_password=? AND admingrant=1";
		UserBean user=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			ps.setString(2, MD5Util.getMD5(password));
			rs = ps.executeQuery();
			while (rs.next()) {
				user=new UserBean();
				user.setId(userID);
				user.setUsername(CustomCryptUtil.cryptData(rs.getString("user_name")));
				user.setFlag(rs.getInt("user_flag"));
				user.setBalance(rs.getBigDecimal("balance"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
//				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return user;
	}


	//管理员特有功能，获取所有普通用户列表
	@Override
	public ArrayList<UserBean> getAllUsers() {
		String sql="select * from t_user where admingrant=0";
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList<UserBean> users=new ArrayList<>();
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			UserBean user;
			while (rs.next()) {
				user=new UserBean();
				user.setId(rs.getInt("user_id"));
				user.setUsername(CustomCryptUtil.cryptData(rs.getString("user_name")));
				user.setFlag(rs.getInt("user_flag"));
				user.setBalance(rs.getBigDecimal("balance"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
//				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return users;
	}

	@Override
	public boolean lockAccount(int userid) {
		String sql="update t_user set user_flag=1 where user_id=?";
		PreparedStatement ps=null;
		int rs=0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,userid);
			rs=ps.executeUpdate();
			if(rs>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
//				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

	@Override
	public boolean unlockAccount(int userid) {
		String sql="update t_user set user_flag=0 where user_id=?";
		PreparedStatement ps=null;
		int rs=0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,userid);
			rs=ps.executeUpdate();
			if(rs>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
//				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return false;
	}
}