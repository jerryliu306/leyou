package com.leyou.item.service;

import com.leyou.item.pojo.SpecParam;

import java.util.List;

/**
 * @Author LiuJinmai
 * @Description 商品信息的规格参数的参数名称管理接口
 * @Date 2019/1/7 17:59
 */
public interface SpecParamService {
    /**
     * 根据商品规格参数规格组的组ID查询组内的参数集合
     * <p>
     * 修改： 添加商品时，需要根据商品分类ID 查询规格参数
     * 虑到以后可能还会根据是否搜索、是否为通用属性等条件过滤，多添加几个过滤条件
     *
     * @param gid       规格组组ID
     * @param cid       商品分类ID
     * @param searching 过滤条件
     * @return 组内的参数集合
     */
    List<SpecParam> queryParamByGid(Long gid, Long cid, Boolean searching);
}
