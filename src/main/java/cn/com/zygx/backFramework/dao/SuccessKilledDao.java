package cn.com.zygx.backFramework.dao;

import org.apache.ibatis.annotations.Param;
import cn.com.zygx.backFramework.db.mybatis.MyBatisRepository;
import cn.com.zygx.backFramework.model.SuccessKilled;
import cn.com.zygx.backFramework.model.SuccessKilledKey;

@MyBatisRepository
public interface SuccessKilledDao {
    int deleteByPrimaryKey(SuccessKilledKey key);

    int insert(SuccessKilled record);

    int insertSelective(SuccessKilled record);

    SuccessKilled selectByPrimaryKey(SuccessKilledKey key);

    int updateByPrimaryKeySelective(SuccessKilled record);

    int updateByPrimaryKey(SuccessKilled record);
    
    /**
     * 插入购买明细，可过滤重复
     * @Description
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKilled(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
    
    /**
     * 根据id查询successkilled并携带秒杀产品对象实体
     * @Description
     * @param seckilId
     * @return
     */
    SuccessKilled queryByIdWithseckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}