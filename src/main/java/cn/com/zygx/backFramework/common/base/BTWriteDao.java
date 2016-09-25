package cn.com.zygx.backFramework.common.base;

public interface BTWriteDao<T extends BaseModel> {
	/**
	 * @Description 物理删除实体
	 * @param entity
	 * @return Integer id
	 * @throws Exception
	 * @author chenh 
	 * @date 2015年12月3日 下午342:22  
	 * */
	public Integer deleteWL(Integer id) throws Exception;
	
	/**
	 * @Description 状态删除实体
	 * @param entity
	 * @return Integer id
	 * @throws Exception
	 * @author chenh 
	 * @date 2015年12月3日 下午342:22  
	 * */
	public Integer delete(T entity) throws Exception;
	
	/**
	 * @Description 新增实体
	 * @param entity
	 * @return Integer
	 * @throws Exception
	 * @author chenh 
	 * @date 2015年12月3日 下午342:22  
	 * */
	public Integer insert(T entity) throws Exception;
	
	/**
	 * @Description 新增实体
	 * @param entity
	 * @return Integer
	 * @throws Exception
	 * @author chenh 
	 * @date 2015年12月3日 下午342:22  
	 * */
	public Integer insertSelective(T entity) throws Exception;
	
	/**
	 * @Description 修改实体
	 * @param entity
	 * @return Integer
	 * @throws Exception
	 * @author chenh 
	 * @date 2015年12月3日 下午342:22  
	 * */
	public Integer update(T entity) throws Exception;
}