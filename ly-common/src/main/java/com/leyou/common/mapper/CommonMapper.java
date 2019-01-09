package com.leyou.common.mapper;

import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

/**
 * @Author LiuJinmai
 * @Description 自定义通用Mapper接口；
 * 继承多个TkMapper接口，
 * @Date 2019/1/8 16:28
 */
@RegisterMapper
public interface CommonMapper<T> extends Mapper<T>, IdListMapper<T, Long>,
        InsertMapper<T>, InsertListMapper<T> {
}
