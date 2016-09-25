package cn.com.zygx.backFramework.common.guava.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import cn.com.zygx.backFramework.common.guava.base.ObjectsUtil;
import cn.com.zygx.backFramework.common.guava.base.Person;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;
import com.google.common.collect.TreeMultiset;

/**
 * url：http://ifeve.com/google-guava/
 * 新集合类型，与jdk中的集合共存
 * Guava集合非常精准地遵循了JDK接口契约。
 * @author wangyi
 * @date 2016-8-2 上午10:25:29 
 */
public class MultiCollectionUtil {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		/*multiset();
		linkedHashMutiSet();*/
		
		//list_multiMap();
		//set_mutilMap();
	
		table();
	}
	
	/*********************************MultiSet**************************************************/
	
	/**
	 * @Description: MultiSet不是继承自Set接口，而是继承了Collection接口，所以有如下特点：
	 * 特点：元素无序可重复（如同意一篇文章的各个单词数量）
	 * MultiSet可以看成是无需的ArrayList,可以重复的HashSet
	 * ①当把Multiset看成普通的Collection时，它表现得就像无序的ArrayList：
		add(E)添加单个给定元素
		iterator()返回一个迭代器，包含Multiset的所有元素（包括重复的元素）
		size()返回所有元素的总个数（包括重复的元素）
		②当把MultiSet看成 Map<E,Integer>时，提供符合性能期望的查询
		count(Object)返回给定元素的计数。HashMultiset.count的复杂度为O(1)，TreeMultiset.count的复杂度为O(log n)。
		entrySet() Multiset中不重复元素的集合，返回Set<Multiset.Entry<E>>，和Map的entrySet类似。
		elementSet()返回所有不重复元素的Set<E>，和Map的keySet()类似。
		所有Multiset实现的内存消耗随着不重复元素的个数线性增长。
	 */
	public static  void multiset(){
		final Multiset<String> multiset=HashMultiset.create();
		String demo="this is a story, there is a good girl in the story.";
		Iterable<String> words = Splitter.onPattern("[^a-z]{1,}").omitEmptyStrings().trimResults().split(demo);
		for(String item:words){
			multiset.add(item);
		}
		//看成① 迭代重复元素
		System.out.println("数量："+multiset.size());
		Iterator<String> iterator = multiset.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		System.err.println("-----------------------------------------------");
		//看成② 不迭代重复元素
		for(Object element:multiset.elementSet()){
			System.out.println((String)element+"-----"+multiset.count(element));
		}
		Set<Entry<String>> entrySet = multiset.entrySet();
		for(Entry<String> item:entrySet){
			System.out.println(item.getElement()+"*****"+item.getCount());
		}
	}
	
	//链表形式的hashSet（按添加的顺序）和TreeMap(自然顺序)
	public static void linkedHashMutiSet(){
		final LinkedHashMultiset<String> linked=LinkedHashMultiset.create();
		final TreeMultiset<String> tree=TreeMultiset.create();
		String demo="this is a story, there is a good girl in the story.";
		Iterable<String> words = Splitter.onPattern("[^a-z]{1,}").omitEmptyStrings().trimResults().split(demo);
		for(String item:words){
			linked.add(item);
			tree.add(item);
		}
		Iterator<String> iterator = linked.iterator();
		Iterator<String> iterator1 = tree.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		while(iterator1.hasNext()){
			System.out.println(iterator1.next());
		}
	}
	
	/****************************************MultiMap***************************************************/
	
	/**
	 * 理解：
	 * 老代码: Map<K,List<V>> Map<K,Set<V>> 可以理解成a -> 1 a -> 2 a ->4 b -> 3 c -> 5
	 * 很少会直接使用Multimap接口，更多时候你会用ListMultimap或SetMultimap接口，它们分别把键映射到List或Set。
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void list_multiMap() throws IllegalArgumentException, IllegalAccessException{
		ArrayListMultimap<String, Person> list_map = ArrayListMultimap.create();
		for(int i=20;i<=30;i++){
			Person person=new Person();
			person.setUsername(i+"");
			person.setPassword("pwd_"+i);
			person.setAddress("西湖区_"+i);
			list_map.put("info", person);
		}
		System.out.println("list_map_size:  "+list_map.size());
		System.out.println("list_map_keys:  "+list_map.keys());
		//1.调用Multimap.get(key)会返回这个键对应的值的集合的视图（view），没有对应集合就返回空集合。
		//2.对于ListMultimap来说，这个方法会返回一个List，对于SetMultimap来说，这个方法就返回一个Set。修改数据是通过修改底层Multimap来实现
		//list_map.clear(); 清空集合
		//list_map.remove("info", Object); //移除某个对象
	    List<Person> list = list_map.get("info");
	    for(Person p:list){
	    	String sys = ObjectsUtil.stringhelper(p);
	    	System.out.println(sys);
	    }
	    
	    System.err.println("====================================================================");
	    
	    //asMap为Multimap<K, V>提供Map<K,Collection<V>>形式的视图。返回的Map支持remove操作，并且会反映到底层的Multimap，但它不支持put或putAll操作。
	    //ma在数据量比较大的时候推荐使用如下方式
	    Map<String, Collection<Person>> asMap = list_map.asMap();
	    Set<java.util.Map.Entry<String,Collection<Person>>> entrySet = asMap.entrySet();
	    for(java.util.Map.Entry<String,Collection<Person>> entry:entrySet){
	    	System.out.println("key:"+entry.getKey()+"   value:");
	    	for(Person pp:entry.getValue()){
	    		System.out.println(ObjectsUtil.stringhelper(pp));
	    	}
	    }
	    
	    System.err.println("====================================================================");
	    //entries  核心思想：用Collection<Map.Entry<K, V>>返回Multimap中所有”键-单个值映射”——包括重复键。（对ListMultimap，返回的是List）
	    Collection<java.util.Map.Entry<String,Person>> entries = list_map.entries();
	    for(java.util.Map.Entry<String,Person> person:entries){
	    	System.out.println("list_map_key:"+person.getKey());
	    	System.out.println("list_map_value:"+ObjectsUtil.stringhelper(person.getValue()));
	    	
	    }
	    //keySet用Set表示Multimap中所有不同的键。
	    Set<String> keySet = list_map.keySet();
	    for(String key:keySet){
	    	System.out.println("key:"+key+"   values:");
	    	for(Person person:list_map.get(key)){
	    		System.out.println(ObjectsUtil.stringhelper(person));
	    	}
	    }
	}
	//处理类似 Map<K,Set<V>>结构的集合数据类型
	public static void set_mutilMap() throws IllegalArgumentException, IllegalAccessException{
		HashMultimap<String, Person> set_map = HashMultimap.create();
		for(int i=20;i<=30;i++){
			Person person=new Person();
			person.setUsername(i+"");
			person.setPassword("pwd_"+i);
			person.setAddress("西湖区_"+i);
			set_map.put("info", person);
		}
		System.out.println("set_map_size:  "+set_map.size());
		System.out.println("set_map_keys:  "+set_map.keys());
		Set<Person> set = set_map.get("info"); //通过键返回一个set对象
		for(Person key:set){
			System.out.println(ObjectsUtil.stringhelper(key));
		}
		 System.err.println("====================================================================");
		
		 //asMap
		Map<String, Collection<Person>> asMap = set_map.asMap();
		Set<java.util.Map.Entry<String, Collection<Person>>> entrySet = asMap.entrySet();
		for(java.util.Map.Entry<String, Collection<Person>> item:entrySet){
			System.out.println("key:"+item.getKey()+"  value:");
			for(Person p:item.getValue()){
				System.out.println(ObjectsUtil.stringhelper(p));
			}
		}
		 System.err.println("====================================================================");
		
		 //entries  核心思想：用Collection<Map.Entry<K, V>>返回Multimap中所有”键-单个值映射”——包括重复键。（对SetMultimap，返回的是Set）
		 Set<java.util.Map.Entry<String,Person>> entries = set_map.entries();
		 for(java.util.Map.Entry<String,Person> item:entries){
			 System.out.println("key:"+item.getKey());
			 System.out.println("value:"+ObjectsUtil.stringhelper(item.getValue()));
		 }
	}
	
	
	/*****************************Table*****************************************/
	//二维数组索引集合,如位置的排序，Table提供多视图的解析
	public static void table(){
		 HashBasedTable<Integer, Integer, String> table = HashBasedTable.create();
		 table.put(1, 2, "张三");
		 table.put(1, 3, "李四");
		 table.put(2, 1, "王五");
		 table.put(2, 3, "周七");
		 table.put(3, 3, "赵六");
		 //返回第2排同学姓名  row
		 Map<Integer, String> row = table.row(2);
		 Set<java.util.Map.Entry<Integer, String>> entrySet = row.entrySet();
		 for(java.util.Map.Entry<Integer, String> item:entrySet){
			 System.out.println("row_key:"+item.getKey()+"---"+item.getValue());
		 }
		 
		 //返回第3列同学的名字 column
		 Map<Integer, String> column = table.column(3);
		 Set<java.util.Map.Entry<Integer, String>> entrySet2 = column.entrySet();
		 for(java.util.Map.Entry<Integer, String> item:entrySet2){
			 System.out.println("colum_key:"+item.getKey()+"---"+item.getValue());
		 }
		 
		 //返回行的集合
		 Set<Integer> rowKeySet = table.rowKeySet();
		 for(Integer r:rowKeySet){
			 System.out.println(r);
		 }
		//返回列的集合
		 Map<Integer, Map<Integer, String>> rowMap = table.rowMap();
		 Set<java.util.Map.Entry<Integer,Map<Integer,String>>> entrySet3 = rowMap.entrySet();
		 for(java.util.Map.Entry<Integer,Map<Integer,String>> item:entrySet3){
			 System.out.println("key:"+item.getKey()+" vaue:"+item.getValue());
		 }
	}
}
