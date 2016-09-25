package cn.com.zygx.backFramework.model;

import java.util.Date;

/**
 * 秒杀明细表
 * @author wangyi
 * @date 2016-5-20 上午11:25:01
 */
public class SuccessKilled extends SuccessKilledKey {
    private Byte state;

    private Date create_time;
    
    private Seckill seckill; //多对一

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}

	public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

	@Override
	public String toString() {
		return "SuccessKilled [state=" + state + ", create_time=" + create_time
				+ ", seckill=" + seckill + "]";
	}
    
    
}