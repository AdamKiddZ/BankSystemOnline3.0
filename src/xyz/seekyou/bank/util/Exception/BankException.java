


package xyz.seekyou.bank.util.Exception;

/**
* @author Adam_Zed
* @version 1.0
*/
public class BankException extends Exception{
	//异常的描述
	private String message;
	
	//不带参的构造方法
	public BankException(){
		this.message="异常发生了！";
	}
	
	//带参的构造方法
	public BankException(String message){
		this.message=message;
	}
	
	//获取异常的描述
	public String getMessage(){
		return this.message;
	}
}