package cn.com.zygx.backFramework.model;

import java.util.Date;
/**
 * 秒杀库存表
 * @author wangyi
 * @date 2016-5-20 上午11:24:15
 */
public class Seckill{
    private Long seckill_id;

    private String name;

    private Integer number;

    private Date start_time;

    private Date end_time;

    private Date create_time;

    public Long getSeckill_id() {
        return seckill_id;
    }

    public void setSeckill_id(Long seckill_id) {
        this.seckill_id = seckill_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

	@Override
	public String toString() {
		return "Seckill [seckill_id=" + seckill_id + ", name=" + name
				+ ", number=" + number + ", start_time=" + start_time
				+ ", end_time=" + end_time + ", create_time=" + create_time
				+ "]";
	}
    
    
}