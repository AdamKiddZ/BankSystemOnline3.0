


package xyz.seekyou.bank.Dao;

import xyz.seekyou.bank.bean.LogBean;
import xyz.seekyou.bank.bean.UserBean;
import xyz.seekyou.bank.util.Exception.BankException;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface iManagerDao{
	//查询方法的声明
	public BigDecimal inquiry(UserBean user) throws BankException;
	//取钱方法的声明
	public String withdraw(UserBean user, BigDecimal money) throws BankException;
	//存钱方法的声明
	public String deposit(UserBean user, BigDecimal money) throws BankException;
	//转账方法的声明
	public String transfer(UserBean sender, int receiverID, BigDecimal money) throws BankException;
    //添加操作记录方法的声明
    public void setLog(UserBean user, String logType, BigDecimal amount) throws BankException;
    //获取操作记录方法的声明
    public ArrayList<LogBean> getLogByUser(UserBean user) throws BankException;
}