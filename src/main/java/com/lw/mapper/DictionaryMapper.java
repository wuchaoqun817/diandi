package com.lw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lw.model.Dictionary;

public interface DictionaryMapper {
	int insert(Dictionary record);

	int insertSelective(Dictionary record);

	List<Dictionary> queryAll(Dictionary dictionary);

	int updateByCondition(@Param("condition") Dictionary condition, @Param("target") Dictionary target);

	int delete(Dictionary dictionary);

	Dictionary queryOneByItemId(String itemId);
}