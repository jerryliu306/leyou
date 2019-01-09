package com.leyou.item.service;

import com.leyou.common.vo.PageResultVO;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;

import java.util.List;

/**
 * @Author LiuJinmai
 * @Description 商品相关操作接口 ：包含SPU 和 SKU
 * @Date 2019/1/8 15:24
 */
public interface GoodsService {

    /**
     * 分页查询商品SPU列表
     *
     * @param page     当前页数
     * @param rows     页面数据容量
     * @param saleable 是否上架
     * @param key      是否搜索？ 关键字： 无
     * @return 分页结果
     */
    PageResultVO<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key);

    /**
     * 添加新的商品
     * 除了对SPU新增以外，还要对SpuDetail、Sku、Stock进行保存
     *
     * @param spu 待添加商品
     */
    void addGoods(Spu spu);

    /**
     * 修改商品 上下架
     *
     * @param id       商品ID
     * @param saleable 上/下 架
     */
    void editSaleable(Long id, Boolean saleable);

    /**
     * 根据商品ID 查询商品详情
     *
     * @param id 商品Id
     * @return 商品详细
     */
    SpuDetail querySpuDetailById(Long id);

    /**
     * 根据SpuId 查询sku集合
     *
     * @param spuId Spu_id
     * @return sku集合
     */
    List<Sku> querySkuBySpuId(Long spuId);

    /**
     * 修改商品
     * spu数据可以修改，但是SKU数据无法修改，因为有可能之前存在的SKU现在已经不存在了，
     * 或者以前的sku属性都不存在了。
     * <p>
     * 因此这里直接删除以前的SKU，然后新增即可。
     *
     * @param spu 商品
     */
    void updateGoods(Spu spu);
}
