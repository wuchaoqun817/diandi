package com.lw.common.aspect;


import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lw.common.constant.Constants;
import com.lw.common.constant.ErrorCode;
import com.lw.common.exception.BaseException;
import com.lw.common.redis.RedisService;
import com.lw.common.utils.StringUtils;
import com.lw.common.utils.Validate;
import com.lw.context.CoreContext;
import com.lw.jwt.UserTokenManager;
import com.lw.login.service.LoginService;
import com.lw.session.SessionUser;

import lombok.extern.slf4j.Slf4j;

@Order(1)
@Aspect
@Component
@Slf4j
public class SessionAspect {

    @Autowired
    private RedisService redisService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private LoginService loginService;

    @Pointcut("(execution(public * com.lw..*.controller.*.*(..))"
            + "&& !execution(public * com.lw.controller.WxAuthController.*(..)))")
    public void verify() {
    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
//        String token = request.getHeader("token");
//        if(StringUtils.isEmpty(token)) {
//        	throw new BaseException(ErrorCode.SESSION_TIMEOUT);
//        }
//        String userId = UserTokenManager.getUserId(token);
//        if(StringUtils.isEmpty(userId)) {
//        	throw new BaseException(ErrorCode.SESSION_TIMEOUT);
//        }
		
		SessionUser sessionUser = CoreContext.getInstance().getDefaultUser(); 
//		sessionUser.setOpenid(openid);
		CoreContext.getInstance().setLocalUser(sessionUser);
	    
    }

    @After("verify()")
    public void afterVerify() {
        CoreContext.getInstance().clearLocalUser();
    }
}
