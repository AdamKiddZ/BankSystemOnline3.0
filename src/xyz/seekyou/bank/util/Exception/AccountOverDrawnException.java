


package xyz.seekyou.bank.util.Exception;

/**
* @author Adam_Zed
* @version 1.0
* 账户余额不足异常
*/
public class AccountOverDrawnException extends BankException {
	 //不带参的构造方法
	 public AccountOverDrawnException(){
		 super("当前账户余额不足！");
	 }
	 
	 //带参的构造方法
	 public AccountOverDrawnException(String message){
		 super(message);
	 }
 } 