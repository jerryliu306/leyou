package com.leyou.item.web;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.service.SpecGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author LiuJinmai
 * @Description 商品信息的规格参数的规格参数组
 * @Date 2019/1/7 14:32
 */
@RestController
@RequestMapping("spec")
public class SpecGroupController {

    private final SpecGroupService groupService;

    @Autowired
    public SpecGroupController(SpecGroupService groupService) {
        this.groupService = groupService;
    }

    /**
     * 根据商品分类id查询规格参数分组集合
     *
     * @param cid 商品ID
     * @return 规格参数组
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupByCid(@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(groupService.queryGroupByCid(cid));
    }
}
