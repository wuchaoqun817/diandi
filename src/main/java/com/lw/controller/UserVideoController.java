package com.lw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lw.common.page.PageInfo;
import com.lw.common.page.ResultWrapper;
import com.lw.context.CoreContext;
import com.lw.model.Video;
import com.lw.query.VideoQuery;
import com.lw.service.VideoService;
import com.lw.session.SessionUser;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user/videos")
public class UserVideoController {
	
	@Autowired
	private VideoService videoService;

	@ApiOperation("我的视频")
	@GetMapping("")
	public ResultWrapper<PageInfo<Video>> listMyVideos() {
		SessionUser user = CoreContext.getInstance().getLocalUser();
		VideoQuery videoQuery = new VideoQuery();
		videoQuery.setCreateUserId(user.getUserId());
		List<Video> videos = videoService.listVideos(videoQuery);
		PageInfo<Video> pageInfo = new PageInfo<Video>(videos);
		return ResultWrapper.success(pageInfo);
	}
}
