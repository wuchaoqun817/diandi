package com.lw.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author wuchaoqun
 * @date 2019年10月18日
 * @description 
 */
@Getter
@Setter
public class BlockVideoKey {
    @ApiModelProperty("")
    private Integer videoId;

    @ApiModelProperty("")
    private Integer blockId;
}