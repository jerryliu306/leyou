package com.leyou.item.service;

import com.leyou.common.vo.PageResultVO;
import com.leyou.item.pojo.Brand;

import java.util.List;

/**
 * @Author LiuJinmai
 * @Description 商品品牌管理 业务层接口
 * @Date 2019/1/5 15:31
 */
public interface BrandService {

    /**
     * 分页查询商品品牌
     *
     * @param page      当前页码
     * @param rows      页面数据行数
     * @param sortBy    要排序字段
     * @param desc      是否降序
     * @param searchKey 要搜索的关键字
     * @return 查询结果
     */
    PageResultVO<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, boolean desc, String searchKey);

    /**
     * 新增品牌
     *
     * @param brand 新增品牌信息
     * @param cids  新增品牌分类ID
     */
    void addBrand(Brand brand, List<Long> cids);

    /**
     * 根据Id查询品牌名字
     *
     * @param brandId id
     * @return 名称
     */
    String queryNameById(Long brandId);

    /**
     * 根据三级分类id查询对应的品牌列表
     *
     * @param cid 三级分类id
     * @return 品牌列表
     */
    List<Brand> queryBrandByCategoryId(Long cid);
}
