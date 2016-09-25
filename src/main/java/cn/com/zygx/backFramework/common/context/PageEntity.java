package cn.com.zygx.backFramework.common.context;

import cn.com.zygx.backFramework.common.base.BaseModel;

/*
 * 分页需要字段
 */
@SuppressWarnings("serial")
public class PageEntity implements BaseModel{

	private Integer startRow;// 页数
	private Integer pageSize;//查询的条数
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	

}
