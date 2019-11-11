package com.lw.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 16439
 * @date 2019年09月08日
 * @description 模块表
 */
@Getter
@Setter
public class Block {
    @ApiModelProperty("模块id")
    private Integer id;

    @ApiModelProperty("模块名称")
    private String name;

    @ApiModelProperty("模块代号")
    private String code;

    @ApiModelProperty("父级模块编号")
    private String parentCode;

    @ApiModelProperty("是否显示")
    private boolean show;
    
    @ApiModelProperty("排序")
    private Integer order;
}