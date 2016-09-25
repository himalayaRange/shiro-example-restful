package cn.com.zygx.backFramework.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import cn.com.zygx.backFramework.db.mybatis.MyBatisRepository;
import cn.com.zygx.backFramework.model.Seckill;

@MyBatisRepository
public interface SeckillDao {
    int deleteByPrimaryKey(Long seckill_id);

    int insert(Seckill record);

    int insertSelective(Seckill record);
   
    int updateByPrimaryKeySelective(Seckill record);

    int updateByPrimaryKey(Seckill record);
    
    /**
     * 减库存
     * @Description
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNum(@Param("seckillId")long seckillId,@Param("killTime")Date killTime);
    
    
    /**
     * 查库存
     * @Description
     * @param seckill_id
     * @return
     */
    Seckill selectByPrimaryKey(Long seckill_id);

    
    /**
     * 根据偏移量查询商品列表
     * @Description
     * @param offet
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset")int offset,@Param("limit")int limit);
    
    /**
     * 使用存储过程执行秒杀
     * @Description
     * @param paramMap
     */
    void killByProcedure(Map<String,Object> paramMap);
    
}