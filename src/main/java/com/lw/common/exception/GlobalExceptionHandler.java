package com.lw.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value = BaseException.class)
	@ResponseBody
	public ErrorInfo handleBaseException(HttpServletRequest request, BaseException exception) {
		ErrorInfo errorInfo = exception.getErrorInfo();
		logger.error("URL:" + request.getRequestURL());
		logger.error("BaseException: " + errorInfo, exception);
		return errorInfo;
	}

}
