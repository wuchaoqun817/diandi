package com.lw.common.constant;


/**
 * @description 八位错误码  0000 00 00
 * 第一组（4位）：大模块  1000 公用（系统）   2000 英语相关 2010 数学相关   30开头的是系统管理的
 * 第二组： 小模块  以 00开始
 * 第三组： 具体错误  以01开始
 * @author wuchaoqun
 * @date 2019年1月17日
 */
public enum ErrorCode {

    //公用部分
    //登录异常
    TRANSFER_CLIENT_EXCEPTION("10000001","用户不能跨角色操作，请切换客户端"),
    INVALID_PHONE_NUMBER("10000007", "无效的手机号"),
    NULL_PARAM("10000101", "请求参数为空"),
    SESSION_TIMEOUT("10000004", "会话无效"),
    LOGINFAILED_EXCEPTION("10000002", "密码错误"),
	
	FILE_UPLOAD_FAILED("20000001","文件上传失败");


    private String code;
    private String message;

    private ErrorCode(String code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
