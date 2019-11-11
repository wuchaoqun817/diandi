package com.lw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lw.common.page.ResultWrapper;
import com.lw.model.Block;
import com.lw.service.BlockService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/blocks")
public class BlockController {

	@Autowired
	private BlockService blockService;

	@ApiOperation("列出模块列表  parentCode：index 为首页模块列表")
	@GetMapping("")
	public ResultWrapper<List<Block>> listBlocks(
			@RequestParam(value = "parentCode", required = false) String parentCode) {
		Block con = new Block();
		con.setParentCode(parentCode);
		List<Block> blocks = blockService.listBlocks(con);
		return ResultWrapper.success(blocks);
	}
}
