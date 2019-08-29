package xyz.seekyou.bank.bean;


//package xyz.seekyou.bank.bean;
//
//import java.math.BigDecimal;
//
//public final class MoneyBean{
//	//钱包实体类
//	//使用BigDecimal类型存放用户账户金额
//	private BigDecimal value;
//	public MoneyBean(double d){
//		this.value=new BigDecimal(d);
//	}
//
//	//查询余额
//	public BigDecimal getMoney(){
//		return this.value;
//	}
//
//	//加钱
//	public synchronized void plus(BigDecimal bd){
//		this.value=this.value.add(bd);
//	}
//
//	//扣钱
//	public synchronized void minus(BigDecimal bd){
//		this.value=this.value.subtract(bd);
//	}
//}