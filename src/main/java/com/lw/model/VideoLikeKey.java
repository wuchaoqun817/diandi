package com.lw.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author wuchaoqun
 * @date 2019年10月17日
 * @description 视频-点赞表
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoLikeKey {
    @ApiModelProperty("用户Id")
    private String userId;

    @ApiModelProperty("视频Id")
    private Integer videoId;
    
}