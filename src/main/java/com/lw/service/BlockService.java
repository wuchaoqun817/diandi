package com.lw.service;

import java.util.List;

import com.lw.model.Block;
import com.lw.model.BlockVideoKey;

public interface BlockService {

	List<Block> listBlocks(Block block);
	
	void addVideoToBlock(BlockVideoKey blockVideoKey);
}
