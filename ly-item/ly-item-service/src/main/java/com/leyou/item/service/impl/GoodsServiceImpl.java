package com.leyou.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionInfoEnum;
import com.leyou.common.exceptions.LyException;
import com.leyou.common.vo.PageResultVO;
import com.leyou.item.mapper.SkuMapper;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.mapper.StockMapper;
import com.leyou.item.pojo.*;
import com.leyou.item.service.BrandService;
import com.leyou.item.service.CategoryService;
import com.leyou.item.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author LiuJinmai
 * @Description 商品相关操作实现类 ：包含SPU 和 SKU
 * @Date 2019/1/8 15:27
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    private final SpuMapper spuMapper;
    private final SpuDetailMapper spuDetailMapper;
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final SkuMapper skuMapper;
    private final StockMapper stockMapper;

    @Autowired
    public GoodsServiceImpl(SpuMapper spuMapper, SpuDetailMapper spuDetailMapper,
                            CategoryService categoryService, BrandService brandService,
                            SkuMapper skuMapper, StockMapper stockMapper) {
        this.spuMapper = spuMapper;
        this.spuDetailMapper = spuDetailMapper;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.skuMapper = skuMapper;
        this.stockMapper = stockMapper;
    }

    @Override
    @Transactional(rollbackFor = LyException.class)
    public void updateGoods(Spu spu) {
        // 操作影响行数
        int count;

        // 参数判断
        if (spu.getId() == null) {
            throw new LyException(ExceptionInfoEnum.GOODS_ID_CANNOT_BE_NULL);
        }
        Sku sku = new Sku();
        sku.setSpuId(spu.getId());
        // 查询SKU
        List<Sku> skuList = skuMapper.select(sku);
        if (!CollectionUtils.isEmpty(skuList)) {
            // 删除Sku
            count = skuMapper.delete(sku);
            if (count != 1) {
                throw new LyException(ExceptionInfoEnum.DELETE_SKU_FAIL);
            }
            // 删除Stock
            List<Long> skuIdList = skuList.stream().map(Sku::getId).collect(Collectors.toList());
            count = stockMapper.deleteByIdList(skuIdList);
            if (count != skuIdList.size()) {
                throw new LyException(ExceptionInfoEnum.DELETE_STOCK_FAIL);
            }
        }

        // 修改SPU
        spu.setValid(null);
        spu.setSaleable(null);
        spu.setLastUpdateTime(new Date());
        spu.setCreateTime(null);
        count = spuMapper.updateByPrimaryKeySelective(spu);
        if (count != 1) {
            throw new LyException(ExceptionInfoEnum.UPDATE_SPU_FAIL);
        }

        // 修改detail
        count = spuDetailMapper.updateByPrimaryKeySelective(spu.getSpuDetail());
        if (count != 1) {
            throw new LyException(ExceptionInfoEnum.UPDATE_SPU_DETAIL_FAIL);
        }

        //新增sku和stock
        count = addSkuAndStock(spu);
        if (count != 1) {
            throw new LyException(ExceptionInfoEnum.GOODS_SAVE_ERROR_SKU_STOCK);
        }
    }

    @Override
    public List<Sku> querySkuBySpuId(Long spuId) {
        //查询Sku
        Sku sku = new Sku();
        sku.setSpuId(spuId);
        List<Sku> skuList = skuMapper.select(sku);
        if (CollectionUtils.isEmpty(skuList)) {
            throw new LyException(ExceptionInfoEnum.GOODS_SKU_NOT_FOUND);
        }

        //查询库存
        List<Long> skuIds = skuList.stream().map(Sku::getId).collect(Collectors.toList());
        List<Stock> stockList = stockMapper.selectByIdList(skuIds);
        if (CollectionUtils.isEmpty(stockList)) {
            throw new LyException(ExceptionInfoEnum.GOODS_SKU_STOCK_NOT_FOUND);
        }

        //stock变成一个map,其key是:sku的id,值是库存值
        Map<Long, Integer> stockMap = stockList.stream().collect(Collectors.toMap(Stock::getSkuId, Stock::getStock));
        // 从map中取出库存，然后保存到sku
        skuList.forEach(s -> s.setStock(stockMap.get(s.getId())));

        return skuList;
    }

    @Override
    public SpuDetail querySpuDetailById(Long id) {
        SpuDetail spuDetail = spuDetailMapper.selectByPrimaryKey(id);
        if (spuDetail == null) {
            throw new LyException(ExceptionInfoEnum.GOODS_SPU_DETAIL_NOT_FOUND);
        }
        return spuDetail;
    }

    @Override
    public void editSaleable(Long id, Boolean saleable) {
        Boolean b = null;
        Spu spu = new Spu();
        spu.setId(id);
        spu.setSaleable(saleable);
        spu.setLastUpdateTime(new Date());
        int count = spuMapper.updateByPrimaryKeySelective(spu);
        if (count != 1) {
            throw new LyException(ExceptionInfoEnum.GOODS_SALEABLE_EDIT_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = LyException.class)
    public void addGoods(Spu spu) {
        //每次保存结果
        int count;

        //新增SPU
        spu.setId(null);
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        spu.setSaleable(true);
        spu.setValid(true);

        count = spuMapper.insert(spu);
        if (count != 1) {
            throw new LyException(ExceptionInfoEnum.GOODS_SAVE_ERROR_SPU);
        }

        //新增detail
        SpuDetail spuDetail = spu.getSpuDetail();
        spuDetail.setSpuId(spu.getId());
        count = spuDetailMapper.insert(spuDetail);
        if (count != 1) {
            throw new LyException(ExceptionInfoEnum.GOODS_SAVE_ERROR_SPU_DETAIL);
        }

        //新增sku 和 库存
        count = addSkuAndStock(spu);
        if (count != 1) {
            throw new LyException(ExceptionInfoEnum.GOODS_SAVE_ERROR_SKU_STOCK);
        }
    }


    @Override
    public PageResultVO<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
        // 分页
        PageHelper.startPage(page, rows);
        // 准备过滤
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        // 模糊搜索
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("title", "%" + key + "%");
        }
        // 过滤下架的
        if (saleable != null) {
            criteria.andEqualTo("saleable", saleable);
        }
        // 过滤删除的商品
        criteria.andEqualTo("valid", true);
        // 默认排序
        example.setOrderByClause("last_update_time");

        // 查询
        List<Spu> spuList = spuMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(spuList)) {
            throw new LyException(ExceptionInfoEnum.GOODS_SPU_NOT_FOUND);
        }

        // 处理要显示的商品分类名称和品牌名称
        handlerCategoryAndBrandName(spuList);
        // 封装返回
        PageInfo<Spu> pageInfo = new PageInfo<>(spuList);
        return new PageResultVO<>(pageInfo.getTotal(), spuList);
    }

    /**
     * 要显示的商品分类名称和品牌名称
     *
     * @param spuList 待处理商品集合
     */
    private void handlerCategoryAndBrandName(List<Spu> spuList) {
        for (Spu spu : spuList) {
            //处理品牌名称
            spu.setBname(brandService.queryNameById(spu.getBrandId()));
            //处理分类名称
            String names = categoryService.queryByIds(
                    Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
                    .stream().map(Category::getName).collect(Collectors.joining("/"));
            spu.setCname(names);
        }
    }

    /**
     * 添加Sku和库存信息
     *
     * @param spu spu
     * @return 影响行数
     */
    private int addSkuAndStock(Spu spu) {
        //影响行数
        int count;

        //定义库存集合
        List<Stock> stockList = new ArrayList<>();
        //新增Sku
        List<Sku> skuList = spu.getSkus();
        for (Sku sku : skuList) {
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            sku.setSpuId(spu.getId());

            count = skuMapper.insert(sku);
            if (count != 1) {
                throw new LyException(ExceptionInfoEnum.GOODS_SAVE_ERROR_SKU);
            }

            //待新增的库存列表
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stockList.add(stock);
        }
        // 批量新增库存
        count = stockMapper.insertList(stockList);

        if (count != stockList.size()) {
            throw new LyException(ExceptionInfoEnum.GOODS_SAVE_ERROR_SKU_STOCK);
        }

        return count;
    }

}
