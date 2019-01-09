package com.leyou.item.service;

import com.leyou.item.pojo.SpecGroup;

import java.util.List;

/**
 * @Author LiuJinmai
 * @Description 商品信息的规格参数的规格参数组业务层接口
 * @Date 2019/1/7 14:40
 */
public interface SpecGroupService {
    /**
     * 根据商品分类id查询规格参数分组集合
     *
     * @param cid 商品ID
     * @return 规格参数组
     */
    List<SpecGroup> queryGroupByCid(Long cid);
}
