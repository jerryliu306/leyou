package com.leyou.item.web;

import com.leyou.common.vo.PageResultVO;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author LiuJinmai
 * @Description 商品SPU信息
 * @Date 2019/1/8 14:38
 */
@RestController
public class GoodsController {

    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    /**
     * 分页查询商品SPU列表
     *
     * @param page     当前页数
     * @param rows     页面数据容量
     * @param saleable 是否上架
     * @param key      是否搜索？ 关键字： 无
     * @return 分页结果
     */
    @GetMapping("spu/page")
    public ResponseEntity<PageResultVO<Spu>> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "key", required = false) String key) {
        return ResponseEntity.ok(goodsService.querySpuByPage(page, rows, saleable, key));
    }

    /**
     * 添加新的商品
     *
     * @param spu 待添加商品
     * @return 无返回
     */
    @PostMapping
    public ResponseEntity<Void> addGoods(@RequestBody Spu spu) {
        this.goodsService.addGoods(spu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 修改商品 上下架
     *
     * @param id       商品ID
     * @param saleable 上/下 架
     */
    @PutMapping("goods/{id}/{saleable}")
    public ResponseEntity<Void> editSaleable(@PathVariable("id") Long id, @PathVariable("saleable") Boolean saleable) {
        goodsService.editSaleable(id, saleable);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 根据商品ID 查询商品详情
     *
     * @param id 商品Id
     * @return 商品详细
     */
    @GetMapping("spu/detail/{id}")
    public ResponseEntity<SpuDetail> querySpuDetailById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(goodsService.querySpuDetailById(id));
    }

    /**
     * 根据SpuId 查询sku集合
     *
     * @param spuId Spu_id
     * @return sku集合
     */
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkuBySpuId(@RequestParam("id") Long spuId) {
        return ResponseEntity.ok(goodsService.querySkuBySpuId(spuId));
    }

    /**
     * 修改商品
     *
     * @param spu 商品
     * @return 无返回
     */
    @PutMapping
    public ResponseEntity<Void> updateGoods(@RequestBody Spu spu) {
        this.goodsService.updateGoods(spu);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
