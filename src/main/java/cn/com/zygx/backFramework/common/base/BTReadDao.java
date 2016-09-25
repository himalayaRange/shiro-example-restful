package cn.com.zygx.backFramework.common.base;

import java.util.List;

public interface BTReadDao<T extends BaseModel> {
	/**
	 * @Description 根据id查询实体
	 * @param id
	 * @return BaseModel
	 * @throws Exception
	 * @author chenh 
	 * @date 2015年12月3日 下午342:22  
	 */
	public T selectByPrimaryKey(Integer id) throws Exception;
	
	/**
	 * @Description: 根据条件查询所有实体记录数
	 * @param entity
	 * @return Integer
	 * @throws Exception
	 * @author chenh 
	 * @date 2015年12月3日 下午3:52:05  
	 */
	public Integer count(T entity) throws Exception;
	
	/**
	 * @Description: 查询所有实体记录
	 * @param entity
	 * @return List<BaseModel>
	 * @throws Exception
	 * @author chenh 
	 * @date 2015年12月3日 下午3:52:05  
	 */
	public List<T> query(T entity) throws Exception;

	/**
	 * @Description 分页查询所有实体记录
	 * @param entity
	 * @return List<T>
	 * @throws Exception
	 * @author chenh 
	 * @date 2015年12月3日 下午3:52:05  
	 */
	public List<T> queryPage(T entity) throws Exception;
}