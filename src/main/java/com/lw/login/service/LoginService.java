package com.lw.login.service;


import com.lw.model.User;


public interface LoginService {

	/**
	 * 登录
	 * @param loginId
	 * @param password
	 * @param role
	 * @return
	 */
	User login(String loginId, String password, Integer role);

}
