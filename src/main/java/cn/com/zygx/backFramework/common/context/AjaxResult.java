package cn.com.zygx.backFramework.common.context;

import java.io.Serializable;

/**
 * @author wangyi
 * @date 2016-7-15 下午2:32:56 
 */
public class AjaxResult implements Serializable {

	private static final long serialVersionUID = 8458207806219748776L;

	private static final Integer AJAX_STATUS_CODE_SUCCESS=0;

	private static final Integer AJAX_STATUS_CODE_WARN=1;
	
	private static final Integer AJAX_STATUS_CODE_ERROR=2;
	
	private Integer statusCode;
	
	private String message;

	
	private AjaxResult() {
		super();
	}

	private AjaxResult(Integer statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	public static AjaxResult success(){
		AjaxResult result=new AjaxResult();
		result.setStatusCode(AJAX_STATUS_CODE_SUCCESS);
		result.setMessage("操作成功");
		return result;
	}
	
	public static AjaxResult error(){
		AjaxResult result=new AjaxResult();
		result.setStatusCode(AJAX_STATUS_CODE_ERROR);
		result.setMessage("操作失败");
		return result;
	}
	
	public static AjaxResult warn(){
		AjaxResult result=new AjaxResult();
		result.setStatusCode(AJAX_STATUS_CODE_WARN);
		result.setMessage("有警告");
		return result;
	}
	
	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
