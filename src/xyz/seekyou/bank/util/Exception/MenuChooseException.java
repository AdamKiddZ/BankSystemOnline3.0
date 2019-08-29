


package xyz.seekyou.bank.util.Exception;

/**
* @author Adam_Zed
* @version 1.0
* 菜单栏选项输入异常
*/
public class MenuChooseException extends BankException {
	 //不带参的构造方法
	 public MenuChooseException(){
		 super("菜单栏选项输入不合法！");
	 }
	 
	 //带参的构造方法
	 public MenuChooseException(String message){
		 super(message);
	 }
 }