package cn.com.zygx.backFramework.common.base;

import java.util.List;
import java.util.Map;

public interface BaseService<T extends BaseModel>{
	/**
	 * @Description 物理删除实体
	 * @param entity
	 * @return Integer id
	 * @
	 * @author chenh 
	 * @date 2015年12月3日 下午342:22  
	 * */
	public Integer deleteWL(Integer id) ;
	
	/**
	 * @Description 状态删除实体
	 * @param entity
	 * @return Integer id
	 * @
	 * @author chenh 
	 * @date 2015年12月3日 下午342:22  
	 * */
	public Integer delete(T entity) ;
	
	/**
	 * @Description 新增实体
	 * @param entity
	 * @return BaseModel
	 * @
	 * @author chenh 
	 * @date 2015年12月3日 下午342:22  
	 * */
	public Integer insert(T entity) ;
	
	
	/**
	 * @Description 新增实体
	 * @param entity
	 * @return BaseModel
	 * @
	 * @author chenh 
	 * @date 2015年12月3日 下午342:22  
	 * */
	public Integer insertSelective(T entity) ;
	
	
	/**
	 * @Description 修改实体
	 * @param entity
	 * @return BaseModel
	 * @
	 * @author chenh 
	 * @date 2015年12月3日 下午342:22  
	 * */
	public Integer update(T entity) ;
	
	/**
	 * @Description 根据id查询实体
	 * @param id
	 * @return BaseModel
	 * @
	 * @author chenh 
	 * @date 2015年12月3日 下午342:22  
	 */
	public T selectByPrimaryKey(Integer id) ;
	
	/**
	 * @Description: 查询所有实体记录
	 * @param entity
	 * @return List<BaseModel>
	 * @
	 * @author chenh 
	 * @date 2015年12月3日 下午3:52:05  
	 */
	public List<T> query(T entity) ;

	/**
	 * @Description 分页查询所有实体记录
	 * @param entity
	 * @return Map<String, Object>
	 * @
	 * @author chenh 
	 * @date 2015年12月3日 下午3:52:05  
	 */
	public Map<String, Object> queryPage(T entity) ;
}