package com.lw.jwt;

import com.lw.common.utils.StringUtils;

/**
 * 维护用户token
 */
public class UserTokenManager {
	public static String generateToken(String userId) {
        JwtHelper jwtHelper = new JwtHelper();
        return jwtHelper.createToken(userId);
    }
    public static String getUserId(String token) {
    	JwtHelper jwtHelper = new JwtHelper();
    	String userId = jwtHelper.verifyTokenAndGetUserId(token);
    	if(StringUtils.isEmpty(userId)){
    		return null;
    	}
        return userId;
    }
}
