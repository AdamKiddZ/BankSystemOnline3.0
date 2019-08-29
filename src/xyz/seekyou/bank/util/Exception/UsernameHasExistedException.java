


package xyz.seekyou.bank.util.Exception;

/**
* @author Adam_Zed
* @version 1.0
* 用户名已存在异常
*/
public class UsernameHasExistedException extends BankException {
	 //不带参的构造方法
	 public UsernameHasExistedException(){
		 super("该用户已存在！");
	 }
	 
	 //带参的构造方法
	 public UsernameHasExistedException(String message){
		 super(message);
	 }
 }