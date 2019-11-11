package com.lw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lw.mapper.BlockMapper;
import com.lw.mapper.BlockVideoMapper;
import com.lw.model.Block;
import com.lw.model.BlockVideoKey;
import com.lw.service.BlockService;

@Service
public class BlockServiceImpl implements BlockService{

	@Autowired
	private BlockMapper blockMapper;
	
	@Autowired
	private BlockVideoMapper blockVideoMapper;
	
	@Override
	public List<Block> listBlocks(Block block) {
		return blockMapper.listBlocks(block);
	}

	@Override
	public void addVideoToBlock(BlockVideoKey blockVideoKey) {
		blockVideoMapper.insertSelective(blockVideoKey);
	}

}
