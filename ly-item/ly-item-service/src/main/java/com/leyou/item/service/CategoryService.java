package com.leyou.item.service;

import com.leyou.item.pojo.Category;

import java.util.List;

/**
 * @Author LiuJinmai
 * @Description 商品类别信息业务层接口
 * @Date 2019/1/3 19:10
 */
public interface CategoryService {
    /**
     * 根据pid查询该节点下的所有商品种类
     * @param pid 要查询的结点id
     * @return 商品类别集合
     */
    List<Category> queryListByPid(Long pid);
}
