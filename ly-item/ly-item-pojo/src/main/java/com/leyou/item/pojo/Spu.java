package com.leyou.item.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author LiuJinmai
 * @Description 商品信息的抽象类
 * @Date 2019/1/8 14:28
 */
@Data
@Table(name = "`tb_spu`")
public class Spu implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 商品详情
     */
    @Transient
    SpuDetail spuDetail;
    /**
     * sku列表
     */
    @Transient
    List<Sku> skus;
    /**
     * spu id
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;
    /**
     * 标题
     */
    @Column(name = "`title`")
    private String title;
    /**
     * 子标题
     */
    @Column(name = "`sub_title`")
    private String subTitle;
    /**
     * 1级类目id
     */
    @Column(name = "`cid1`")
    private Long cid1;
    /**
     * 2级类目id
     */
    @Column(name = "`cid2`")
    private Long cid2;
    /**
     * 3级类目id
     */
    @Column(name = "`cid3`")
    private Long cid3;
    /**
     * 商品所属品牌id
     */
    @Column(name = "`brand_id`")
    private Long brandId;
    /**
     * 是否上架，0下架，1上架
     */
    @Column(name = "`saleable`")
    private Boolean saleable;
    /**
     * 是否有效，0已删除，1有效
     */
    @JsonIgnore
    @Column(name = "`valid`")
    private Boolean valid;
    /**
     * 添加时间
     */
    @JsonIgnore
    @Column(name = "`create_time`")
    private Date createTime;
    /**
     * 最后修改时间
     */
    @JsonIgnore
    @Column(name = "`last_update_time`")
    private Date lastUpdateTime;
    /**
     * 页面展示的需要的商品分类名称
     */
    @Transient
    private String cname;
    /**
     * 页面展示需要的品牌名称
     */
    @Transient
    private String bname;
}