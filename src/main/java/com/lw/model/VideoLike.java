package com.lw.model;

import java.util.Date;

import org.springframework.data.annotation.Transient;

import io.swagger.annotations.ApiModelProperty;
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
@NoArgsConstructor
public class VideoLike extends VideoLikeKey {
    @ApiModelProperty("点赞时间")
    private Date createTime;
    
    @Transient
    private boolean isLike;
    
}