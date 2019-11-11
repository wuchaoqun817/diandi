DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) DEFAULT NULL COMMENT '视频名称',
  `storage_path` varchar(255) DEFAULT NULL COMMENT '视频存储路径',
  `video_size` bigint(20) DEFAULT NULL COMMENT '视频大小',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建者id',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建者名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态',
  `type` varchar(10) DEFAULT NULL COMMENT '类型',
  `is_selected` bit(1) DEFAULT b'0' COMMENT '是否精选',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面',
  `like_num` int(11) default 0 comment '点赞数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='视频表';

drop table block;
create table block(
  id int(4) unsigned not null AUTO_INCREMENT comment '模块id',
  name varchar(10) not null comment '模块名称',
  code varchar(10) not null comment '模块代号',
  parent_code varchar(20) comment '父级模块编号', 
  `order` int(4) not null comment '排序',
  is_show bit(1) comment '是否显示',  
  primary key(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='模块表';


DROP TABLE IF EXISTS `block_video`;
create table block_video(
  block_id int(4) not null,
  video_id int(11) not null,
  primary key(video_id,block_id)
);

DROP TABLE IF EXISTS `video_like`;
CREATE TABLE `video_like` (
  `user_id` varchar(255) NOT NULL COMMENT '用户Id',
  `video_id` int(11) NOT NULL COMMENT '视频Id',
  `create_time` datetime DEFAULT NULL COMMENT '点赞时间',
  PRIMARY KEY (`user_id`,`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频-点赞表';




