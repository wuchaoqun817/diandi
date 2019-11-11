package com.lw.common.exception;

import com.lw.common.constant.ErrorCode;
import com.lw.common.utils.StringUtils;

public class BaseException extends RuntimeException {

    private ErrorInfo errorInfo;

    public BaseException(ErrorCode errorCode, String message) {
        super(StringUtils.isEmpty(message) ? errorCode.getMessage() : message);
        this.errorInfo = new ErrorInfo(errorCode.getCode(), StringUtils.isEmpty(message) ? errorCode.getMessage() : message);
    }

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorInfo = new ErrorInfo(errorCode.getCode(), errorCode.getMessage());
    }

    public BaseException(String message) {
        super(message);
        this.errorInfo = new ErrorInfo(null, message);
    }

    public BaseException(String code, String message) {
        super(message);
        this.errorInfo = new ErrorInfo(code, message);
    }

    public ErrorInfo getErrorInfo() {
        return this.errorInfo;
    }
}
