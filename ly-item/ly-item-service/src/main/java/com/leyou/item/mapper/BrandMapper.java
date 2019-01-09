package com.leyou.item.mapper;

import com.leyou.common.mapper.CommonMapper;
import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LiuJinmai
 * @Description 商品品牌管理 持久层接口
 * @Date 2019/1/5 14:59
 */
public interface BrandMapper extends CommonMapper<Brand> {
    /**
     * 新增数据
     *
     * @param cid 分类ID
     * @param bid 品牌ID
     * @return 影响行数
     */
    @Insert("INSERT INTO tb_category_brand (category_id, brand_id) VALUES (#{cid},#{bid})")
    int insertCategortBrand(@Param("cid") Long cid, @Param("bid") Long bid);

    /**
     * 根据名字ID查询品爱名字
     *
     * @param brandId 品牌ID
     * @return 品牌名字
     */
    @Select("SELECT name FROM tb_brand WHERE id = #{brandId}")
    String queryNameById(@Param("brandId") Long brandId);

    /**
     * 根据三级分类id查询对应的品牌列表
     *
     * @param cid 三级分类id
     * @return 品牌列表
     */
    List<Brand> queryBrandByCategoryId(@Param("cid") Long cid);
}