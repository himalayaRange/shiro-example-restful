package cn.com.zygx.backFramework.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
	final static public SimpleDateFormat DateFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   
	public static void info(Class<?> T,String msg,Exception e){
		final Logger logger=LoggerFactory.getLogger(T);
		String format = DateFormater.format(new Date());
		StringBuffer sb=new StringBuffer().append(format).append(msg);
		if(e!=null){
			sb.append(",异常信息：").append(e.getMessage());
		}
		logger.info(sb.toString());
	}
	
	public static void debug(Class<?> T,String msg,Exception e){
		final Logger logger=LoggerFactory.getLogger(T);
		String format = DateFormater.format(new Date());
		StringBuffer sb=new StringBuffer().append(format).append(msg);
		if(e!=null){
			sb.append("异常信息：").append(e.getMessage());
		}
		logger.debug(sb.toString());
	}
	
	
	public static void log(Class<?> T,String msg){
		final Logger logger=LoggerFactory.getLogger(T);
		String format = DateFormater.format(new Date());
		StringBuffer sb=new StringBuffer().append(format).append(msg);
		logger.info(sb.toString());
	}
		
		
}
