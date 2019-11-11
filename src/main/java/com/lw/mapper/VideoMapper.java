package com.lw.mapper;

import java.util.List;

import com.lw.model.Video;
import com.lw.query.VideoQuery;

public interface VideoMapper {
    /**
     * 根据主键删除实体
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入实体（包含空值）
     */
    int insert(Video record);

    /**
     * 插入实体（不包含空值）
     */
    int insertSelective(Video record);

    /**
     * 根据主键查询实体
     */
    Video selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新实体（不包含实体）
     */
    int updateByPrimaryKeySelective(Video record);

    /**
     * 根据主键更新实体（包含实体）
     */
    int updateByPrimaryKey(Video record);
    
    List<Video> selectVideos(VideoQuery videoQuery);
    
    void updateLikeNum(int videoId);
}