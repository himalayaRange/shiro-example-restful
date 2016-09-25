package cn.com.zygx.backFramework.model;
/**
 * 秒杀明细表联合主键
 * @author wangyi
 * @date 2016-5-20 上午11:24:36
 */
public class SuccessKilledKey {
    private Long seckill_id;

    private Long user_phone;

    public Long getSeckill_id() {
        return seckill_id;
    }

    public void setSeckill_id(Long seckill_id) {
        this.seckill_id = seckill_id;
    }

    public Long getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(Long user_phone) {
        this.user_phone = user_phone;
    }

	@Override
	public String toString() {
		return "SuccessKilledKey [seckill_id=" + seckill_id + ", user_phone="
				+ user_phone + "]";
	}
    
    
}