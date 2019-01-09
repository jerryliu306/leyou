package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author LiuJinmai
 * @Description 商品品牌和商品类别的关联类
 * @Date 2019/1/5 20:00
 */
@Data
@Table(name = "`tb_category_brand`")
public class CategoryBrand implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 商品类目id
     */
    @Id
    @Column(name = "`category_id`")
    private Long categoryId;
    /**
     * 品牌id
     */
    @Id
    @Column(name = "`brand_id`")
    private Long brandId;
}