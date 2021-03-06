/**  
 * @author wangyi
 * @date 2016-5-24 下午2:58:31 
 * @version V1.0   
 */ 
package cn.com.zygx.backFramework.dto;

import cn.com.zygx.backFramework.enums.SeckillStatEnum;
import cn.com.zygx.backFramework.model.SuccessKilled;


/** 封装秒杀执行后的结果
 * @author wangyi
 * @date 2016-5-24 下午2:58:31 
 */

public class SeckillExecution {

	private long seckillId;
	
	//秒杀结果
	private int state;
	
	//状态表示
	private String stateInfo;
	
	//成功秒杀对象
	private SuccessKilled successKilled;
	

	public  SeckillExecution(long seckillId, SeckillStatEnum stateEnum,SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.successKilled = successKilled;
	}
	
	public  SeckillExecution(long seckillId, SeckillStatEnum stateEnum) {
		super();
		this.seckillId = seckillId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}

	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state
				+ ", stateInfo=" + stateInfo + ", successKilled="
				+ successKilled + "]";
	}

	
}
