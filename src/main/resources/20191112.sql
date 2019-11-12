-- 2019-11-12 --
alter table video add column detail varchar(255) comment '视频描述';
alter table video add column update_time datetime comment '更新时间';
alter table video add column update_user_id int(11) comment '更新者';
alter table video modify column create_user_id varchar(64);