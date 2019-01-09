package com.leyou.item.web;

import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author LiuJinmai
 * @Description 商品信息的规格参数的参数名称管理
 * @Date 2019/1/7 17:53
 */
@RestController
@RequestMapping("spec")
public class SpecParamController {

    private final SpecParamService specParamService;

    @Autowired
    public SpecParamController(SpecParamService specParamService) {
        this.specParamService = specParamService;
    }

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
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParamByGid(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "searching", required = false) Boolean searching) {
        return ResponseEntity.ok(specParamService.queryParamByGid(gid, cid, searching));
    }
}
