package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author LiuJinmai
 * @Description 商品品牌实体类
 * @Date 2019/1/5 14:59
 */
@Data
@Table(name = "`tb_brand`")
public class Brand implements Serializable {
    /**
     * 品牌id
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 品牌名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 品牌图片地址
     */
    @Column(name = "`image`")
    private String image;

    /**
     * 品牌的首字母
     */
    @Column(name = "`letter`")
    private String letter;
}