package cn.com.zygx.backFramework.service;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class PasswordHelper {

	@Value("#{configProperties['password.algorithmName']}")
    private String algorithmName ;
	
    @Value("#{configProperties['password.hashIterations']}")
    private String hashIterations;
	    
	    

	/**
	 * 使用用户名作为盐值进行加密
	 * @param password
	 * @param username
	 * @return
	 */
	public  String md5(String password,String salt){
		String p=null;
		p=new Md5Hash(password, salt,Integer.valueOf(hashIterations)).toHex();
		return p;
	}
	
	public static boolean isEmpty(Object obj){
		if(obj instanceof String){
			return "".equals(obj);
		}
		if(obj==null) return true;
		else return false;
	}
	
	@SuppressWarnings("resource")
	public static Object getBean(String beanName){
		String[] paths = { "classpath:applicationContext.xml"};
		ApplicationContext ctx = new FileSystemXmlApplicationContext(paths);
		return ctx.getBean(beanName);
	}
	
}
