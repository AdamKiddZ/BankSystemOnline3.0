


package xyz.seekyou.bank.Dao.DaoImpl;

import xyz.seekyou.bank.Dao.iManagerDao;
import xyz.seekyou.bank.bean.LogBean;
import xyz.seekyou.bank.bean.UserBean;
import xyz.seekyou.bank.factory.DaoFactory;
import xyz.seekyou.bank.util.CustomCryptUtil;
import xyz.seekyou.bank.util.Exception.AccountOverDrawnException;
import xyz.seekyou.bank.util.Exception.BankException;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
* @author Adam_Zed
* @version 1.4
*/
public class ManagerDaoImpl implements iManagerDao{
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
	private ManagerDaoImpl(){

	}

	//静态内部类
	private static class SingletonBuild{
        private static ManagerDaoImpl instance = DaoFactory.getInstance().factoryMake("managerdaoimpl");
    }

	//获取单例对象
	public synchronized static ManagerDaoImpl getInstance(){
		return  SingletonBuild.instance;
	}


	//查询方法的实现
	public BigDecimal inquiry(UserBean user) throws BankException{

		BigDecimal bd=null;
		String sql="select balance from t_user where user_id=?";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1,user.getId());
			rs=ps.executeQuery();
			if(rs.next()){
//				returnWord= "账户:"+user.getUsername()+" 当前余额为："+df.format(rs.getBigDecimal("balance"))+"元";
				bd= rs.getBigDecimal("balance");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BankException("查询失败！");
		}finally {
			try {
				rs.close();
				ps.close();
				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bd;
	}

	//取钱方法的实现
	public String withdraw(UserBean user,BigDecimal money) throws BankException{
		if(user.getFlag()==1){
			throw new BankException("当前账户已冻结！");
		}
		//返回的信息
		String returnWord=null;
//		MoneyBean money=user.getBalance();
		String sql="update t_user set balance=? where user_id=? and user_flag=0";
		PreparedStatement ps=null;
//		money.minus(bd);

		//用户账户余额减少（先判断余额够不够）
		if(user.getBalance().compareTo(money)<0){
			throw new AccountOverDrawnException("账户余额不足！");
		}
		user.setBalance(user.getBalance().subtract(money));
		int rs=0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setBigDecimal(1,user.getBalance());
			ps.setInt(2,user.getId());
			rs=ps.executeUpdate();
			if(rs>0){
				returnWord= "取出成功！"+getReturnMessage(user);
				setLog(user,"取款",money);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			//发生错误则用户余额不变
//			money.plus(bd);
			user.setBalance(user.getBalance().add(money));
			throw new BankException("取款失败！");
		}finally {
			try {
				ps.close();
//				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnWord;
	}

	//存钱方法的实现
	public String deposit(UserBean user,BigDecimal money) throws BankException{
		if(user.getFlag()==1){
			throw new BankException("当前账户已冻结！");
		}
		//返回的信息
		String returnWord=null;

		//用户余额增加，再更新至数据库
		user.setBalance(user.getBalance().add(money));
		//显示余额
		String sql="update t_user set balance=? where user_id=? and user_flag=0";
		PreparedStatement ps=null;
		int rs=0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setBigDecimal(1,user.getBalance());
			ps.setInt(2,user.getId());
			rs=ps.executeUpdate();
			if(rs>0){
				System.out.println("存入成功！");
				returnWord= "存入成功！"+getReturnMessage(user);
				setLog(user,"存款",money);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			//发生错误，则用户余额不变

			user.setBalance(user.getBalance().subtract(money));
			e.printStackTrace();
			throw new BankException("存款失败！");
		}finally {
			try {
				ps.close();
				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnWord;
	}

	//转账方法的实现
	public String transfer(UserBean sender,int receiverID,BigDecimal money) throws BankException{
		//先判断账户是否已经冻结
		if(sender.getFlag()==1){
			throw new BankException("当前账户已冻结！");
		}

		//转账账户余额减少（先判断余额够不够）
		if(sender.getBalance().compareTo(money)<0){
			throw new AccountOverDrawnException("账户余额不足！");
		}

		String returnWord=null;
		String sql="update t_user set balance=? where user_id=? and user_flag=0";
		PreparedStatement ps=null;
		int rs=0;

		sender.setBalance(sender.getBalance().subtract(money));
		try {
			ps=conn.prepareStatement(sql);
			ps.setBigDecimal(1,sender.getBalance());
			ps.setInt(2,sender.getId());
			rs=ps.executeUpdate();
			if(rs<=0){
				//转出失败则恢复余额
				sender.setBalance(sender.getBalance().add(money));
				returnWord= "转出失败！";
				return returnWord;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//转出失败则恢复余额
			sender.setBalance(sender.getBalance().add(money));
			throw new BankException("转账失败！");
		} finally {
			try {
				ps.close();
				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//转入账户余额增加
		rs=0;
		try {
			sql="update t_user set balance=balance+? where user_id=?";
			ps=conn.prepareStatement(sql);
			ps.setBigDecimal(1,money);
			ps.setInt(2,receiverID);
			rs=ps.executeUpdate();
			if(rs>0){
				returnWord= "转账成功！"+getReturnMessage(sender);
				setLog(sender,"转账",money);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			sender.setBalance(sender.getBalance().add(money));
			throw new BankException("转账失败！");
		} finally {
			try {
				ps.close();
				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnWord;

//		if(bd.addBank(sender,reciever)){
//			System.out.println("转账成功！");
//			//显示余额
//			inquiry(sender);
//			inquiry(reciever);
//			return "转账成功！"+inquiry(sender);
//		}else{
//			System.out.println("本次转账未完成！");
//			return "本次转账未完成！"+inquiry(sender);
//		}
	}


	//写入操作记录
	public void setLog(UserBean user,String logType,BigDecimal amount){
		// 发送sql语句块
		String insert_sql = "insert into t_log(log_type,log_amount,user_id) values(?,?,?)";
		PreparedStatement ps;
		int rs;
		try {
			ps = conn.prepareStatement(insert_sql);
			ps.setString(1, CustomCryptUtil.cryptData(logType));// 设置参数
			ps.setBigDecimal(2, amount);
			ps.setInt(3, user.getId());
			rs=ps.executeUpdate();
			if (rs>0) {
				System.out.println("插入日志成功！");
			}
			// 完成页面的跳转
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	//读取操作记录
	public ArrayList<LogBean> getLogByUser(UserBean user){
		ArrayList<LogBean> logs=new ArrayList<>();
		// 发送sql语句块
		String insert_sql = "select * from t_log where user_id=?";
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conn.prepareStatement(insert_sql);
			ps.setInt(1, user.getId());// 设置参数
			rs=ps.executeQuery();
			while (rs.next()) {
				LogBean log=new LogBean();
				log.setLogid(rs.getInt("log_id"));
				log.setLogtype(CustomCryptUtil.cryptData(rs.getString("log_type")));
				log.setAmount(rs.getBigDecimal("log_amount"));
				log.setUserid(rs.getInt("user_id"));
				log.setLogtime(rs.getTimestamp("log_time"));
				logs.add(log);
			}
			// 完成页面的跳转
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return logs;
	}


	private String getReturnMessage(UserBean user) throws BankException {
		//格式化BigDecimal数据精度
		//#.00 表示两位小数 #.0000四位小数
		DecimalFormat df=new DecimalFormat("0.0000");
		return "账户:"+user.getUsername()+" 当前余额为："+df.format(inquiry(user))+"元";
	}


//
//	//完成交易方法的实现
//	public String exitSystem(UserBean user) throws BankException{
//		UserDaoImpl bd= UserDaoImpl.getInstance();
//		if(bd.addBank(user)){
//			System.out.println("本次交易已完成！");
//			inquiry(user);
//			return "本次交易已完成！"+inquiry(user);
//		}else{
//			System.out.println("本次交易未完成！");
//			return "本次交易未完成！"+inquiry(user);
//		}
//		//显示余额
//	}


//	//添加交易到数据文件（保存交易）的方法（Java8接口的默认方法，使用default关键字修饰，可被继承）
//	public boolean addBank(UserBean... user) throws BankException{//可变参数
//		boolean flag=false;
//		// 发送sql语句块
//		String insert_sql = "insert into t_log(user_name,user_password) values(?,?)";
//		PreparedStatement ps;
//		ResultSet rs;
//		try {
//			if(conn==null) {
//				//加载驱动
//				Class.forName("com.mysql.jdbc.Driver");
//				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem", "root", "19981223zcy");
//			}
//			ps = conn.prepareStatement(insert_sql);
//			ps.setString(1, username);// 设置参数
//			ps.setString(2, password);
//			ps.executeUpdate();
//			// 完成页面的跳转
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return flag;
//	}
}