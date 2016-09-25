package cn.com.zygx.backFramework.common.guava.collection;

import java.util.Collection;
import java.util.Map.Entry;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;

/**
 * 内容：不可变集合
 * 作用：进行防御性编程和性能的提升
 *  当对象被不可信的库调用时，不可变形式是安全的；
	不可变对象被多个线程调用时，不存在竞态条件问题
	不可变集合不需要考虑变化，因此可以节省时间和空间。所有不可变的集合都比它们的可变形式有更好的内存利用率（分析和测试细节）；
	不可变对象因为有固定不变，可以作为常量来安全使用。
	注意：google的guava中所有的可不变集合不接受null值
 * @author wangyi
 * @date 2016-8-1 下午5:24:28 
 */
public class ImmutableSetUtil {
	
	private static final ImmutableSet<String> COLOR_NAMES=ImmutableSet.of(
					"red",
			         "orange",
			         "yellow",
			         "green",
			         "blue",
			         "purple"
			);
	
	private static final ImmutableList<String> NAME_LIST=ImmutableList.of(
				"张三","李四","王五","赵六"
			);
	
	//Map集合创建方法1：
	private static final ImmutableMap<String, String> KV_MAP=ImmutableMap.of("name","张三","age","25","address","杭州西湖区"); 
	//Map集合创建方法2：
	private static final ImmutableMap<String, String> BUILD_MAP=new ImmutableMap.Builder<String,String>().put("name","李四").put("age","26").put("address","上海闵行区").build();
	
	public static void main(String[] args) {
		immutableset(COLOR_NAMES);
		
		immutableList(NAME_LIST);
		
		immutableMap(KV_MAP,BUILD_MAP);
	}
	
	/*
	 * Set的不可变版本
	 */
	public static void immutableset(Collection<String> collection){
		ImmutableList<String> copyOf = ImmutableList.copyOf(collection).asList(); //asList将集合转化为视图再从里面取值
		for(String item:copyOf){
			System.out.println(item);
		}
	}
	
	/*
	 * List的不可变版本
	 */
	public static void immutableList(Collection<String> collection){
		ImmutableList<String> copyOf = ImmutableList.copyOf(collection).asList();
		for(String item:copyOf){
			System.out.println(item);
		}
	}
	
	/*
	 * Map的版本
	 */
	public static void immutableMap(ImmutableMap<String, String> kvMap,ImmutableMap<String, String> kvMap1){
		ImmutableMap<String,String> copyOf = ImmutableMap.copyOf(kvMap);
		//方式1遍历
		ImmutableSetMultimap<String, String> asMultimap = copyOf.asMultimap();
		ImmutableSet<Entry<String,String>> entries = asMultimap.entries();
		for(Entry<String,String> item:entries){
			System.out.println(item.getKey()+"--->"+item.getValue());
		}
		
		//方式二遍历
		ImmutableMap<String, String> copyOf2 = ImmutableMap.copyOf(kvMap1);
		ImmutableSet<Entry<String,String>> entrySet = copyOf2.entrySet();
		for(Entry<String,String> set:entrySet){
			System.out.println(set.getKey()+"-->"+set.getValue());
		}
		
	}
	
	
}
