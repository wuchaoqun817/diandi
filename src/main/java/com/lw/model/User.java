package com.lw.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("用户角色 ")
    private Integer role;

    @ApiModelProperty("登录名")
    private String loginId;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("性别 0-男 1-女")
    private Integer gender;

    @ApiModelProperty("学习等级")
    private String studyLevel;

    @ApiModelProperty("学校id")
    private String schoolId;

    @ApiModelProperty("学科id")
    private Long domainId;

    @ApiModelProperty("生日")
    private Long birthday;

    @ApiModelProperty("创建时间")
    private Long createDate;

    @ApiModelProperty("更新时间")
    private Long updateDate;

    @ApiModelProperty("微信union_id")
    private String unionId;

    @ApiModelProperty("客户端ip地址")
    private String clientIp;

    @ApiModelProperty("设备号")
    private String deviceId;

    @ApiModelProperty("用户头像")
    private String pictureUrl;
    
    @ApiModelProperty("最近一次登录时间")
    private Long lastLoginTime;
    
    @ApiModelProperty("QQ号")
    private String qq;
    
    @ApiModelProperty("状态 -1已删除  1正常")
    private Integer status;
    
}