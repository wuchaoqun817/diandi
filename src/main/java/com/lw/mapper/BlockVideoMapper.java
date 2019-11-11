package com.lw.mapper;

import com.lw.model.BlockVideoKey;

public interface BlockVideoMapper {
    /**
     * 根据主键删除实体
     */
    int deleteByPrimaryKey(BlockVideoKey key);

    /**
     * 插入实体（包含空值）
     */
    int insert(BlockVideoKey record);

    /**
     * 插入实体（不包含空值）
     */
    int insertSelective(BlockVideoKey record);
}