package com.lw.cms.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.lw.common.page.Page;
import com.lw.common.page.PageInfo;
import com.lw.common.page.Result;
import com.lw.common.page.ResultWrapper;
import com.lw.common.utils.Validate;
import com.lw.context.CoreContext;
import com.lw.model.Video;
import com.lw.query.VideoQuery;
import com.lw.service.VideoService;
import com.lw.session.SessionUser;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/cms/videos/")
@RestController
public class CmsVideoController {
	@Autowired
	private VideoService videoService;
	
	@ApiOperation("获取视频列表")
	@GetMapping("")
	public ResultWrapper<PageInfo<Video>> listVideos(@ModelAttribute Page page,@ModelAttribute VideoQuery videoQuery) {
		PageHelper.startPage(page);
		List<Video> videos = videoService.listVideos(videoQuery);
		PageInfo<Video> pageInfo = new PageInfo<Video>(videos);
		return ResultWrapper.success(pageInfo);
	}
	
	@ApiOperation("新增视频")
	@PostMapping("")
	public Result saveVideo(@RequestBody Video video) {
		SessionUser user = CoreContext.getInstance().getLocalUser();
		video.setCreateUserId(user.getUserId());
		video.setCreateUserName(user.getUserName());
		video.setCreateTime(new Date());
		video.setUpdateTime(video.getCreateTime());
		video.setUpdateUserId(user.getUserId());
		videoService.saveVideo(video);
		return Result.success();
	}
	
	@ApiOperation("更新视频")
	@PutMapping("")
	public Result updateVideo(@RequestBody Video video) {
		Validate.isNotNull(video.getId());
		SessionUser user = CoreContext.getInstance().getLocalUser();
		video.setUpdateTime(new Date());
		video.setUpdateUserId(user.getUserId());
		videoService.updateVideo(video);
		return Result.success();
	}
	
	@ApiOperation("视频详情")
	@GetMapping("/{id}")
	public ResultWrapper<Video> getVideo(@PathVariable Integer id){
		Video video = videoService.getDetails(id);
		return ResultWrapper.success(video);
	}
	
	@ApiOperation("删除视频")
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Integer id){
		videoService.deleteById(id);
		return Result.success();
	}
}
