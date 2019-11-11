package com.lw.context;


import com.lw.session.SessionUser;

/**
 * 
 * @author wuchaoqun
 * @date 2018年12月28日
 * @description 上下文（用户信息等）
 */
public class CoreContext {

	private static CoreContext instance = null;

	private static final ThreadLocal<SessionUser> LOCAL_USER = new ThreadLocal<SessionUser>();
	
	private static SessionUser defaultUser;
	
	public synchronized static CoreContext getInstance() {
		if (instance == null) {
			instance = new CoreContext();
		}
		return instance;
	}

	public void setLocalUser(SessionUser sessionUser) {
		LOCAL_USER.set(sessionUser);
	}

	public SessionUser getLocalUser() {
		return LOCAL_USER.get();
	}

	public void clearLocalUser() {
		LOCAL_USER.remove();
	}

	public void setDefaultUser (SessionUser user) {
		defaultUser = user;
	}
	
	public SessionUser getDefaultUser() {
		if (defaultUser == null) {
			defaultUser = new SessionUser();
			defaultUser.setUserId("2");
			defaultUser.setUserName("苍龙");
		}
		return defaultUser;
	}
}
