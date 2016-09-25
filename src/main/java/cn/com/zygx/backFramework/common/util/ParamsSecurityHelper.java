/**  
 * @author wangyi
 * @date 2016-6-20 下午3:12:44 
 * @version V1.0   
 */ 
package cn.com.zygx.backFramework.common.util;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;



/**
 * url工具
 * @author wangyi
 * @date 2016-6-20 下午3:12:44 
 */
public class ParamsSecurityHelper<T> {
	
	/**
	 * 指定对象进行属性加密
	 * @Description
	 * @param t 对象类型
	 * @param params String：属性名称；Class:属性的类对象
	 * @return
	 */
	 @SuppressWarnings("rawtypes")
	public   T initParamsSingle(T t,List<Map<String, Object>>  params) {
		 for(Map<String,Object> temp:params){
			 //属性
			 String property=(String)temp.get("property");
			 //值
			 Object value=(Object)temp.get("value");
			 Class cla=(Class)t.getClass();
			 //首字母大写高效
			 char[] cs = property.toCharArray();
			 cs[0]-=32;
			 Method[] methods = cla.getDeclaredMethods();
			 for(int i=0;i<methods.length;i++){
				 Method method = methods[i];
				 if(method.getName().equals(new StringBuilder().append("set").append(String.valueOf(cs)).toString())){
					try{
						//给对象赋值
						method.invoke(t,value);
					}catch(Exception e){
						e.printStackTrace();
					}
				 }
			 }
			 
		 }
		 return  t;
	 }
	 
	 
}
