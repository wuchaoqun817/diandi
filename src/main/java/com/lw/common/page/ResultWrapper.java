package com.lw.common.page;

import com.lw.common.constant.ErrorCode;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author wuchaoqun
 * @date 2019年1月3日
 * @description
 * @param <T>
 */
public class ResultWrapper<T> extends Result {

	private static final long serialVersionUID = 8227307338710971177L;

	@ApiModelProperty("返回数据")
	public T data;

	/**
	 * 请求成功，并带上数据
	 * 
	 * @param data
	 */
	public ResultWrapper(T data) {
		super("200", "请求成功！");
		this.data = data;
	}
	
	/**
	 * 请求成功
	 * @param data
	 * @return
	 */
	public static <T> ResultWrapper<T> success(T data) {
        ResultWrapper<T> result = new ResultWrapper<T>(data);
        return result;
    }

	/**
	 * 请求失败，并带上数据
	 * 
	 * @param errorCode
	 * @param data
	 */
	public ResultWrapper(ErrorCode errorCode, T data) {
		super(errorCode.getCode(), errorCode.getMessage());
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
