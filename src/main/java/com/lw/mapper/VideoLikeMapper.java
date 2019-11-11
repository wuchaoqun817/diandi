package com.lw.mapper;

import com.lw.model.VideoLike;
import com.lw.model.VideoLikeKey;

public interface VideoLikeMapper {
    /**
     * 根据主键删除实体
     */
    int deleteByPrimaryKey(VideoLikeKey key);

    /**
     * 插入实体（包含空值）
     */
    int insert(VideoLike record);

    /**
     * 插入实体（不包含空值）
     */
    int insertSelective(VideoLike record);

    /**
     * 根据主键查询实体
     */
    VideoLike selectByPrimaryKey(VideoLikeKey key);

    /**
     * 根据主键更新实体（不包含实体）
     */
    int updateByPrimaryKeySelective(VideoLike record);

    /**
     * 根据主键更新实体（包含实体）
     */
    int updateByPrimaryKey(VideoLike record);
}