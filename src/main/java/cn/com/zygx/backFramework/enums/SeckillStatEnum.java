/**   * @author wangyi
 * @date 2016-5-24 下午6:34:45 
 * @version V1.0   
 */ 
package cn.com.zygx.backFramework.enums;

/**
 * @author wangyi
 * @date 2016-5-24 下午6:34:45 
 */
public enum SeckillStatEnum {
	
	SUCCESS(1,"秒杀成功"),
	END(0,"秒杀结束"),
	REPEAR_KILL(-1,"重复秒杀"),
	INNNER_ERROR(-2,"系统异常"),
	REWRITE(-3,"数据篡改");
	
	private int state;
	
	private String stateInfo;

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

	private SeckillStatEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	
	public static SeckillStatEnum stateOf(int index){
		for(SeckillStatEnum state:values()){
			if(state.getState()==index){
				return state;
			}
		}
		return null;
	}
	

}
