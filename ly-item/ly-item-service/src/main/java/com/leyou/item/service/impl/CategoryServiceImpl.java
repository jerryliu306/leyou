package com.leyou.item.service.impl;

import com.leyou.common.enums.ExceptionInfoEnum;
import com.leyou.common.exceptions.LyException;
import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author LiuJinmai
 * @Description 商品类别信息业务层接口实现类
 * @Date 2019/1/3 19:14
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> queryListByPid(Long pid) {
        //创建要查询的 条件对象
        Category category = new Category();
        category.setParentId(pid);
        List<Category> categoryList = categoryMapper.select(category);
        //判断结果
        if (CollectionUtils.isEmpty(categoryList)) {
            throw new LyException(ExceptionInfoEnum.CATEGORY_NOT_FOUND);
        }

        return categoryList;
    }
}
