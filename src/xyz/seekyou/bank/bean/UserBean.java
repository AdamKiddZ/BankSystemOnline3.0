


package xyz.seekyou.bank.bean;

import java.math.BigDecimal;

/**
* @author Adam_Zed
* @version 1.4
* 封装了用户数据的用户实体类
*/
public class UserBean{
	//用户id、用户名、密码、是否冻结、以及账户金额等
	private int id;
	private String username;
	private int flag;
	private BigDecimal balance;

	public int getAdminGrant() {
		return adminGrant;
	}

	public void setAdminGrant(int adminGrant) {
		this.adminGrant = adminGrant;
	}

	private int adminGrant;

	public UserBean(){
	}

	public UserBean(int id,String username,BigDecimal balance){
		this.id=id;
		this.username=username;
		this.balance=balance;
	}


	public void setId(int id) { this.id = id; }

	public int getId() { return this.id; }

	public void setUsername(String username){
		this.username=username;
	}

	public String getUsername(){
		return this.username;
	}

	public void setBalance(BigDecimal balance) { this.balance = balance; }

	public BigDecimal getBalance() { return balance; }

	public int getFlag() { return flag; }

	public void setFlag(int flag) { this.flag = flag; }

	public String toInfoString(){
		return "用户ID："+id+"\t用户名："+username+"\t状态："+(flag==1?"已冻结":"未冻结");
	}
}