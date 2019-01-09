package com.leyou.item.web;

import com.leyou.common.vo.PageResultVO;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author LiuJinmai
 * @Description 商品品牌管理
 * @Date 2019/1/5 15:09
 */
@RestController
@RequestMapping("brand")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    /**
     * 分页查询 商品品牌
     *
     * @param page      当前页
     * @param rows      本页数据行数
     * @param sortBy    要排序的字段
     * @param desc      是否降序
     * @param searchKey 搜索条件
     */
    @GetMapping("page")
    public ResponseEntity<PageResultVO<Brand>> queryBrandByPage(@RequestParam("page") Integer page,
                                                                @RequestParam("rows") Integer rows, @RequestParam("sortBy") String sortBy,
                                                                @RequestParam("desc") boolean desc, @RequestParam("key") String searchKey) {
        return ResponseEntity.ok(brandService.queryBrandByPage(page, rows, sortBy, desc, searchKey));
    }

    /**
     * 新增品牌
     *
     * @param brand 新增品牌信息
     * @param cids  新增品牌分类ID
     * @return 不返回
     */
    @PostMapping
    public ResponseEntity<Void> addBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        brandService.addBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据三级分类id查询对应的品牌列表
     *
     * @param cid 三级分类id
     * @return 品牌列表
     */
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCategoryId(@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(brandService.queryBrandByCategoryId(cid));
    }
}
