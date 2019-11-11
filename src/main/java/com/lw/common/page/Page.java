package com.lw.common.page;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分页用
 * 
 * @author wuchaoqun
 *
 */
public class Page {

	@ApiModelProperty("页面大小")
	private int pageSize;

	@ApiModelProperty("页码，从1开始")
	private int pageNum = 1;

	public int getPageSize() {
		return pageSize > 50 ? 50 : pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}
