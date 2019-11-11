package com.lw.session;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SessionUser {
	
	private String sessionKey;

	private String openid;
	
	private String userId;
	
	private String userName;
}
