package com.lw.mapper;

import java.util.List;

import com.lw.model.Block;

public interface BlockMapper {
    /**
     * 根据主键删除实体
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入实体（包含空值）
     */
    int insert(Block record);

    /**
     * 插入实体（不包含空值）
     */
    int insertSelective(Block record);

    /**
     * 根据主键查询实体
     */
    Block selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新实体（不包含实体）
     */
    int updateByPrimaryKeySelective(Block record);

    /**
     * 根据主键更新实体（包含实体）
     */
    int updateByPrimaryKey(Block record);
    
    /**
     * 根据条件查询
     * @param condition
     * @return
     */
    List<Block> listBlocks(Block condition);
}