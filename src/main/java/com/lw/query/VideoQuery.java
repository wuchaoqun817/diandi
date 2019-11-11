package com.lw.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VideoQuery {

	@ApiModelProperty("模块id")
	private Integer blockId;
	
	@ApiModelProperty("模块代码")
	private String blockCode;
	
	@ApiModelProperty("创建者id")
	private String createUserId;
	
	@ApiModelProperty("视频名称")
	private String videoName;
	
}
