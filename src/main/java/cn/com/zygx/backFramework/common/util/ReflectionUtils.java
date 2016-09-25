package cn.com.zygx.backFramework.common.util;
 
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
 
/**
 * 反射的Utils函数集合. 提供访问私有变量,获取泛型类型Class,提取集合中元素的属性等Utils函数.
 * @date: 2016年6月21日
 * @author wangyi
 */
public class ReflectionUtils {
 
    private static Logger logger = LoggerFactory.getLogger(ReflectionUtils.class);
 
    private ReflectionUtils() {
    }
 
    /*********************set get **************************************/
    
    /**
     * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    public static Object getFieldValue(final Object object, final String fieldName) throws NoSuchFieldException, SecurityException {
        Field field = getField(object.getClass(), fieldName);
 
        if (field == null)
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
 
        makeAccessible(field);
 
        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            logger.error("不可能抛出的异常{}", e.getMessage());
        }
        return result;
    }
    
    /**
     * 不仅可以获取类自己的属性，还可以获取父类的属性
     * @Description
     * @param clazz
     * @param filedName
     * @return
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     */
    @SuppressWarnings("rawtypes")
	public static Field getField(Class clazz,String filedName) throws NoSuchFieldException, SecurityException{
    	Field field=null;
    	for(;clazz!=Object.class;clazz=clazz.getSuperclass()){
    		field=clazz.getDeclaredField(filedName);
    	}
    	return field;
    }
    
 
    /**
     * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
     */
    public static void setFieldValue(final Object object, final String fieldName, final Object value) {
        Field field = getDeclaredField(object, fieldName);
 
        if (field == null)
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
 
        makeAccessible(field);
 
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            logger.error("不可能抛出的异常:{}", e.getMessage());
        }
    }
 
    /**
     * 循环向上转型,获取对象的DeclaredField.
     */
    protected static Field getDeclaredField(final Object object, final String fieldName) {
        Assert.notNull(object, "object不能为空");
        return getDeclaredField(object.getClass(), fieldName);
    }
    
 
    
    /**
     * 强制转换fileld可访问.
     */
    protected static void makeAccessible(final Field field) {
        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }
    }
 
    
    
    /**
     * 循环向上转型,获取类的DeclaredField.
     */
    @SuppressWarnings("rawtypes")
	protected static Field getDeclaredField(final Class clazz, final String fieldName) {
        Assert.notNull(clazz, "clazz不能为空");
        Assert.hasText(fieldName, "fieldName");
        for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                // Field不在当前类定义,继续向上转型
            }
        }
        return null;
    }
 

    /**
     * 通过反射,获得定义Class时声明的父类的泛型参数的类型. 如public UserDao extends HibernateDao<User>
     * 
     * @param clazz
     *            The class to introspect
     * @return the first generic declaration, or Object.class if cannot be
     *         determined
     */
    @SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(final Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }
 
    /**
     * 通过反射,获得定义Class时声明的父类的泛型参数的类型. 如public UserDao extends
     * HibernateDao<User,Long>
     * 
     * @param clazz
     *            clazz The class to introspect
     * @param index
     *            the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be
     *         determined
     */
 
    @SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(final Class clazz, final int index) {
 
        Type genType = clazz.getGenericSuperclass();
 
        if (!(genType instanceof ParameterizedType)) {
            logger.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }
 
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
 
        if (index >= params.length || index < 0) {
            logger.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            logger.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }
        return (Class) params[index];
    }
    
    @SuppressWarnings("rawtypes")
 	public  Class getSuperGenericType(Class clazz){
         return getSuperClassGenricType(clazz, 0);
     }
 
    
    /***************************Method*******************************************/
    
    /**
     * 获取数据 
     * TODO
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     */
    public  static Method getMethod(String name,Class<?>... classes){
    	for(Class<?> each : classes){
    		
    		
    	}
    	
    	return null;
    }
    
    /**
     * 
     * @Description 把类对象和类方法作为参数，执行该方法<不经能够访问改类的自己的private方法，还能获取父类中所有的方法>
     * 
     * @param object 方法执行的那个对象. 
     * @param methodName 类的一个方法的方法名. 该方法也可能是私有方法. 
     * @param args 调用该方法需要传入的参数
     * @return
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     */
    @SuppressWarnings("rawtypes")
	public static  Object invoke(Object object,String methodName,Object...args) throws Exception{
    	 //   因为getMethod的参数为Class列表类型，所以要把参数args转化为对应的Class类型。
    	Class[] paramterTypes=new Class[args.length];
    	for(int i=0;i<args.length;i++){
    		paramterTypes[i]=args[i].getClass();
    		System.out.println(paramterTypes[i]);
    	}
    	Method method = getMethod(object.getClass(), methodName,paramterTypes);
    	//执行方法返回执行对象,如果使用getDeclaredMethod，就不能获取父类方法，如果使用getMethod，就不能获取私有方法
    	method.setAccessible(true);
    	method.invoke(object, args);
    	return object;
    }
    
    /***
     * 
     * @Description 通过类名，方法名，方法的参数执行方法
     * 好处是类名和方法可以放在一个配置文件中
     * @param className
     * @param methodName
     * @param args
     * @return
     */
    public static Object invoke(String className,String methodName,Object...args){
    	Object object=null;
    	try {
    		object= Class.forName(className).newInstance();
    		return invoke(object, methodName, args);
    	} catch (Exception e) {
		}
    	return null;
    }
    
    
    /*
     * 如果该类中找不到方法，再从其父类中找，知道找到位置
     * 这个方法的另一个作用是根据一个类名，一个方法名，追踪到并获得此方法
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static Method getMethod(Class clazz,String methodName,Class...parameterTypes){
    	for(;clazz != Object.class; clazz = clazz.getSuperclass()){
    		try{
    			Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
    			return method;
    		}catch(Exception e){
    			//找不到该方法
    		}
    	}
	   return null;
   }  
    
   
    
    /******************************集合篇******************************************/   
    
    /**
     * 提取集合中的对象的属性,组合成List.
     * 
     * @param collection
     *            来源集合.
     * @param propertityName
     *            要提取的属性名.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List fetchElementPropertyToList(final Collection collection, final String propertyName) throws Exception {
 
        List list = new ArrayList();
 
        for (Object obj : collection) {
            list.add(PropertyUtils.getProperty(obj, propertyName));
        }
 
        return list;
    }
 
    /**
     * 提取集合中的对象的属性,组合成由分割符分隔的字符串.
     * 
     * @param collection
     *            来源集合.
     * @param propertityName
     *            要提取的属性名.
     * @param separator
     *            分隔符.
     */
    @SuppressWarnings("rawtypes")
	public static String fetchElementPropertyToString(final Collection collection, final String propertyName, final String separator) throws Exception {
        List list = fetchElementPropertyToList(collection, propertyName);
        return StringUtils.join(list.toArray(), separator);
    }
}