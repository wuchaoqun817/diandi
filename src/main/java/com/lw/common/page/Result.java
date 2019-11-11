package com.lw.common.page;


import java.io.Serializable;

import com.lw.common.constant.ErrorCode;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author wuchaoqun
 * @date 2019年1月3日
 * @description
 */
public class Result implements Serializable {

	private static final long serialVersionUID = -2287812017364082406L;
	/**
     * 返回结果编码
     */
	@ApiModelProperty("返回结果码")
    private String statusCode;
    /**
     * 返回消息
     */
	@ApiModelProperty("返回消息")
    private String message;

    public Result(String code,String message) {
        this.statusCode = code;
        this.message = message;
    }

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 请求成功时
	 * @return
	 */
	public static Result success() {
    	Result result = new Result("200","请求成功！");
    	return result;
    }
	
	/**
	 * 请求失败时
	 * @param errorCode
	 * @return
	 */
	public static Result error(ErrorCode errorCode) {
		Result result = new Result(errorCode.getCode(),errorCode.getMessage());
    	return result;
	}
}
