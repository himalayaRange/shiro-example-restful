package cn.com.zygx.backFramework.common.guava.collection;

import java.util.ArrayList;
import java.util.HashMap;
import cn.com.zygx.backFramework.common.guava.base.Person;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * Collections扩展工具类
 * @author wangyi
   @date 2016-8-2
 *
 */
public class CollectionsUtil {
public static void main(String[] args) {
		instance();
		
	}
	
	//create
	public static void instance(){
		ArrayList<String> newArrayList = Lists.newArrayList("alpha","beta","gamma");
		HashMap<String,Person> newHashMap = Maps.newHashMap();
		
	}
	
}
