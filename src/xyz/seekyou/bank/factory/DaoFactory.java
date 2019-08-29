


package xyz.seekyou.bank.factory;

import java.lang.reflect.Constructor;
import java.util.ResourceBundle;

public class DaoFactory {
//	private static Properties p;

	private static ResourceBundle bundle;
	private static DaoFactory instance;
	//私有构造方法
	private DaoFactory(){
		
	}
	
	static{
//		p=new Properties();
//		System.out.println("#######################"+new File("."));
		String path="classinfo";

		bundle=ResourceBundle.getBundle(path);
//		字符输入流（过滤流），InputStreamReader是Reader的子类，并可以指定编码
//		InputStreamReader in;
//		try{
//			File f=new File(path);
//			ResourceBundle bundle=ResourceBundle.getBundle(path);
////			//使用字符流指定编码为utf-8，过滤文件输入节点流
////			in=new InputStreamReader(new FileInputStream(f),"utf-8");
////			p.load(in);
////			in.close();
//
//		}catch(Exception e){
//			e.printStackTrace();
//		}
	}
	
	public static synchronized DaoFactory getInstance(){
		return instance=(instance==null?new DaoFactory():instance);
	}
	
	public synchronized <T> T factoryMake(String key){
//		String className=p.getProperty(key);
		String className=bundle.getString(key);
		try {
			Constructor c=Class.forName(className).getDeclaredConstructor();
			c.setAccessible(true);
			return (T) c.newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}