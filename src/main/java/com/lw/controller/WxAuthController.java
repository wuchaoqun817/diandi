package com.lw.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.lw.common.page.Result;
import com.lw.common.page.ResultWrapper;
import com.lw.common.utils.HttpUtil;
import com.lw.context.CoreContext;
import com.lw.dto.WxLoginDTO;
import com.lw.jwt.UserTokenManager;
import com.lw.session.SessionUser;
import com.lw.wx.config.WxConfig;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/wx/auth")
public class WxAuthController {

	@ApiOperation("微信登录")
	@PostMapping("login")
	public ResultWrapper<String> loginByWeixin(HttpServletRequest request, @RequestBody WxLoginDTO wxLoginDTO) {
		Map<String, String> params = new HashMap<>(8);
		params.put("appid", "wx812f1224f7ec6484");
		params.put("secret", "80ef0bc9a1404d242942cfb00a620d6d");
		params.put("js_code", wxLoginDTO.getCode());
		params.put("grant_type", "authorization_code");
		System.out.println(params);
		String str = HttpUtil.sendPost(WxConfig.JSCODE_TO_SESSION_URL, params);
		Map<?, ?> map = JSON.parseObject(str, HashMap.class);
		SessionUser user = new SessionUser();
		user.setOpenid((String) map.get("openid"));
		user.setSessionKey((String) map.get("session_key"));
		String token = UserTokenManager.generateToken(user.getOpenid() + user.getSessionKey());
		return ResultWrapper.success(token);
	}

	@ApiOperation("修改默认用户信息")
	@GetMapping("default-user")
	public Result modify(@RequestParam("userId") String userId, @RequestParam("userName") String userName) {
		SessionUser user = new SessionUser();
		user.setUserId(userId);
		CoreContext.getInstance().setDefaultUser(user);
		return Result.success();
	}
}
