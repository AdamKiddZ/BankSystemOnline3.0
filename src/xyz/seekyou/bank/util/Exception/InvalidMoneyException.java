


package xyz.seekyou.bank.util.Exception;

/**
* @author Adam_Zed
* @version 1.0
* 金额输入不合法异常，例如数值为负等
*/
public class InvalidMoneyException extends BankException {
	 //不带参的构造方法
	 public InvalidMoneyException(){
		 super("金额输入不合法！");
	 }
	 
	 //带参的构造方法
	 public InvalidMoneyException(String message){
		 super(message);
	 }
 }