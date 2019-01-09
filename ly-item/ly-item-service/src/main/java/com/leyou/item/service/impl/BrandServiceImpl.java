package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionInfoEnum;
import com.leyou.common.exceptions.LyException;
import com.leyou.common.vo.PageResultVO;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author LiuJinmai
 * @Description 商品品牌管理 业务实现
 * @Date 2019/1/5 15:41
 */
@Service
public class BrandServiceImpl implements BrandService {

    private final BrandMapper brandMapper;

    @Autowired
    public BrandServiceImpl(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    @Override
    public List<Brand> queryBrandByCategoryId(Long cid) {
        List<Brand> brandList = brandMapper.queryBrandByCategoryId(cid);
        //非空判断
        if (CollectionUtils.isEmpty(brandList)) {
            throw new LyException(ExceptionInfoEnum.BRAND_NOT_FOUND);
        }
        return brandList;
    }

    @Override
    public String queryNameById(Long brandId) {
        return brandMapper.queryNameById(brandId);
    }

    @Override
    public void addBrand(Brand brand, List<Long> cids) {
        //新增品牌
        brand.setId(null);
        int count = brandMapper.insert(brand);
        if (count != 1) {
            throw new LyException(ExceptionInfoEnum.BRAND_EDIT_ERROR);
        }
        //cids添加到品牌和商品类别的中间表
        for (Long cid : cids) {
            count = brandMapper.insertCategortBrand(cid, brand.getId());
            if (count != 1) {
                throw new LyException(ExceptionInfoEnum.BRAND_EDIT_ERROR);
            }
        }

    }

    @Override
    public PageResultVO<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, boolean desc, String searchKey) {
        //分页
        PageHelper.startPage(page, rows);
        //过滤。搜索
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(searchKey.trim())) {
            example.createCriteria().orLike("name", " %" + searchKey + "% ")
                    .orEqualTo("letter", searchKey);
        }
        //排序
        if (StringUtils.isNotBlank(sortBy.trim())) {
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询结果
        List<Brand> brandList = brandMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(brandList)) {
            throw new LyException(ExceptionInfoEnum.BRAND_NOT_FOUND);
        }
        //总页数
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brandList);
        //封装返回
        return new PageResultVO<>(brandPageInfo.getTotal(), brandList);
    }
}
