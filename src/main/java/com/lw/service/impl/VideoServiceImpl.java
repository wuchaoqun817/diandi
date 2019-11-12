package com.lw.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.lw.mapper.VideoLikeMapper;
import com.lw.mapper.VideoMapper;
import com.lw.model.Video;
import com.lw.model.VideoLike;
import com.lw.model.VideoLikeKey;
import com.lw.query.VideoQuery;
import com.lw.service.VideoService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoMapper videoMapper;

	@Autowired
	private VideoLikeMapper videoLikeMapper;

	@GetMapping
	public List<Video> listVideos(VideoQuery videoQuery) {
		return videoMapper.selectVideos(videoQuery);
	}

	@Override
	public void saveVideo(Video video) {
		videoMapper.insertSelective(video);
	}

	@Override
	public void updateVideo(Video video) {
		videoMapper.updateByPrimaryKeySelective(video);
	}

	@Override
	public void likeVideoOrNot(VideoLike videoLike) {
		if (videoLike.isLike()) {
			try {
				videoLike.setCreateTime(new Date());
				videoLikeMapper.insertSelective(videoLike);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		} else {
			videoLikeMapper.deleteByPrimaryKey(new VideoLikeKey(videoLike.getUserId(), videoLike.getVideoId()));
		}
		videoMapper.updateLikeNum(videoLike.getVideoId());
	}

	@Override
	public Video getDetails(int id) {
		return videoMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteById(int id) {
		videoMapper.deleteByPrimaryKey(id);
	}

}
