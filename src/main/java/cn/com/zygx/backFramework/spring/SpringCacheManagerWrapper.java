package cn.com.zygx.backFramework.spring;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.sf.ehcache.Ehcache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.cache.support.SimpleValueWrapper;
import com.google.common.collect.Lists;

/**
 *  包装Spring cache抽象
 * <p>User: wangyi
 * <p>Date: 2016-9-9
 * <p>Version: 1.0
 */

public class SpringCacheManagerWrapper implements CacheManager {

	 private org.springframework.cache.CacheManager cacheManager;
	
	/**
	 * 设置缓存管理器
	 * @param @param cacheManager
	 */
	public void setCacheManager(org.springframework.cache.CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public <K,V> Cache<K,V> getCache(String name) {
		 org.springframework.cache.Cache springCache  = cacheManager.getCache(name);
		return new SpringCacheWrapper(springCache);
	}

	static class SpringCacheWrapper implements Cache{

		private org.springframework.cache.Cache springCache;
		
		SpringCacheWrapper(org.springframework.cache.Cache springCache){
			this.springCache=springCache;
		}

		/**
		 * 获取缓存
		 */
		@Override
		public Object get(Object key) throws CacheException {
			Object value = springCache.get(key);
			if(value instanceof SimpleValueWrapper){
				return ((SimpleValueWrapper)value).get();
			}
			return value;
		}

		/**
		 * 添加缓存
		 */
		@Override
		public Object put(Object key, Object value) throws CacheException {
			springCache.put(key, value);
			return value;
		}

		/**
		 * 清楚某个缓存
		 */
		@Override
		public Object remove(Object key) throws CacheException {
			 springCache.evict(key);
			return null;
		}

		/**
		 * 清楚所有缓存
		 */
		@Override
		public void clear() throws CacheException {
			springCache.clear();
		}

		/**
		 * 缓存的大小
		 */
		@Override
		public int size() {
			//判读缓存的类型
			if(springCache.getNativeCache() instanceof Ehcache){
				Ehcache ehcache=(Ehcache)springCache.getNativeCache();
				return ehcache.getSize();
			}
			 throw new UnsupportedOperationException("invoke spring cache abstract size method not supported");
		}

		/**
		 * 缓存的所有键
		 */
		@Override
		public Set keys() {
			if(springCache.getNativeCache() instanceof Ehcache){
				Ehcache ehcache=(Ehcache)springCache.getNativeCache();
				return new HashSet(ehcache.getKeys());
			}
			  throw new UnsupportedOperationException("invoke spring cache abstract keys method not supported");
		}
		
		/**
		 * 缓存集合
		 */
		@Override
		public Collection values() {
			if(springCache.getNativeCache() instanceof Ehcache){
				Ehcache ehcache=(Ehcache)springCache.getNativeCache();
				List keys = ehcache.getKeys();
				if(!CollectionUtils.isEmpty(keys)){
					List values=Lists.newArrayList();
					for(Object key:values){
						Object value = get(key);
						if(value!=null){
							keys.add(value);
						}
					}
					return Collections.unmodifiableList(values);
				}
			}
			throw new UnsupportedOperationException("invoke spring cache abstract values method not supported");
		}
		
	}
	

}
