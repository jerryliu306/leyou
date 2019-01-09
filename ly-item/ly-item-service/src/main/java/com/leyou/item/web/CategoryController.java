package com.leyou.item.web;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author LiuJinmai
 * @Description 商品分类管理
 * @Date 2019/1/3 18:46
 */
@RestController
@RequestMapping("category")
public class CategoryController {

    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 根据Pid查询商品种类
     * @param pid 分类商品结点
     * @return 该节点下类别集合
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryListByPid(@RequestParam("pid") Long pid) {
        return ResponseEntity.ok(categoryService.queryListByPid(pid));
    }

}
