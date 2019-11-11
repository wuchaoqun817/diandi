package com.lw.model;

import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 16439
 * @date 2019年09月05日
 * @description 视频表
 */
@Getter
@Setter
public class Video {
    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("视频名称")
    private String name;

    @ApiModelProperty("视频存储路径")
    private String storagePath;

    @ApiModelProperty("视频大小")
    private Long videoSize;

    @ApiModelProperty("创建者id")
    private String createUserId;

    @ApiModelProperty("创建者名称")
    private String createUserName;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("状态")
    private Byte status;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("是否精选")
    private boolean selected;
    
    @ApiModelProperty("封面")
    private String cover;
    
    @ApiModelProperty("点赞数")
    private Integer likeNum;
    
    private String blockId;
    
    private String blockName;
}