package com.lw.service;

import java.util.List;

import com.lw.model.Video;
import com.lw.model.VideoLike;
import com.lw.query.VideoQuery;

public interface VideoService {
	
	List<Video> listVideos(VideoQuery videoQuery);
	
	void saveVideo(Video video);

	void updateVideo(Video video);
	
	void likeVideoOrNot(VideoLike videoLike);
	
	Video getDetails(int id);
	
	void deleteById(int id);
}
